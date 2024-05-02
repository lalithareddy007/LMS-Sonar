package com.numpyninja.lms.repository;

import com.numpyninja.lms.entity.SkillMaster;
import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.entity.UserSkill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UserSkillRepositoryTest {
    @MockBean
    private UserSkillRepository mockUserSkillRepository;

    @Mock
    private List<UserSkill> mockUserSkills;
    private User mockUser;

    @BeforeEach
    public void setUp()
    {
        this.mockUserSkills = setMockUserSkills();
    }

    private List<UserSkill> setMockUserSkills()
    {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        mockUser = new User("U01", "Steve", "Jobs", "",
                1234567890L, "CA", "PST", "@stevejobs", "BCA",
                "MCA", "", "Citizen", timestamp, timestamp);

        SkillMaster skillMaster = new SkillMaster(2L, "SQL", Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

        UserSkill userSkill1 = new UserSkill("US10", mockUser, skillMaster, 24, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

        UserSkill  userSkill2 = new UserSkill("US21", mockUser, skillMaster, 23, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

        List<UserSkill> mockUserSkillList = new ArrayList<UserSkill>();
        mockUserSkillList.add(userSkill1);
        mockUserSkillList.add(userSkill2);
        return mockUserSkillList;
    }

    @DisplayName("Test for get UserSkill by UserId")
    @Test
    void testFindUserSKillByUserId(){
        //given
        List<UserSkill> userSkills = this.mockUserSkills;
        //when
        when(mockUserSkillRepository.findByUserId("U01")).thenReturn(userSkills);
        //then
        assertThat(userSkills).isNotNull();
        assertThat(userSkills.size()).isGreaterThan(0);
    }

    @DisplayName("Test for get UserSkill by User")
    @Test
    void testFindUserSkillByUser(){
        //given
        List<UserSkill> userSkills =this.mockUserSkills;
        //when
        when(mockUserSkillRepository.findByUser(mockUser)).thenReturn(userSkills);
        //then
        assertThat(userSkills).isNotNull();
        assertThat(userSkills.size()).isGreaterThan(0);
    }
    @DisplayName("Test for Exist By by UserId")
    @Test
    void testExistsByUserId(){
        //given
        List<UserSkill> userSkills =this.mockUserSkills;
        //when
        when(mockUserSkillRepository.existsByUserId("U01")).thenReturn(userSkills);
        //then
        assertThat(userSkills).isNotNull();
        assertThat(userSkills.size()).isGreaterThan(0);
    }


}
