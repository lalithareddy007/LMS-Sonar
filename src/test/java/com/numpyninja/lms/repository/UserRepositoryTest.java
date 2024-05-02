package com.numpyninja.lms.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.numpyninja.lms.dto.UserDto;
import com.numpyninja.lms.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UserRepositoryTest {

	@MockBean
	UserRepository mockuserRepo;

	@Mock
	private List<User> mockUsers;
	private User mockUser;

	@BeforeEach
	public void setUp()
	{
		this.mockUsers = setMockUsers();
	}

	private List<User> setMockUsers()
	{
		Date utilDate = new Date();
		mockUser = new User("U02", "Abdul", "Kalam", " ", 2222222222L, "India", "IST", "www.linkedin.com/Kalam1234",
				"MCA", "MBA", "Indian scientist", "H4", new Timestamp(utilDate.getTime()),
				new Timestamp(utilDate.getTime()));

		List<User> mockUserList= new ArrayList<>();
		mockUserList = new ArrayList<>();
		mockUserList.add(mockUser);
		return mockUserList;
	}

	  @DisplayName("test for getting user By Id")
	  @Test
	  void testFindUserById() {
		  //given
		  List<String> userIdList = new ArrayList<>();
		  userIdList.add("U02");
		  List<User> userList = this.mockUsers;
		  //when
		  when(mockuserRepo.findByUserId(userIdList)).thenReturn(userList);
		  //then
		  assertThat(userList).isNotNull();
	}
	  
	  @DisplayName("test for getting all users")
	  @Test
	  void testFindAllUsers() {
		  //given
		  List<User> userList = this.mockUsers;
		  //when
		  when(mockuserRepo.findAll()).thenReturn(userList);
		  //then
		  assertThat(userList).isNotNull().hasSizeGreaterThan(0);
	  }


}
