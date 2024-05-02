package com.numpyninja.lms.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.numpyninja.lms.util.Constants;
import com.numpyninja.lms.util.PhoneNumberDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	@JsonProperty("userId")
	private String userId;
	
	@NotEmpty(message = "User First Name is mandatory")
	@Pattern(regexp = Constants.REGEX_MIN_2_ALPHABET,
			message = "userFirstName " + Constants.MSG_ALPHABET_ONLY_MIN_2)
	@JsonProperty("userFirstName")
	private String userFirstName;
	
	@NotEmpty(message = "User Last Name is mandatory")
	@Pattern(regexp = Constants.REGEX_MIN_1_ALPHABET,
			message = "userLastName " + Constants.MSG_ALPHABET_ONLY_MIN_1)
	@JsonProperty("userLastName")
	private String userLastName;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("userMiddleName")
	@Pattern(regexp = Constants.REGEX_MIN_1_ALPHABET,
			message = "userMiddleName " + Constants.MSG_ALPHABET_ONLY_MIN_1)
	private String userMiddleName;
	
	@NotNull(message = "Phone Number cannot be null/empty")
	@JsonProperty("userPhoneNumber")
	@JsonDeserialize(using = PhoneNumberDeserializer.class)
	private Long userPhoneNumber;
	
	@JsonProperty("userLocation")
	@Pattern(regexp = Constants.REGEX_MIN_2_ALPHABET,
			message = "userLocation " + Constants.MSG_ALPHABET_ONLY_MIN_2)
	private String userLocation;
	
	@JsonProperty("userTimeZone")
	private String userTimeZone;
	
	@JsonProperty("userLinkedinUrl")
	@Pattern(regexp = Constants.REGEX_LINKEDIN_URL,
			message = "userLinkedinUrl" + Constants.MSG_INVALID_LINKEDIN_URL)
	private String userLinkedinUrl;

	@Pattern(regexp = Constants.REGEX_MIN_2_ALPHA_NUMERIC,
			message = "userEduUg " + Constants.MSG_ALPHANUMERIC_ONLY_MIN_2)
	@JsonProperty("userEduUg")
	private String userEduUg;

	@Pattern(regexp = Constants.REGEX_MIN_2_ALPHA_NUMERIC,
			message = "userEduPg " + Constants.MSG_ALPHANUMERIC_ONLY_MIN_2)
	@JsonProperty("userEduPg")
	private String userEduPg;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("userComments")
	private String userComments;
	
	@JsonProperty("userVisaStatus")
	private String userVisaStatus;

	@Pattern(regexp = Constants.REGEX_EMAIL_ID ,
		message = "userLoginEmail " + Constants.MSG_INVALID_EMAIL_ID)
	@JsonProperty("userLoginEmail")
	private String userLoginEmail;

}
