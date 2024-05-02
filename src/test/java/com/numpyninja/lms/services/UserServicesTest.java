package com.numpyninja.lms.services;

import com.numpyninja.lms.dto.*;
import com.numpyninja.lms.entity.*;
import com.numpyninja.lms.exception.DuplicateResourceFoundException;
import com.numpyninja.lms.exception.InvalidDataException;
import com.numpyninja.lms.exception.ResourceNotFoundException;
import com.numpyninja.lms.mappers.*;
import com.numpyninja.lms.repository.*;
import com.numpyninja.lms.security.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserCache;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServicesTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserServices userService;

    @Mock
    UserRoleMapRepository userRoleMapRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserLoginMapper userLoginMapper;

    @Mock
    private BatchMapper batchMapper;

    @Mock
    private UserSkillMapper userSkillMapper;

    @Mock
    private UserPictureMapper userPictureMapper;

    @Mock
    private ProgramRepository programRepository;

    @Mock
    private ProgBatchRepository progBatchRepository;

    @Mock
    private UserRoleProgramBatchMapRepository userRoleProgramBatchMapRepository;

    @Mock
    private UserSkillRepository userSkillRepository;


    @Mock
    private UserPictureRepository userPictureRepository;

    private User mockUser, mockUser2, mockUser3;

    private UserLogin mockUserLogin,mockUserLogin2;

    private UserLoginDto mockUserLoginDto;

    private UserDto mockUserDto, mockUserDto2, mockUserDto3, mockUserDto4;

    private UserRoleMap mockUserRoleMap, mockUserRoleMap2;

    private Role mockRole, mockRole2, mockRole3;

    private Program mockProgram;

    private Batch mockBatch, mockBatch2;

    private UserAndRoleDTO mockUserAndRoleDto;

    private UserRoleMapSlimDTO mockUserRoleMapSlimDto;

    private List<UserRoleMap> userRoleMapList;

    private List<UserRoleMapSlimDTO> userRoleMapsSlimList;

    private UserRoleProgramBatchDto mockUserRoleProgramBatchDtoWithBatch, mockUserRoleProgramBatchDtoWithBatches;

    @Mock
    private UserLoginRepository userLoginRepository;

    //private UserRoleProgramBatchMap mockUserRoleProgramBatchMap;

    private List<UserRoleProgramBatchMap> mockUserRoleProgramBatchMapList;

    private UserRoleProgramBatchMap mockUserRoleProgramBatchMap;

    private UserRoleProgramBatchSlimDto mockUserRoleProgramBatchSlimDto;

    private UserCountByStatusDTO mockUserCountByStatusDto;

    @Mock
    private UserCache userCache;

    private SkillMaster mockSkillMaster;

    @BeforeEach
    void setUp() {
        mockUserDto = setupUserAndUserDTO();

    }

    public UserDto setupUserAndUserDTO() {


        Date utilDate = new Date();
        mockUser = new User("U02", "Abdul", "Kalam", " ", 2222222222L, "India", "IST", "www.linkedin.com/Kalam1234",
                "MCA", "MBA", "Indian scientist", "H4", new Timestamp(utilDate.getTime()),
                new Timestamp(utilDate.getTime()));

        mockUserDto = new UserDto("U02", "Abdul", "Kalam", " ", 2222222222L, "India", "IST", "www.linkedin.com/Kalam1234",
                "MCA", "MBA", "Indian scientist", "H4", "abdul.kalam@gmail.com");

        mockUserLogin = new UserLogin("U02","abdul.kalam@gmail.com","Abdul123","Active",Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()),mockUser);

        mockUserLoginDto = new UserLoginDto("abdul.kalam@gmail.com","Abdul123","Active","Active",List.of("R02","R03"));

        String userRoleStatus = "Active";
        Timestamp Timestamp = new Timestamp(utilDate.getTime());

        Program program = new Program((long) 7, "Django", "new Prog", "nonActive", Timestamp, Timestamp);
        Batch batch = new Batch(1, "SDET 1", "SDET Batch 1", "Active", program, 5, Timestamp, Timestamp);
        Role userRole1 = new Role("R01", "Staff", "LMS_Staff", Timestamp, Timestamp);
        Role userRole2 = new Role("R02", "User", "LMS_User", Timestamp, Timestamp);
        mockRole = new Role("R01", "Staff", "LMS_Staff", Timestamp, Timestamp);

        mockUserRoleMap = new UserRoleMap(1L, mockUser, userRole2, userRoleStatus, Timestamp, Timestamp);

        mockUserRoleMap2 = new UserRoleMap(2L, mockUser, userRole1, userRoleStatus, Timestamp, Timestamp);

        userRoleMapsSlimList = new ArrayList<>();

        mockUserRoleMapSlimDto = new UserRoleMapSlimDTO("R01", "Active");
        userRoleMapsSlimList.add(mockUserRoleMapSlimDto);

        mockUserAndRoleDto = new UserAndRoleDTO("U02", "Abdul", "Kalam", " ", 2222222222L, "India", "IST", "www.linkedin.com/Kalam1234",
                "MCA", "MBA", "Indian scientist", "H4", userRoleMapsSlimList);

        mockUser2 = new User("U07", "Mary", "Poppins", "",
                9899245876L, "India", "IST", "www.linkedin.com/Mary123",
                "BCA", "MBA", "", "H4", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()));

        mockUserDto2 = new UserDto("U07", "Mary", "Poppins", "",
                9899245876L, "India", "IST", "www.linkedin.com/Mary123",
                "BCA", "MBA", "", "H4", "Mary.poppins@gmail.com");

        mockRole2 = new Role("R03", "Student", "LMS_User", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()));

        mockUser3 = new User("U02", "Steve", "Jobs", "",
                9899245877L, "India", "IST", "www.linkedin.com/Steve123",
                "BE", "MBA", "", "H4", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()));

        mockUserDto3 = new UserDto("U02", "Steve", "Jobs", "",
                9899245877L, "India", "IST", "www.linkedin.com/Steve123",
                "BE", "MBA", "", "H4", "steve.jobs@gmail.com");

        mockRole3 = new Role("R02", "Staff", "LMS_Staff", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()));

        mockUserRoleProgramBatchSlimDto = new UserRoleProgramBatchSlimDto(5,"active");

        List<UserRoleProgramBatchSlimDto> mockUserRoleProgramBatches =
                List.of(new UserRoleProgramBatchSlimDto(2, "Active"));
        mockUserRoleProgramBatchDtoWithBatch = UserRoleProgramBatchDto.builder().roleId("R03").programId(2L)
                .userRoleProgramBatches(mockUserRoleProgramBatches).build();

        List<UserRoleProgramBatchSlimDto> mockUserRoleProgramBatches2 =
                List.of(new UserRoleProgramBatchSlimDto(2, "Active"),
                        new UserRoleProgramBatchSlimDto(5, "Active"));
        mockUserRoleProgramBatchDtoWithBatches = UserRoleProgramBatchDto.builder().roleId("R02").programId(2L)
                .userRoleProgramBatches(mockUserRoleProgramBatches2).build();

        mockProgram = new Program(2L, "SDET", "", "Active",
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

        mockBatch = new Batch(1, "SDET 1", "", "Active", mockProgram,
                5, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

        mockBatch2 = new Batch(2, "SDET 2", "", "Active", mockProgram,
                7, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));

        mockUserRoleProgramBatchMap = new UserRoleProgramBatchMap(1L, mockUser2, mockRole2,
                mockProgram, mockBatch, "Active", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()));

        mockSkillMaster = new SkillMaster(1L, "Java", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()));

        return mockUserDto;
    }

   /* @DisplayName("test for creating user with Role info")
    @Test
        //@Order(2)
    void createUserWithRole() throws InvalidDataException, DuplicateResourceFoundException {

        String roleId = "R01";

        userRoleMapList = new ArrayList<>();
        userRoleMapList.add(mockUserRoleMap);
        userRoleMapList.add(mockUserRoleMap2);

        given(userMapper.toUser(mockUserAndRoleDto)).willReturn(mockUser);
        given(userRepo.save(mockUser)).willReturn(mockUser);
        given(roleRepository.getById(roleId)).willReturn(mockRole);
        given(userMapper.userRoleMapList(mockUserAndRoleDto.getUserRoleMaps())).willReturn(userRoleMapList);
        given(userRoleMapRepository.save(userRoleMapList.get(0))).willReturn(mockUserRoleMap);

        given(userMapper.userDto(mockUser)).willReturn(mockUserDto);

        //when
        UserDto userDto = userService.createUserWithRole(mockUserAndRoleDto);
        //then
        assertThat(userDto).isNotNull();

    } */

   /* @DisplayName("test for getAllUsers method")
//    @Test
        //@Order(1)
    void getAllUsersTest() {

        // create mock users
        User mockUser2 = new User();
        mockUser2.setUserId("U03");
        mockUser2.setUserFirstName("Homi");
        mockUser2.setUserLastName("Baba");
        mockUser2.setUserPhoneNumber(1122112211L);

        List<User> userList = new ArrayList<>();
        userList.add(mockUser);
        userList.add(mockUser2);

        UserDto mockUserDto1 = new UserDto();
        mockUserDto1.setUserId(mockUser.getUserId());
        mockUserDto1.setUserFirstName(mockUser.getUserFirstName());
        mockUserDto1.setUserLastName(mockUser.getUserLastName());
        mockUserDto1.setUserPhoneNumber(mockUser.getUserPhoneNumber());

        UserDto mockUserDto2 = new UserDto();
        mockUserDto2.setUserId(mockUser2.getUserId());
        mockUserDto2.setUserFirstName(mockUser2.getUserFirstName());
        mockUserDto2.setUserLastName(mockUser2.getUserLastName());
        mockUserDto2.setUserPhoneNumber(mockUser2.getUserPhoneNumber());

        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(mockUserDto1);
        userDtoList.add(mockUserDto2);

        // create mock user login data
        UserLogin mockUserLogin1 = new UserLogin();
        mockUserLogin1.setUserId(mockUser.getUserId());
        mockUserLogin1.setUserLoginEmail("user1@example.com");

        UserLogin mockUserLogin2 = new UserLogin();
        mockUserLogin2.setUserId(mockUser2.getUserId());
        mockUserLogin2.setUserLoginEmail("user2@example.com");

        Map<String, String> userLoginEmailMap = new HashMap<>();
        userLoginEmailMap.put(mockUser.getUserId(), mockUserLogin1.getUserLoginEmail());
        userLoginEmailMap.put(mockUser2.getUserId(), mockUserLogin2.getUserLoginEmail());

        given(userLoginRepository.findAll()).willReturn(userList);
        given(userMapper.userDtos(userList)).willReturn(userDtoList);
        given(userLoginRepository.findByUserUserId(mockUser.getUserId())).willReturn(Optional.of(mockUserLogin1));
        given(userLoginRepository.findByUserUserId(mockUser2.getUserId())).willReturn(Optional.of(mockUserLogin2));

        //when
        List<UserDto> userDtos = userService.getAllUsers();

        //then
        assertThat(userDtos).isNotNull();
        assertThat(userDtos.size()).isEqualTo(2);
        assertThat(userDtos.get(0).getUserLoginEmail()).isEqualTo(mockUserLogin1.getUserLoginEmail());
        assertThat(userDtos.get(1).getUserLoginEmail()).isEqualTo(mockUserLogin2.getUserLoginEmail());
    } */

    @DisplayName("test for getAllUsers method")
     @Test
        //@Order(1)
    void getAllUsersTest() {
        // create mock users
        User mockUser2 = new User();
        mockUser2.setUserId("U03");
        mockUser2.setUserFirstName("Homi");
        mockUser2.setUserLastName("Baba");
        mockUser2.setUserPhoneNumber(1122112211L);

        // create mock user login data
        UserLogin mockUserLogin1 = new UserLogin();
        mockUserLogin1.setUserId(mockUser.getUserId());
        mockUserLogin1.setUserLoginEmail("user1@example.com");
        mockUserLogin1.setUser( mockUser );

        UserLogin mockUserLogin2 = new UserLogin();
        mockUserLogin2.setUserId(mockUser2.getUserId());
        mockUserLogin2.setUserLoginEmail("user2@example.com");
        mockUserLogin2.setUser( mockUser2 );

        List<UserLogin> userLoginList = new ArrayList<>();
        userLoginList.add(mockUserLogin1);
        userLoginList.add(mockUserLogin1);

        UserDto mockUserDto1 = new UserDto();
        mockUserDto1.setUserId(mockUser.getUserId());
        mockUserDto1.setUserFirstName(mockUser.getUserFirstName());
        mockUserDto1.setUserLastName(mockUser.getUserLastName());
        mockUserDto1.setUserPhoneNumber(mockUser.getUserPhoneNumber());

        UserDto mockUserDto2 = new UserDto();
        mockUserDto2.setUserId(mockUser2.getUserId());
        mockUserDto2.setUserFirstName(mockUser2.getUserFirstName());
        mockUserDto2.setUserLastName(mockUser2.getUserLastName());
        mockUserDto2.setUserPhoneNumber(mockUser2.getUserPhoneNumber());

        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(mockUserDto1);
        userDtoList.add(mockUserDto2);

        //given
        given(userLoginRepository.findAll()).willReturn(userLoginList);
        given(userLoginMapper.toUserDTOs(userLoginList)).willReturn(userDtoList);

        //when
        List<UserDto> userDtos = userService.getAllUsers();

        //then
        assertThat(userDtos).isNotNull();
        assertThat(userDtos.size()).isEqualTo(2);
    }

    @DisplayName("test for deleting an user by id")
    @Test
        //@Order(4)
    void testDeleteUser() {
        UserLogin mockUserLogin = new UserLogin("U02","abdul.kalam@gmail.com","sket","ACTIVE",
                        Timestamp.valueOf(LocalDateTime.now()),Timestamp.valueOf(LocalDateTime.now()),mockUser);
        //given
        given(userRepo.existsById(mockUser.getUserId())).willReturn(true);
        given(userLoginRepository.findById(mockUser.getUserId())).willReturn(Optional.of(mockUserLogin));
        mockUserLogin.setLoginStatus("Inactive");
        given(userLoginRepository.save(mockUserLogin)).willReturn(mockUserLogin);

        //when
        userService.deleteUser(mockUser.getUserId());

        //then
        verify(userRepo).existsById(mockUser.getUserId());
        verify(userLoginRepository).save(mockUserLogin);
        assertEquals("Inactive",mockUserLogin.getLoginStatus());

    }

    @DisplayName("test for getting User Info for a given userId - When User not found")
    @Test
    void testGetUserInfoByIdWhenUserNotFound() {
        String userId = "U99";
        String message = String.format("User not found with Id : %s ", userId);

        when(userLoginRepository.findByUserUserId(userId)).thenReturn(Optional.empty());

        Exception ex = assertThrows(ResourceNotFoundException.class,
                () -> userService.getUserInfoById(userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test for getting User Info for a given userId with role Student")
    @Test
    void testGetUserInfoByIdForStudent() {
        String userId = "U07";
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        List<UserRoleMap> mockUserRoleMaps = List.of(new UserRoleMap(1L, mockUser2, mockRole2,
                "Active", timestamp, timestamp));
        List<UserRoleMapSlimDTO> mockUserRoleMapSlimDtos = List.of(new UserRoleMapSlimDTO(mockRole2.getRoleId(),
                "Active"));

        List<UserRoleProgramBatchMap> mockUserRoleProgramBatchMaps = List.of(new UserRoleProgramBatchMap(
                1L, mockUser2, mockRole2, mockProgram, mockBatch, "Active",
                timestamp, timestamp));
        List<BatchSlimDto> mockBatchslimDtos = List.of(new BatchSlimDto(mockBatch.getBatchId(), mockBatch.getBatchName(),
                "Active"));

        List<UserSkill> mockUserSkills = List.of(new UserSkill("US01", mockUser2, mockSkillMaster, 36,
                timestamp, timestamp));
        List<UserSkillSlimDto> mockUserSkillSlimDtos = List.of(new UserSkillSlimDto(mockSkillMaster.getSkillId(),
                mockSkillMaster.getSkillName(), 36));

        List<UserPictureEntity> mockUserPictureEntityList = List.of(new UserPictureEntity(1L, "ProfilePic",
                mockUser2, "C:\\Images"));
        List<UserPictureSlimDto> mockUserPictureSlimDtos = List.of(new UserPictureSlimDto(1L, "ProfilePic",
                "C:\\Images"));

        when(userLoginRepository.findByUserUserId(userId)).thenReturn(Optional.of(mockUserLogin));
        when(userRoleMapRepository.findUserRoleMapsByUserUserId(userId)).thenReturn(mockUserRoleMaps);
        when(userLoginMapper.toUserDto(mockUserLogin)).thenReturn(mockUserDto);
        when(userMapper.toUserRoleMapSlimDtos(mockUserRoleMaps)).thenReturn(mockUserRoleMapSlimDtos);
        when(userRoleProgramBatchMapRepository.findByUser_UserId(userId)).thenReturn(mockUserRoleProgramBatchMaps);
        when(batchMapper.toBatchSlimDtoList(anyList())).thenReturn(mockBatchslimDtos);
        when(userSkillRepository.findByUserId(userId)).thenReturn(mockUserSkills);
        when(userSkillMapper.toUserSkillSlimDtoList(mockUserSkills)).thenReturn(mockUserSkillSlimDtos);
        when(userPictureRepository.findByUser_UserId(userId)).thenReturn(mockUserPictureEntityList);
        when(userPictureMapper.toUserPictureSlimDtoList(mockUserPictureEntityList)).thenReturn(mockUserPictureSlimDtos);

        UserAllDto responseUserAllDto = userService.getUserInfoById(userId);

        assertThat(responseUserAllDto).isNotNull();
        assertEquals(2L, responseUserAllDto.getUserProgramBatchSlimDtos().get(0).getProgramId());
        assertEquals("Java", responseUserAllDto.getUserSkillSlimDtos().get(0).getSkillName());
        assertEquals("ProfilePic", responseUserAllDto.getUserPictureSlimDtos().get(0).getUserFileType());
        assertEquals("abdul.kalam@gmail.com",responseUserAllDto.getUserDto().getUserLoginEmail());
    }

    @DisplayName("test for getting User Info for a given userId with role Staff")
    @Test
    void testGetUserInfoByIdForStaff() {
        String userId = "U02";
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        List<UserRoleMap> mockUserRoleMaps = List.of(new UserRoleMap(2L, mockUser3, mockRole3,
                "Active", timestamp, timestamp));
        List<UserRoleMapSlimDTO> mockUserRoleMapSlimDtos = List.of(new UserRoleMapSlimDTO(mockRole3.getRoleId(),
                "Active"));

        List<UserRoleProgramBatchMap> mockUserRoleProgramBatchMaps = List.of(
                new UserRoleProgramBatchMap(2L, mockUser3, mockRole3, mockProgram, mockBatch,
                        "Active", timestamp, timestamp),
                new UserRoleProgramBatchMap(3L, mockUser3, mockRole3, mockProgram, mockBatch2,
                        "Active", timestamp, timestamp));
        List<BatchSlimDto> mockBatchslimDtos = List.of(
                new BatchSlimDto(mockBatch.getBatchId(), mockBatch.getBatchName(), "Active"),
                new BatchSlimDto(mockBatch2.getBatchId(), mockBatch2.getBatchName(), "Active"));

        List<UserPictureEntity> mockUserPictureEntityList = List.of(new UserPictureEntity(2L, "Resume",
                mockUser2, "C:\\Documents"));
        List<UserPictureSlimDto> mockUserPictureSlimDtos = List.of(new UserPictureSlimDto(2L, "Resume",
                "C:\\Documents"));

        when(userLoginRepository.findByUserUserId(userId)).thenReturn(Optional.of(mockUserLogin));
        when(userRoleMapRepository.findUserRoleMapsByUserUserId(userId)).thenReturn(mockUserRoleMaps);
        when(userLoginMapper.toUserDto(mockUserLogin)).thenReturn(mockUserDto);
        when(userMapper.toUserRoleMapSlimDtos(mockUserRoleMaps)).thenReturn(mockUserRoleMapSlimDtos);
        when(userRoleProgramBatchMapRepository.findByUser_UserId(userId)).thenReturn(mockUserRoleProgramBatchMaps);
        when(batchMapper.toBatchSlimDtoList(anyList())).thenReturn(mockBatchslimDtos);
        when(userSkillRepository.findByUserId(userId)).thenReturn(Collections.emptyList());
        when(userPictureRepository.findByUser_UserId(userId)).thenReturn(mockUserPictureEntityList);
        when(userPictureMapper.toUserPictureSlimDtoList(mockUserPictureEntityList)).thenReturn(mockUserPictureSlimDtos);

        UserAllDto responseUserAllDto = userService.getUserInfoById(userId);

        assertThat(responseUserAllDto).isNotNull();
        assertEquals(2L, responseUserAllDto.getUserProgramBatchSlimDtos().get(0).getProgramId());
        assertEquals("Resume", responseUserAllDto.getUserPictureSlimDtos().get(0).getUserFileType());
        assertEquals("abdul.kalam@gmail.com",responseUserAllDto.getUserDto().getUserLoginEmail());

    }

    @DisplayName("test for updating an User")
    @Test
    void testUpdateUser() throws ResourceNotFoundException, InvalidDataException {
        //given
        given(userRepo.findById(mockUser.getUserId())).willReturn(Optional.of(mockUser));
        mockUserDto.setUserMiddleName("APJ");
        given(userMapper.user(mockUserDto)).willReturn(mockUser);
        given(userRepo.save(mockUser)).willReturn(mockUser);
        given(userLoginRepository.findByUserUserId(mockUser.getUserId())).willReturn(Optional.of(mockUserLogin));
        mockUserLogin.setUserLoginEmail("abdul.kalam@mail.com");
        given(userLoginRepository.save(mockUserLogin)).willReturn(mockUserLogin);
        given(userLoginMapper.toUserDto(mockUserLogin)).willReturn(mockUserDto);

        //when
        UserDto userDto = userService.updateUser(mockUserDto, mockUser.getUserId());

        //then
        assertThat(userDto).isNotNull();
        assertThat(userDto.getUserMiddleName()).isEqualTo("APJ");
        assertThat(userDto.getUserLoginEmail()).isEqualTo("abdul.kalam@gmail.com");

    }

    /**
     * JUnit test cases for mapping program/batch(es) to Student/Staff : START
     **/
    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - Validate ProgramId")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramId() {
        String userId = "U07";
        String message = "Program Id must be greater than or equal to 1 \n ";

        mockUserRoleProgramBatchDtoWithBatch.setProgramId(-11L);

        Exception ex = assertThrows(InvalidDataException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - Validate BatchId")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateBatchId() {
        String userId = "U07";
        String message = "Batch Id must be greater than or equal to 1 \n ";

        List<UserRoleProgramBatchSlimDto> mockUserRoleProgramBatches =
                List.of(new UserRoleProgramBatchSlimDto(-2, "Active"));
        mockUserRoleProgramBatchDtoWithBatch.setUserRoleProgramBatches(mockUserRoleProgramBatches);

        Exception ex = assertThrows(InvalidDataException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - User-Role-Program-Batch Status is not present")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_UserRoleProgramBatchStatusNotPresent() {
        String userId = "U07";
        String message = "User-Role-Program-Batch Status is Mandatory \n ";

        List<UserRoleProgramBatchSlimDto> mockUserRoleProgramBatches =
                List.of(new UserRoleProgramBatchSlimDto(2, null));
        mockUserRoleProgramBatchDtoWithBatch.setUserRoleProgramBatches(mockUserRoleProgramBatches);

        Exception ex = assertThrows(InvalidDataException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - Validate User-Role-Program-Batch Status")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateUserRoleProgramBatchStatus() {
        String userId = "U07";
        String message = "User-Role-Program-Batch Status can be Active or Inactive \n ";

        List<UserRoleProgramBatchSlimDto> mockUserRoleProgramBatches =
                List.of(new UserRoleProgramBatchSlimDto(2, "Hello"));
        mockUserRoleProgramBatchDtoWithBatch.setUserRoleProgramBatches(mockUserRoleProgramBatches);

        Exception ex = assertThrows(InvalidDataException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - When User is not present")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_WhenUserNotFound() {
        String userId = "U11";
        String message = String.format("User not found with Id : %s ", userId);

        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        Exception ex = assertThrows(ResourceNotFoundException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - When Role is not present")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_WhenRoleNotFound() {
        String userId = "U07";
        String roleId = "R06";
        String message = String.format("Role not found with Id : %s ", roleId);

        mockUser.setUserId("U07");
        mockUserRoleProgramBatchDtoWithBatch.setRoleId(roleId);

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.empty());

        Exception ex = assertThrows(ResourceNotFoundException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - When User-Role mapping is not present")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_WhenUserRoleMappingNotFound() {
        String userId = "U02";
        String message = String.format("User not found with Role : %s ", mockUserRoleProgramBatchDtoWithBatch.getRoleId());

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(false);

        Exception ex = assertThrows(ResourceNotFoundException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - When Program is not present")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_WhenProgramNotFound() {
        String userId = "U07";
        mockUserRoleProgramBatchDtoWithBatch.setProgramId(11L);

        String message = "Program " + mockUserRoleProgramBatchDtoWithBatch.getProgramId()
                + " not found with Program Status : Active ";

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.empty());

        Exception ex = assertThrows(ResourceNotFoundException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - Validate single program/batch for Student - " +
            "Scenario: multiple batches are present")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramBatchForStudent1() {
        String userId = "U07";
        mockUserRoleProgramBatchDtoWithBatches.setRoleId("R03");

        String message = "User with Role " + mockUserRoleProgramBatchDtoWithBatches.getRoleId() +
                " can be assigned to single program/batch";

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.of(mockProgram));

        Exception ex = assertThrows(InvalidDataException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatches, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - Validate Program-Batch mapping for Student - " +
            "Scenario: given batch is inactive or not mapped to given program")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramBatchForStudent2() {
        String userId = "U07";
        String message = "Batch " + mockUserRoleProgramBatchDtoWithBatch.getUserRoleProgramBatches().get(0).getBatchId()
                + " not found with Status as Active for Program " + mockUserRoleProgramBatchDtoWithBatch.getProgramId()
                + "  \n ";

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.of(mockProgram));
        when(progBatchRepository.findBatchByBatchIdAndProgram_ProgramIdAndBatchStatusEqualsIgnoreCase
                (anyInt(), anyLong(), anyString())).thenReturn(Optional.empty());

        Exception ex = assertThrows(InvalidDataException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - When Existing Program-Batch is active for Student" +
            "and received Program-Batch mapping for same Program with different Batch & batchStatus as Active")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramBatchForStudent3() {
        String userId = "U07";
        String message = "Please deactivate User from existing program/batch and then activate for another program/batch";

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.of(mockProgram));
        when(progBatchRepository.findBatchByBatchIdAndProgram_ProgramIdAndBatchStatusEqualsIgnoreCase
                (anyInt(), anyLong(), anyString())).thenReturn(Optional.of(mockBatch));
        when(userRoleProgramBatchMapRepository
                .findByUser_UserIdAndRoleRoleIdAndUserRoleProgramBatchStatusEqualsIgnoreCase
                        (anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(mockUserRoleProgramBatchMap));

        Exception ex = assertThrows(InvalidDataException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - When Existing Program-Batch is active for Student" +
            "and received Program-Batch mapping for same Program with different Batch & batchStatus as Inactive")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramBatchForStudent4() {
        String userId = "U07";
        mockUserRoleProgramBatchDtoWithBatch.getUserRoleProgramBatches().get(0).setUserRoleProgramBatchStatus("Inactive");

        String message = "Please deactivate User from existing program/batch and then activate for another program/batch";

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.of(mockProgram));
        when(progBatchRepository.findBatchByBatchIdAndProgram_ProgramIdAndBatchStatusEqualsIgnoreCase
                (anyInt(), anyLong(), anyString())).thenReturn(Optional.of(mockBatch));
        when(userRoleProgramBatchMapRepository
                .findByUser_UserIdAndRoleRoleIdAndUserRoleProgramBatchStatusEqualsIgnoreCase
                        (anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(mockUserRoleProgramBatchMap));

        Exception ex = assertThrows(InvalidDataException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - When Existing Program-Batch is active for Student" +
            "and received Program-Batch mapping for different Program & batchStatus as Active/Inactive")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramBatchForStudent5() {
        String userId = "U07";
        mockUserRoleProgramBatchDtoWithBatch.setProgramId(1L);

        String message = "Please deactivate User from existing program/batch and then activate for another program/batch";

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.of(mockProgram));
        when(progBatchRepository.findBatchByBatchIdAndProgram_ProgramIdAndBatchStatusEqualsIgnoreCase
                (anyInt(), anyLong(), anyString())).thenReturn(Optional.of(mockBatch));
        when(userRoleProgramBatchMapRepository
                .findByUser_UserIdAndRoleRoleIdAndUserRoleProgramBatchStatusEqualsIgnoreCase
                        (anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(mockUserRoleProgramBatchMap));

        Exception ex = assertThrows(InvalidDataException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - Received Program-Batch mapping for Student")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramBatchForStudent6() {
        String userId = "U07";
        String message = "User " + userId + " has been successfully assigned to Program/Batch(es)";

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.of(mockProgram));
        when(progBatchRepository.findBatchByBatchIdAndProgram_ProgramIdAndBatchStatusEqualsIgnoreCase
                (anyInt(), anyLong(), anyString())).thenReturn(Optional.of(mockBatch));
        when(userRoleProgramBatchMapRepository
                .findByUser_UserIdAndRoleRoleIdAndUserRoleProgramBatchStatusEqualsIgnoreCase
                        (anyString(), anyString(), anyString()))
                .thenReturn(Optional.empty());
        when(userRoleProgramBatchMapRepository
                .findByUser_UserIdAndRoleRoleIdAndProgram_ProgramIdAndBatch_BatchId(anyString(), anyString(),
                        anyLong(), anyInt())).thenReturn(Optional.empty());
        when(userMapper.toUserRoleProgramBatchMap(any(UserRoleProgramBatchSlimDto.class)))
                .thenReturn(mockUserRoleProgramBatchMap);
        when(userRoleProgramBatchMapRepository.save(mockUserRoleProgramBatchMap)).thenReturn(mockUserRoleProgramBatchMap);

        String response = userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId);

        assertEquals(message, response);
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - When Existing Program-Batch is active and " +
            "received same Program-Batch mapping for Student")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramBatchForStudent7() {
        String userId = "U07";
        String message = "User " + userId + " has been successfully assigned to Program/Batch(es)";
        mockUserRoleProgramBatchDtoWithBatch.getUserRoleProgramBatches().get(0).setBatchId(1);
        mockUserRoleProgramBatchDtoWithBatch.getUserRoleProgramBatches().get(0).setUserRoleProgramBatchStatus("Inactive");


        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.of(mockProgram));
        when(progBatchRepository.findBatchByBatchIdAndProgram_ProgramIdAndBatchStatusEqualsIgnoreCase
                (anyInt(), anyLong(), anyString())).thenReturn(Optional.of(mockBatch));
        when(userRoleProgramBatchMapRepository
                .findByUser_UserIdAndRoleRoleIdAndUserRoleProgramBatchStatusEqualsIgnoreCase
                        (anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(mockUserRoleProgramBatchMap));
        when(userRoleProgramBatchMapRepository
                .findByUser_UserIdAndRoleRoleIdAndProgram_ProgramIdAndBatch_BatchId(anyString(), anyString(),
                        anyLong(), anyInt())).thenReturn(Optional.of(mockUserRoleProgramBatchMap));
        lenient().when(userMapper.toUserRoleProgramBatchMap(any(UserRoleProgramBatchSlimDto.class)))
                .thenReturn(mockUserRoleProgramBatchMap);
        when(userRoleProgramBatchMapRepository.save(mockUserRoleProgramBatchMap)).thenReturn(mockUserRoleProgramBatchMap);

        String response = userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId);

        assertEquals(message, response);
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - Validate single Program-Batch mapping for Staff - " +
            "Scenario: given batch is inactive or not mapped to given program")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramBatchForStaff1() {
        String userId = "U09";
        mockUserRoleProgramBatchDtoWithBatch.setRoleId("R02");

        String message = "Batch " + mockUserRoleProgramBatchDtoWithBatch.getUserRoleProgramBatches().get(0).getBatchId()
                + " not found with Status as Active for Program " + mockUserRoleProgramBatchDtoWithBatch.getProgramId()
                + "  \n ";

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.of(mockProgram));
        when(progBatchRepository.findBatchByBatchIdAndProgram_ProgramIdAndBatchStatusEqualsIgnoreCase
                (anyInt(), anyLong(), anyString())).thenReturn(Optional.empty());

        Exception ex = assertThrows(InvalidDataException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - Received single Program-Batch mapping for Staff")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramBatchForStaff2() {
        String userId = "U09";
        String message = "User " + userId + " has been successfully assigned to Program/Batch(es)";
        mockUserRoleProgramBatchDtoWithBatch.setRoleId("R02");

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.of(mockProgram));
        when(progBatchRepository.findBatchByBatchIdAndProgram_ProgramIdAndBatchStatusEqualsIgnoreCase
                (anyInt(), anyLong(), anyString())).thenReturn(Optional.of(mockBatch));
        lenient().when(userRoleProgramBatchMapRepository
                        .findByUser_UserIdAndRoleRoleIdAndUserRoleProgramBatchStatusEqualsIgnoreCase
                                (anyString(), anyString(), anyString()))
                .thenReturn(Optional.empty());
        when(userRoleProgramBatchMapRepository
                .findByUser_UserIdAndRoleRoleIdAndProgram_ProgramIdAndBatch_BatchId(anyString(), anyString(),
                        anyLong(), anyInt())).thenReturn(Optional.empty());
        when(userMapper.toUserRoleProgramBatchMap(any(UserRoleProgramBatchSlimDto.class)))
                .thenReturn(mockUserRoleProgramBatchMap);
        when(userRoleProgramBatchMapRepository.save(mockUserRoleProgramBatchMap)).thenReturn(mockUserRoleProgramBatchMap);

        String response = userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId);

        assertEquals(message, response);
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - When Existing Program-Batch is active and " +
            "received same Program-Batch mapping for Staff")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramBatchForStaff3() {
        String userId = "U09";
        String message = "User " + userId + " has been successfully assigned to Program/Batch(es)";
        mockUserRoleProgramBatchDtoWithBatch.setRoleId("R02");
        mockUserRoleProgramBatchDtoWithBatch.getUserRoleProgramBatches().get(0).setBatchId(1);
        mockUserRoleProgramBatchDtoWithBatch.getUserRoleProgramBatches().get(0).setUserRoleProgramBatchStatus("Inactive");


        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.of(mockProgram));
        when(progBatchRepository.findBatchByBatchIdAndProgram_ProgramIdAndBatchStatusEqualsIgnoreCase
                (anyInt(), anyLong(), anyString())).thenReturn(Optional.of(mockBatch));
        lenient().when(userRoleProgramBatchMapRepository
                        .findByUser_UserIdAndRoleRoleIdAndUserRoleProgramBatchStatusEqualsIgnoreCase
                                (anyString(), anyString(), anyString()))
                .thenReturn(Optional.of(mockUserRoleProgramBatchMap));
        when(userRoleProgramBatchMapRepository
                .findByUser_UserIdAndRoleRoleIdAndProgram_ProgramIdAndBatch_BatchId(anyString(), anyString(),
                        anyLong(), anyInt())).thenReturn(Optional.of(mockUserRoleProgramBatchMap));
        lenient().when(userMapper.toUserRoleProgramBatchMap(any(UserRoleProgramBatchSlimDto.class)))
                .thenReturn(mockUserRoleProgramBatchMap);
        when(userRoleProgramBatchMapRepository.save(mockUserRoleProgramBatchMap)).thenReturn(mockUserRoleProgramBatchMap);

        String response = userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatch, userId);

        assertEquals(message, response);
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - Validate multiple Program-Batches mapping for Staff - " +
            "Scenario: given all batches are inactive or not mapped to given program")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramBatchForStaff4() {
        String userId = "U09";

        String message = "Batch " + mockUserRoleProgramBatchDtoWithBatches.getUserRoleProgramBatches().get(0).getBatchId()
                + " not found with Status as Active for Program " + mockUserRoleProgramBatchDtoWithBatches.getProgramId()
                + "  \n "
                + "Batch " + mockUserRoleProgramBatchDtoWithBatches.getUserRoleProgramBatches().get(1).getBatchId()
                + " not found with Status as Active for Program " + mockUserRoleProgramBatchDtoWithBatches.getProgramId()
                + "  \n ";

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.of(mockProgram));
        when(progBatchRepository.findBatchByBatchIdAndProgram_ProgramIdAndBatchStatusEqualsIgnoreCase
                (anyInt(), anyLong(), anyString())).thenReturn(Optional.empty());

        Exception ex = assertThrows(InvalidDataException.class,
                () -> userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatches, userId));

        assertEquals(message, ex.getMessage());
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - Validate multiple Program-Batches mapping for Staff - " +
            "Scenario: given some batches are inactive or not mapped to given program")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramBatchForStaff5() {
        String userId = "U09";

        String message = "User " + userId + " has failed for - Batch " +
                mockUserRoleProgramBatchDtoWithBatches.getUserRoleProgramBatches().get(1).getBatchId()
                + " not found with Status as Active for Program " + mockUserRoleProgramBatchDtoWithBatches.getProgramId()
                + "  \n ";

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.of(mockProgram));
        when(progBatchRepository.findBatchByBatchIdAndProgram_ProgramIdAndBatchStatusEqualsIgnoreCase
                (eq(2), eq(2L), eq("Active"))).thenReturn(Optional.of(mockBatch));
        when(progBatchRepository.findBatchByBatchIdAndProgram_ProgramIdAndBatchStatusEqualsIgnoreCase
                (eq(5), eq(2L), eq("Active"))).thenReturn(Optional.empty());
        lenient().when(userRoleProgramBatchMapRepository
                .findByUser_UserIdAndRoleRoleIdAndProgram_ProgramIdAndBatch_BatchId(eq(userId), eq("R02"),
                        eq(2L), eq(2))).thenReturn(Optional.of(mockUserRoleProgramBatchMap));
        when(userMapper.toUserRoleProgramBatchMap(any(UserRoleProgramBatchSlimDto.class)))
                .thenReturn(mockUserRoleProgramBatchMap);
        when(userRoleProgramBatchMapRepository.save(mockUserRoleProgramBatchMap)).thenReturn(mockUserRoleProgramBatchMap);

        String response = userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatches, userId);

        assertEquals(message, response);
    }

    @DisplayName("test - assignUpdateUserRoleProgramBatchStatus - Received multiple Program-Batches mapping for Staff")
    @Test
    void testAssignUpdateUserRoleProgramBatchStatus_ValidateProgramBatchForStaff6() {
        String userId = "U09";
        String message = "User " + userId + " has been successfully assigned to Program/Batch(es)";

        when(userRepo.findById(userId)).thenReturn(Optional.of(mockUser2));
        when(roleRepository.findById(anyString())).thenReturn(Optional.of(mockRole2));
        when(userRoleMapRepository.existsUserRoleMapByUser_UserIdAndRole_RoleIdAndUserRoleStatusEqualsIgnoreCase
                (anyString(), anyString(), anyString())).thenReturn(true);
        when(programRepository.findProgramByProgramIdAndProgramStatusEqualsIgnoreCase(anyLong(), anyString()))
                .thenReturn(Optional.of(mockProgram));
        when(progBatchRepository.findBatchByBatchIdAndProgram_ProgramIdAndBatchStatusEqualsIgnoreCase
                (anyInt(), anyLong(), anyString())).thenReturn(Optional.of(mockBatch));
        lenient().when(userRoleProgramBatchMapRepository
                        .findByUser_UserIdAndRoleRoleIdAndUserRoleProgramBatchStatusEqualsIgnoreCase
                                (anyString(), anyString(), anyString()))
                .thenReturn(Optional.empty());
        when(userRoleProgramBatchMapRepository
                .findByUser_UserIdAndRoleRoleIdAndProgram_ProgramIdAndBatch_BatchId(anyString(), anyString(),
                        anyLong(), anyInt())).thenReturn(Optional.empty());
        when(userMapper.toUserRoleProgramBatchMap(any(UserRoleProgramBatchSlimDto.class)))
                .thenReturn(mockUserRoleProgramBatchMap);
        when(userRoleProgramBatchMapRepository.save(mockUserRoleProgramBatchMap)).thenReturn(mockUserRoleProgramBatchMap);

        String response = userService.assignUpdateUserRoleProgramBatchStatus(mockUserRoleProgramBatchDtoWithBatches, userId);

        assertEquals(message, response);
    }

    /**
     * JUnit test cases for mapping program/batch(es) to Student/Staff : END
     **/


    @DisplayName("test for getting List of Users for a given Program/batch batchid")
    @Test
    void testGetUserByProgramBatches() {
        Batch batch = mockBatch;
        given(progBatchRepository.findById(mockBatch.getBatchId())).willReturn(Optional.of(mockBatch));

        UserRoleProgramBatchMap userRoleProgramBatchMap = new UserRoleProgramBatchMap();
        userRoleProgramBatchMap.setUserRoleProgramBatchStatus("active");
        mockUserRoleProgramBatchMap.setUser(mockUser2);

        List<UserRoleProgramBatchMap> userRoleProgramBatchMaplist = new ArrayList<UserRoleProgramBatchMap>();
        userRoleProgramBatchMaplist.add(mockUserRoleProgramBatchMap);
        when(userRoleProgramBatchMapRepository.findByBatch_BatchId(batch.getBatchId()))
                .thenReturn(userRoleProgramBatchMaplist);

        List<UserDto> mockUserDtoList = new ArrayList<>();
        mockUserDtoList.add(mockUserDto);
        when(userMapper.userDtos(anyList())).thenReturn(mockUserDtoList);

        // When
        List<UserDto> result = userService.getUserByProgramBatch(batch.getBatchId());

        // Then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo(mockUserDto);
    }


    @DisplayName("test for getting List of Users for a given Program - ProgramId")
    @Test
    void testGetUsersByProgram_returnsUsers_whenProgramExists() {
        // Given
        long programId = 7;


        when(programRepository.findById(programId)).thenReturn(Optional.of(mockProgram));

        UserRoleProgramBatchMap mockUserRoleProgramBatchMap = new UserRoleProgramBatchMap();

        mockUserRoleProgramBatchMap.setUserRoleProgramBatchStatus("Active");
        mockUserRoleProgramBatchMap.setUser(mockUser2);

        List<UserRoleProgramBatchMap> userRoleProgramBatchMaplist = new ArrayList<UserRoleProgramBatchMap>();
        userRoleProgramBatchMaplist.add(mockUserRoleProgramBatchMap);
        when(userRoleProgramBatchMapRepository.findByProgram_ProgramId(programId)).thenReturn(userRoleProgramBatchMaplist);

        List<UserDto> mockUserDtoList = new ArrayList<>();
        mockUserDtoList.add(mockUserDto);
        when(userMapper.userDtos(anyList())).thenReturn(mockUserDtoList);

        // When
        List<UserDto> result = userService.getUsersByProgram(programId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo(mockUserDto);
    }

    @DisplayName("Test getUsersByProgram_returnsEmptyList_whenProgramDoesNotExist()")
    @Test
    void testGetUsersByProgram_returnsEmptyList_whenProgramDoesNotExist() {
        // Given
        long programId = 30;
        when(programRepository.findById(programId)).thenReturn(Optional.empty());

        // When
        assertThrows(ResourceNotFoundException.class, () -> userService.getUsersByProgram(programId));

        // Then
        Mockito.verify(programRepository).findById(programId);

    }

    @DisplayName("test to update user login")
    @Test
    void testUpdateUserLogin()
    {
        String userId="U02";
        String updatedMessage = "UserLogin updated successfully";

        mockUserLoginDto.setUserLoginEmail("abdul.kalam1@gmail.com");
        mockUserLoginDto.setLoginStatus("Inactive");

        //given
        given(userRepo.findById(userId)).willReturn(Optional.of(mockUser));
        given(userLoginRepository.findByUserUserId(userId)).willReturn(Optional.of(mockUserLogin));
        given(userLoginRepository.findByUserLoginEmailIgnoreCase(mockUserLoginDto.getUserLoginEmail())).willReturn(Optional.of(mockUserLogin));
        doNothing().when(userLoginRepository).updateUserLogin(userId, mockUserLoginDto.getUserLoginEmail(), mockUserLoginDto.getLoginStatus());

        //when
        String result = userService.updateUserLogin(mockUserLoginDto,userId);

        //then
        assertThat(result).isEqualTo(updatedMessage);
        assertThat(mockUserLoginDto.getUserLoginEmail()).isEqualTo("abdul.kalam1@gmail.com");
    }


    @DisplayName("test to get all users with roles")
    @Test
    void testGetAllUsersWithRoles()
    {
        userRoleMapList = new ArrayList<>();
        userRoleMapList.add(mockUserRoleMap);
        userRoleMapList.add(mockUserRoleMap2);

        //given
        given(userRoleMapRepository.findAll()).willReturn(userRoleMapList);

        //when
        List<UserRoleMap> result = userService.getAllUsersWithRoles();

        //then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
    }

    @DisplayName("test to get users by role id")
    @Test
    void testGetUsersByRoleId()
    {
        String roleId = "R03";
        userRoleMapList = new ArrayList<>();
        userRoleMapList.add(mockUserRoleMap);
        userRoleMapList.add(mockUserRoleMap2);

        List<User> userList = new ArrayList<>();
        userList.add(mockUser);
        userList.add(mockUser2);

        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(mockUserDto);
        userDtoList.add(mockUserDto2);

        //given
        given(roleRepository.findById(roleId)).willReturn(Optional.of(mockRole2));
        given(userRoleMapRepository.findByRole_RoleId(roleId)).willReturn(userRoleMapList);
        given(userMapper.userDtos(anyList())).willReturn(userDtoList);

        //when
        List<UserDto> result = userService.getUsersByRoleID(roleId);

        //then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualTo(mockUserDto);
    }

    @DisplayName("test to get all roles")
    @Test
    void testGetAllRoles()
    {
        List<Role> roleList = new ArrayList<>();
        roleList.add(mockRole);
        roleList.add(mockRole2);
        roleList.add(mockRole3);

        //given
        given(roleRepository.findAll()).willReturn(roleList);

        //when
        List<Role> result = userService.getAllRoles();

        //then
        assertThat(result).isNotNull();
    }

    @DisplayName("test to get user with active status")
    @Test
    void testGetUserWithActiveStatus()
    {
        String userId = "U02";
        mockUserLogin2 = new UserLogin("U03","abraham.lincoln@gmail.com","Abrahaml123","Active",Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()),mockUser);

        List<UserLogin> userLoginList = new ArrayList<>();
        userLoginList.add(mockUserLogin);
        userLoginList.add(mockUserLogin2);

        List<User> userList = new ArrayList<>();
        userList.add(mockUser);
        userList.add(mockUser2);

        //given
        given(userLoginRepository.findByLoginStatus("active")).willReturn(userLoginList);
        given(userRepo.findByUserId(anyList())).willReturn(userList);

        //when
        List<User> result = userService.getUserWithActiveStatus();

        //then
        assertThat(result).isNotNull();
    }

    @DisplayName("test to get Batch id By user id")
    @Test
    void testGetBatchIdByUserId()
    {
        String userId = "U02";

        UserRoleProgramBatchMap mockUserRoleProgramBatchMap2 = new UserRoleProgramBatchMap(2L, mockUser3, mockRole2,
                mockProgram, mockBatch, "Active", Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()));
        mockUserRoleProgramBatchMapList = new ArrayList<>();
        mockUserRoleProgramBatchMapList.add(mockUserRoleProgramBatchMap);
        mockUserRoleProgramBatchMapList.add(mockUserRoleProgramBatchMap2);

        //given
        given(userRepo.findById(userId)).willReturn(Optional.of(mockUser));
        given(userRoleProgramBatchMapRepository.findByUser_UserId(userId)).willReturn(mockUserRoleProgramBatchMapList);

        //when
        int result = userService.getBatchIdByUserId(userId);

        //then
        assertThat(result).isNotNull();
    }

}

