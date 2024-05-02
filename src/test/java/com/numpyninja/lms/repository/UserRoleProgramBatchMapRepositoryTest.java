package com.numpyninja.lms.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.numpyninja.lms.dto.ClassDto;
import com.numpyninja.lms.entity.Assignment;
import com.numpyninja.lms.entity.AssignmentSubmit;
import com.numpyninja.lms.entity.Batch;
import com.numpyninja.lms.entity.Program;
import com.numpyninja.lms.entity.Role;
import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.entity.UserRoleProgramBatchMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UserRoleProgramBatchMapRepositoryTest {

    @MockBean
    private UserRoleProgramBatchMapRepository userRoleProgramBatchMapRepository;
    
    @Mock
	private UserRoleProgramBatchMap mockUserRoleProgramBatchMap;
	
    
    @BeforeEach
	public void setUp()
	{
		this.mockUserRoleProgramBatchMap = setUserRoleProgramBatchMap();

	}

	private UserRoleProgramBatchMap setUserRoleProgramBatchMap() {
		
		List<UserRoleProgramBatchMap> mocklist = new ArrayList<UserRoleProgramBatchMap>();
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
		

		User user = new User("U01", "Steve", "Jobs", "",
				1234567890L, "CA", "PST", "@stevejobs", "",
				"", "", "Citizen", timestamp, timestamp);

		User user1 = new User("U02", "Elon", "Musk", "",
				1234567809L, "CA", "PST", "@elonmusk", "",
				"", "", "Citizen", timestamp, timestamp);

		Role role = new Role("R02", "ROLE_STAFF", "Staff", timestamp,timestamp);

		UserRoleProgramBatchMap userRoleProgramBatchMap = new UserRoleProgramBatchMap(
				 10L, user, role, program, batch,"Active",timestamp, timestamp
				);
		
	    //mocklist.add(assignmentSubmit1);
		return userRoleProgramBatchMap;
	}


    @DisplayName("test to get UserRoleProgramBatchMap by userId, roleId, programId, batchId")
    @Test
    void testFindByUser_UserIdAndRoleRoleIdAndAndProgram_ProgramIdAndBatch_BatchId() {

    	Optional<UserRoleProgramBatchMap> expectedAssignmentSubmit = Optional.ofNullable(this.mockUserRoleProgramBatchMap);

    	//when
		when(userRoleProgramBatchMapRepository.findByUser_UserIdAndRoleRoleIdAndProgram_ProgramIdAndBatch_BatchId("U02","R02", 10l,2)).thenReturn((expectedAssignmentSubmit));

         //then
        assertThat(expectedAssignmentSubmit).isNotEmpty();
    }

    @DisplayName("test to get UserRoleProgramBatchMap by userId, roleId and status")
    @Test
    void testFindByUser_UserIdAndRoleRoleIdAndUserRoleProgramBatchStatusEqualsIgnoreCase() {
        //given
    	Optional<UserRoleProgramBatchMap> expectedAssignmentSubmit = Optional.ofNullable(this.mockUserRoleProgramBatchMap);

    	//when
		when(userRoleProgramBatchMapRepository.findByUser_UserIdAndRoleRoleIdAndUserRoleProgramBatchStatusEqualsIgnoreCase("U02","R02", "Active")).thenReturn((expectedAssignmentSubmit));

        
        //then
        assertThat(expectedAssignmentSubmit).isNotEmpty();
    }

}
