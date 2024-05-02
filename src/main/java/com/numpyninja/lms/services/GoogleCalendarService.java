package com.numpyninja.lms.services;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.Calendar.Events;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.numpyninja.lms.dto.GCalendarEventRequestDTO;
import com.numpyninja.lms.dto.GCalendarEventResponseDTO;
import com.numpyninja.lms.exception.CalendarAccessDeniedException;
import com.numpyninja.lms.exception.GCalendarCreateEventException;
import com.numpyninja.lms.exception.GCalendarDeleteEventException;
import com.numpyninja.lms.exception.GCalendarEventNotFoundException;
import com.numpyninja.lms.exception.GCalendarIOException;
import com.numpyninja.lms.exception.GCalendarSecurityException;
import com.numpyninja.lms.mappers.GCalendarEventsMapper;
import com.numpyninja.lms.util.GCalenderEventCtreatedEvent;
import com.numpyninja.lms.util.NotificationService;

@Service
public class GoogleCalendarService {
	private static final String UPDATE_EVENT_FAILED =  "Update event failed";
	private static final String GENERAL_SECURITY_EXCEPTION ="GeneralSecurityException";
	private static final String IOEXCEPTION ="IOException";
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	NotificationService notificationService;

	@Value("${spring.application.name}")
	private String APPLICATION_NAME;

	@Value("${google.calendar-id-lms}")
	private String CALENDAR_ID;
	
	@Autowired
	KeyService keyService;
	private static final Logger logger = LoggerFactory.getLogger(GoogleCalendarService.class);

	//Load credentials from file to GoogleCredentials Object
	private GoogleCredentials getServiceCredentials() throws IOException {

		GoogleCredentials credential;

		try {
			InputStream credentialsStream = keyService.getCredentialsAsStream();
			credential = GoogleCredentials
					.fromStream(credentialsStream)
					.createScoped(Collections.singletonList(CalendarScopes.CALENDAR));
			credentialsStream.close();
			logger.info("Credentials fetched successfully.");
		} catch (Exception e) {
			logger.error("Error: ", e);
			throw new GCalendarIOException(e.getLocalizedMessage());
		}
		// .createDelegated("numpyninja01@gmail.com");
		credential.refreshIfExpired();
		logger.info("credential: {}", credential);
		return credential;
	}
	
	//Initialize google calendar service
	private Calendar getCalendarService( GoogleCredentials googleCredential) throws IOException, GeneralSecurityException
	{
		//Uses HttpCredentialsAdapter to convert GoogleCredentials to an HttpRequestInitializer.
		HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(googleCredential);
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
		
		return new Calendar.Builder(httpTransport, jsonFactory, requestInitializer)
				.setApplicationName(APPLICATION_NAME).build();
	}

	public List<GCalendarEventResponseDTO> getEventsUsingServiceAcc()
			throws CalendarAccessDeniedException, GCalendarIOException, GCalendarSecurityException {
		try {
			com.google.api.services.calendar.model.Events eventList;
			
			Calendar calendar = getCalendarService(getServiceCredentials());

			String pageToken = null;
			List<GCalendarEventResponseDTO> eventListResponse = new ArrayList<>();
			
			do {
			//eventList = events.list(CALENDAR_ID).setTimeMin(now).execute();
				eventList = calendar.events().list(CALENDAR_ID).setPageToken(pageToken).execute();
				pageToken = eventList.getNextPageToken();
                eventListResponse.addAll(GCalendarEventsMapper.mapToGCalendarEventResponseDTOList(eventList.getItems()));

			} while(pageToken!=null);
			
			return eventListResponse;
			
		} catch (IOException e) {
			logger.error(IOEXCEPTION, e);
			throw new GCalendarIOException(e.getLocalizedMessage());
		} catch (GeneralSecurityException gse) {
			logger.error(GENERAL_SECURITY_EXCEPTION, gse);
			throw new GCalendarSecurityException(gse.getLocalizedMessage());
		}

	}

	public GCalendarEventResponseDTO createEventUsingServiceAcc(GCalendarEventRequestDTO calendarEventRequestDTO)
			throws GCalendarIOException, CalendarAccessDeniedException, GCalendarCreateEventException,
			GCalendarSecurityException {
		try {
			Calendar calendar = getCalendarService(getServiceCredentials());
			Events events = calendar.events();

			Event event = new Event().setAttachments(
					GCalendarEventsMapper.mapToEventAttachment(calendarEventRequestDTO.getAttachments()))

			/*
			 * Google API throws 403-forbidden error, if attendees are set. TO set attendees,
			 * it needs domain wide delegation of authority, which is not currently enabled.
			 * Hence commenting this line. Once we have a domain account, we need to set
			 * domain wide delegation of authority and uncomment this line.
			 * https://support.google.com/a/answer/162106?hl=en#zippy=%2Cbefore-you-begin%
			 * 2Cset-up-domain-wide-delegation-for-a-client
			 */
//						.setAttendees(GCalendarEventsMapper.mapToEventAttendees(calendarEventRequestDTO.getAttendees()))
					.setDescription(calendarEventRequestDTO.getEventDescription())
					.setLocation(calendarEventRequestDTO.getLocation())
					.setSummary(calendarEventRequestDTO.getEventSummary());

			EventDateTime startDate = new EventDateTime();
			startDate.setDateTime(new DateTime(calendarEventRequestDTO.getEventStartDateTime()));
			// startDate.setTimeZone(TimeZone.getDefault().toString());
			event.setStart(startDate);

			EventDateTime endDate = new EventDateTime();
			endDate.setDateTime(new DateTime(calendarEventRequestDTO.getEventEndDateTime()));
			// endDate.setTimeZone(TimeZone.getDefault().toString());
			event.setEnd(endDate);
			
			//When a new Google calendar is created, an GCalenderEventCreatedEvent is triggered and published using Spring's ApplicationEventPublisher
			GCalenderEventCtreatedEvent newGCalendarEventCreatedEvent = new GCalenderEventCtreatedEvent(calendarEventRequestDTO);
			eventPublisher.publishEvent(newGCalendarEventCreatedEvent);
			notificationService.handleGCalenderEventCteatedEvent(newGCalendarEventCreatedEvent);
			

			Event eventInsertResponse = events.insert(CALENDAR_ID, event).execute();
			logger.debug(eventInsertResponse.toString());
			return GCalendarEventsMapper.mapToGCalendarEventResponseDTO(eventInsertResponse);
		} catch (IOException e) {
			logger.error(IOEXCEPTION, e);
			throw new GCalendarIOException(e.getLocalizedMessage());
			// return new ResponseEntity<List<GCalendarEventResponseDTO>>(new
			// ArrayList<GCalendarEventResponseDTO>(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (GeneralSecurityException gse) {
			logger.error(GENERAL_SECURITY_EXCEPTION, gse);
			throw new GCalendarSecurityException(gse.getLocalizedMessage());
		} catch (Exception e) {
			logger.error("Create event failed:");
			logger.error("Exception:", e);
			throw new GCalendarCreateEventException(e.getLocalizedMessage());
		}
	}

	public GCalendarEventResponseDTO updateEvent( String eventId, @Valid GCalendarEventRequestDTO eventRequest) throws GCalendarSecurityException {
		try {
			Calendar calendar = getCalendarService(getServiceCredentials());
			Events events = calendar.events();

			//Get  the event using the ID
			Event existingEvent = events.get(CALENDAR_ID, eventId).execute();
			
			//Update the event with the  user specified changes
			if(existingEvent != null) {

				/*
				 * Google API throws 403-forbidden error, if attendees are set. To set
				 * attendees, it needs domain wide delegation of authority, which is not
				 * currently enabled. Hence commenting this line. Once we have a domain account,
				 * we need to set domain wide delegation of authority and uncomment this line.
				 * https://support.google.com/a/answer/162106?hl=en#zippy=%2Cbefore-you-begin%
				 * 2Cset-up-domain-wide-delegation-for-a-client
				 */
	//						.setAttendees(GCalendarEventsMapper.mapToEventAttendees(calendarEventRequestDTO.getAttendees()))
				existingEvent.setDescription(eventRequest.getEventDescription());
				existingEvent.setLocation(eventRequest.getLocation());
				existingEvent.setSummary(eventRequest.getEventSummary());
	
				EventDateTime startDate = new EventDateTime();
				startDate.setDateTime(new DateTime(eventRequest.getEventStartDateTime()));
				existingEvent.setStart(startDate);
	
				EventDateTime endDate = new EventDateTime();
				endDate.setDateTime(new DateTime(eventRequest.getEventEndDateTime()));
				existingEvent.setEnd(endDate);
	
				events.update(CALENDAR_ID, eventId, existingEvent).execute();
			}
            assert existingEvent != null;
            return GCalendarEventsMapper.mapToGCalendarEventResponseDTO(existingEvent);
			
		} catch (IOException e) {
			logger.error(UPDATE_EVENT_FAILED);
			logger.error(IOEXCEPTION,e);
			if(e.getMessage().contains("404 Not Found")) {
				throw new GCalendarEventNotFoundException(eventId);
			}
			throw new GCalendarIOException(e.getLocalizedMessage());
		} catch (GeneralSecurityException gse) {
			logger.error(UPDATE_EVENT_FAILED);
			logger.error(GENERAL_SECURITY_EXCEPTION,gse);
			throw new GCalendarSecurityException(gse.getLocalizedMessage());
		} catch (Exception e) {
			logger.error(UPDATE_EVENT_FAILED);
			logger.error("Exception:", e);
			throw new GCalendarCreateEventException(e.getLocalizedMessage());
		}

	}

	public boolean deleteEvent(String eventId) throws GCalendarSecurityException {
		try {
			Calendar calendar = getCalendarService(getServiceCredentials());
			Events events = calendar.events();
			events.delete(CALENDAR_ID, eventId).execute();
			return true;
		} catch (IOException e) {
			logger.error("Delete event failed for event id:{}", eventId);
			logger.error(IOEXCEPTION, e);
			if(e.getMessage().contains("404 Not Found")) {
				throw new GCalendarEventNotFoundException(eventId);
			}
			throw new GCalendarIOException(e.getLocalizedMessage());
		} catch (GeneralSecurityException gse) {
			logger.error("Delete event failed for event id: {}", eventId);
			logger.error(GENERAL_SECURITY_EXCEPTION,gse);
			throw new GCalendarSecurityException("Failed to delete event due to security exception: " + gse.getLocalizedMessage());
		} catch (Exception e) {
			logger.error("Delete event failed for event id: {}", eventId);
			logger.error("Exception: ", e);
			throw new GCalendarDeleteEventException(e.getLocalizedMessage());
		}

	}
	
}
