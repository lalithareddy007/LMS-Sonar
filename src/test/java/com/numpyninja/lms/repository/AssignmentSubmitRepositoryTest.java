package com.numpyninja.lms.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.numpyninja.lms.dto.ClassDto;
import com.numpyninja.lms.entity.*;
import com.numpyninja.lms.entity.Class;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class AssignmentSubmitRepositoryTest {

	@MockBean
	private AssignmentSubmitRepository mockassignmentSubmitRepo;

	@Mock
	private List<AssignmentSubmit> mockAssignmentSubmit;
	
	@Mock
	TestEntityManager entManger;

	@BeforeEach
	public void setUp()
	{
		this.mockAssignmentSubmit = setMockassignmentSubmit();

	}

	private List<AssignmentSubmit>  setMockassignmentSubmit() {
		
		List<AssignmentSubmit> mocklist = new ArrayList<AssignmentSubmit>();
		String sDate = "05/25/2022";
		Date dueDate = null;
		try {
			dueDate = new SimpleDateFormat("dd/mm/yyyy").parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalDateTime now = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(now);

		Program program = new Program(7L, "Django", "new Prog",
				"Active", timestamp, timestamp);

		Batch batch = new Batch(1, "SDET 1", "SDET Batch 1", "Active", program,
				5, timestamp, timestamp);
		
		ClassDto class1 = new ClassDto(1L, 1, 1, timestamp, "Selenium1",
                "Active","UO2", "Selenium1 Class", "OK",
                "c:/ClassNotes",
                "c:/RecordingPath","SDET01");

		User user = new User("U01", "Steve", "Jobs", "",
				1234567890L, "CA", "PST", "@stevejobs", "",
				"", "", "Citizen", timestamp, timestamp);

		User user1 = new User("U02", "Elon", "Musk", "",
				1234567809L, "CA", "PST", "@elonmusk", "",
				"", "", "Citizen", timestamp, timestamp);

		Assignment assignment1 = new Assignment(20L, "Test Assignment",
				"Junit test", "practice", dueDate, "Filepath1",
				"Filepath2", "Filepath3", "Filepath4",
				"Filepath5", batch, null, user1, user1, timestamp, timestamp);


		AssignmentSubmit assignmentSubmit = new AssignmentSubmit(1L, assignment1, user,
				"Assignement Submissions", "Assignment Submit for test", "Filepath1",
				"Filepath2", "Filepath3", "Filepath4",
				"Filepath5", Timestamp.valueOf(LocalDateTime.now()), "U01", Timestamp.valueOf(LocalDateTime.now()), 250, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
		AssignmentSubmit assignmentSubmit1 = new AssignmentSubmit(1L, assignment1, user,
				"Assignement Submissions", "Assignment Submit for test", "Filepath1",
				"Filepath2", "Filepath3", "Filepath4",
				"Filepath5", Timestamp.valueOf(LocalDateTime.now()), "U01", Timestamp.valueOf(LocalDateTime.now()), 250, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

		mocklist.add(assignmentSubmit1);
		return mocklist;
	}

//	@DisplayName("Test for find User by UserID")
//	@Test
//	public void testFindByUser_userId() {
//
////		//given
////		assignmentSubmitRepo.save(mockAssignmentSubmit);
//		//expected Assignment Array
//		List<AssignmentSubmit> expectedAssignmentSubmit = this.mockAssignmentSubmit;
//
//
//		when(mockassignmentSubmitRepo.findByUser_userId("U02")).thenReturn((expectedAssignmentSubmit));
//
//
//
//		//then
//		assertThat(expectedAssignmentSubmit).isNotNull();
//		assertThat(expectedAssignmentSubmit.size()).isGreaterThan(0);
//	}

//	@DisplayName("Test for find Assignment submission by Student ID and AssignmentID")
//	@Test
//	public void testFindByStudentIdAndAssignmentId() {
//
//		Optional<List<AssignmentSubmit>>expectedAssignmentSubmit = Optional.ofNullable(this.mockAssignmentSubmit);
//
//		when(mockassignmentSubmitRepo.findByStudentIdAndAssignmentId("U02",20L)).thenReturn((expectedAssignmentSubmit));
//
//
//		//then
//		assertThat(expectedAssignmentSubmit).isNotNull();
//		assertThat(expectedAssignmentSubmit.stream()).hasSizeGreaterThan(0);
//
//	}

//	@Test
//	public void testGetGradesByAssignmentId() {
//		List<AssignmentSubmit>expectedAssignmentSubmit =this.mockAssignmentSubmit;
//
//		when(mockassignmentSubmitRepo.getGradesByAssignmentId(20L)).thenReturn((expectedAssignmentSubmit));
//
//		//then
//		assertThat(expectedAssignmentSubmit).isNotNull();
//		assertThat(expectedAssignmentSubmit.size()).isGreaterThan(0);
//
//	}

	@DisplayName("Test for get Grades by Student ID")
	@Test
	public void testGetGradesByStudentID() {
		List<AssignmentSubmit>expectedAssignmentSubmit =this.mockAssignmentSubmit;

		when(mockassignmentSubmitRepo.getGradesByStudentID("U02")).thenReturn((expectedAssignmentSubmit));

		
		//then
		assertThat(expectedAssignmentSubmit).isNotNull();
		assertThat(expectedAssignmentSubmit.size()).isGreaterThan(0);
	}

//	@Test
//	@DisplayName("Test for reSubmit Assignment")
//	public void testResubmitAssignment() {
//		Optional<List<AssignmentSubmit>>expectedAssignmentSubmit =Optional.ofNullable(this.mockAssignmentSubmit);
//
//
//		when(mockassignmentSubmitRepo.findById(20L)).thenReturn((Optional.of(expectedAssignmentSubmit.get().get(0))));
//
//		AssignmentSubmit submit = new AssignmentSubmit();
//		submit = expectedAssignmentSubmit.get().get(0);
//		assertThat(submit).isNotNull();
//		assertEquals(submit.getAssignmentId(),20L);
//	}


}

