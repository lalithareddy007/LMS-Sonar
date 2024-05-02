package com.numpyninja.lms.repository;

import com.numpyninja.lms.entity.Program;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ProgramRepositoryTest {
	
	@MockBean
	ProgramRepository programRepository;
    @Mock
	Program mockProgram;

	@BeforeEach
	public void setUp() {
		setMockProgramAndSave();
	}

	private List<Program> setMockProgramAndSave() {


			mockProgram = new Program(1L, "SDET", "SDET 01 Basic", "Active",
					Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
			List<Program> mockProgramList = new ArrayList<>();
			mockProgramList.add(mockProgram);
			return mockProgramList;
	}


	@DisplayName("JUnit test for get Programs by ProgramName ")
	@Test
	void givenProgramName_WhenFindPrograms_ReturnProgramObjects() {

		//given
		String programName = "SDET";

		//when
		List<Program> programList= programRepository.findByProgramName(programName);
		
		//then
		assertThat(programList).isNotNull();
	}

	@DisplayName("JUnit test for get Programs by ProgramName ignoring case ")
	@Test
	void givenProgramName_WhenFindPrograms_ReturnProgramsList() {
		//given
		String programName = "SDET";

		//when
		List<Program> programList= programRepository.findByProgramNameContainingIgnoreCaseOrderByProgramIdAsc(programName);

		//then
		assertThat(programList).isNotNull();
	}

	@DisplayName("test to get program by Id and Status")
	@Test
	public void testFindProgramByProgramIdAndAndProgramStatusEqualsIgnoreCase() {
		Optional<Program> optionalProgram = programRepository
				.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(1L, "active");

		assertThat(optionalProgram).isEmpty();
	}
}


