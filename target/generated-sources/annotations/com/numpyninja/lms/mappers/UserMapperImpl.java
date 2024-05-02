package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.UserAndRoleDTO;
import com.numpyninja.lms.dto.UserDto;
import com.numpyninja.lms.dto.UserLoginDto;
import com.numpyninja.lms.dto.UserLoginRoleDTO;
import com.numpyninja.lms.dto.UserRoleIdDTO;
import com.numpyninja.lms.dto.UserRoleMapSlimDTO;
import com.numpyninja.lms.dto.UserRoleProgramBatchDto;
import com.numpyninja.lms.dto.UserRoleProgramBatchDto.UserRoleProgramBatchDtoBuilder;
import com.numpyninja.lms.dto.UserRoleProgramBatchSlimDto;
import com.numpyninja.lms.entity.Batch;
import com.numpyninja.lms.entity.Role;
import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.entity.UserLogin;
import com.numpyninja.lms.entity.UserRoleMap;
import com.numpyninja.lms.entity.UserRoleProgramBatchMap;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-02T10:21:19-0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserId( user.getUserId() );
        userDto.setUserFirstName( user.getUserFirstName() );
        userDto.setUserLastName( user.getUserLastName() );
        userDto.setUserMiddleName( user.getUserMiddleName() );
        userDto.setUserPhoneNumber( user.getUserPhoneNumber() );
        userDto.setUserLocation( user.getUserLocation() );
        userDto.setUserTimeZone( user.getUserTimeZone() );
        userDto.setUserLinkedinUrl( user.getUserLinkedinUrl() );
        userDto.setUserEduUg( user.getUserEduUg() );
        userDto.setUserEduPg( user.getUserEduPg() );
        userDto.setUserComments( user.getUserComments() );
        userDto.setUserVisaStatus( user.getUserVisaStatus() );

        return userDto;
    }

    @Override
    public User user(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userDto.getUserId() );
        user.setUserFirstName( userDto.getUserFirstName() );
        user.setUserLastName( userDto.getUserLastName() );
        user.setUserMiddleName( userDto.getUserMiddleName() );
        user.setUserPhoneNumber( userDto.getUserPhoneNumber() );
        user.setUserLocation( userDto.getUserLocation() );
        user.setUserTimeZone( userDto.getUserTimeZone() );
        user.setUserLinkedinUrl( userDto.getUserLinkedinUrl() );
        user.setUserEduUg( userDto.getUserEduUg() );
        user.setUserEduPg( userDto.getUserEduPg() );
        user.setUserComments( userDto.getUserComments() );
        user.setUserVisaStatus( userDto.getUserVisaStatus() );

        return user;
    }

    @Override
    public List<UserDto> userDtos(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( userDto( user ) );
        }

        return list;
    }

    @Override
    public User toUser(UserAndRoleDTO userAndRoleDto) {
        if ( userAndRoleDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userAndRoleDto.getUserId() );
        user.setUserFirstName( userAndRoleDto.getUserFirstName() );
        user.setUserLastName( userAndRoleDto.getUserLastName() );
        user.setUserMiddleName( userAndRoleDto.getUserMiddleName() );
        user.setUserPhoneNumber( userAndRoleDto.getUserPhoneNumber() );
        user.setUserLocation( userAndRoleDto.getUserLocation() );
        user.setUserTimeZone( userAndRoleDto.getUserTimeZone() );
        user.setUserLinkedinUrl( userAndRoleDto.getUserLinkedinUrl() );
        user.setUserEduUg( userAndRoleDto.getUserEduUg() );
        user.setUserEduPg( userAndRoleDto.getUserEduPg() );
        user.setUserComments( userAndRoleDto.getUserComments() );
        user.setUserVisaStatus( userAndRoleDto.getUserVisaStatus() );

        return user;
    }

    @Override
    public User toUser(UserLoginRoleDTO userLoginRoleDTO) {
        if ( userLoginRoleDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userLoginRoleDTO.getUserId() );
        user.setUserFirstName( userLoginRoleDTO.getUserFirstName() );
        user.setUserLastName( userLoginRoleDTO.getUserLastName() );
        user.setUserMiddleName( userLoginRoleDTO.getUserMiddleName() );
        user.setUserPhoneNumber( userLoginRoleDTO.getUserPhoneNumber() );
        user.setUserLocation( userLoginRoleDTO.getUserLocation() );
        user.setUserTimeZone( userLoginRoleDTO.getUserTimeZone() );
        user.setUserLinkedinUrl( userLoginRoleDTO.getUserLinkedinUrl() );
        user.setUserEduUg( userLoginRoleDTO.getUserEduUg() );
        user.setUserEduPg( userLoginRoleDTO.getUserEduPg() );
        user.setUserComments( userLoginRoleDTO.getUserComments() );
        user.setUserVisaStatus( userLoginRoleDTO.getUserVisaStatus() );

        return user;
    }

    @Override
    public UserLogin toUserLogin(UserLoginDto userLogin) {
        if ( userLogin == null ) {
            return null;
        }

        UserLogin userLogin1 = new UserLogin();

        userLogin1.setUserLoginEmail( userLogin.getUserLoginEmail() );
        userLogin1.setPassword( userLogin.getPassword() );
        userLogin1.setLoginStatus( userLogin.getLoginStatus() );

        return userLogin1;
    }

    @Override
    public UserRoleMap userRoleMap(UserRoleMapSlimDTO userRoleMapSlimDto) {
        if ( userRoleMapSlimDto == null ) {
            return null;
        }

        UserRoleMap userRoleMap = new UserRoleMap();

        userRoleMap.setUserRoleStatus( userRoleMapSlimDto.getUserRoleStatus() );

        return userRoleMap;
    }

    @Override
    public List<UserRoleMap> userRoleMapList(List<UserRoleMapSlimDTO> userRoleMapSlimDto) {
        if ( userRoleMapSlimDto == null ) {
            return null;
        }

        List<UserRoleMap> list = new ArrayList<UserRoleMap>( userRoleMapSlimDto.size() );
        for ( UserRoleMapSlimDTO userRoleMapSlimDTO : userRoleMapSlimDto ) {
            list.add( userRoleMap( userRoleMapSlimDTO ) );
        }

        return list;
    }

    @Override
    public UserRoleMap userRoleMap(UserAndRoleDTO userAndRoleDto) {
        if ( userAndRoleDto == null ) {
            return null;
        }

        UserRoleMap userRoleMap = new UserRoleMap();

        return userRoleMap;
    }

    @Override
    public UserRoleMap userRole(UserRoleIdDTO updateRoleId) {
        if ( updateRoleId == null ) {
            return null;
        }

        UserRoleMap userRoleMap = new UserRoleMap();

        return userRoleMap;
    }

    @Override
    public List<UserRoleMap> touserRoleMapList(List<UserAndRoleDTO> userAndRoleDto) {
        if ( userAndRoleDto == null ) {
            return null;
        }

        List<UserRoleMap> list = new ArrayList<UserRoleMap>( userAndRoleDto.size() );
        for ( UserAndRoleDTO userAndRoleDTO : userAndRoleDto ) {
            list.add( userRoleMap( userAndRoleDTO ) );
        }

        return list;
    }

    @Override
    public UserRoleMapSlimDTO toUserSlimRoleMapDto(UserRoleMap userRoleMap) {
        if ( userRoleMap == null ) {
            return null;
        }

        UserRoleMapSlimDTO userRoleMapSlimDTO = new UserRoleMapSlimDTO();

        userRoleMapSlimDTO.setRoleId( userRoleMapRoleRoleId( userRoleMap ) );
        userRoleMapSlimDTO.setUserRoleStatus( userRoleMap.getUserRoleStatus() );

        return userRoleMapSlimDTO;
    }

    @Override
    public UserRoleProgramBatchMap toUserRoleProgramBatchMap(UserRoleProgramBatchSlimDto userRoleProgramBatchSlimDto) {
        if ( userRoleProgramBatchSlimDto == null ) {
            return null;
        }

        UserRoleProgramBatchMap userRoleProgramBatchMap = new UserRoleProgramBatchMap();

        userRoleProgramBatchMap.setBatch( userRoleProgramBatchSlimDtoToBatch( userRoleProgramBatchSlimDto ) );
        userRoleProgramBatchMap.setUserRoleProgramBatchStatus( userRoleProgramBatchSlimDto.getUserRoleProgramBatchStatus() );

        return userRoleProgramBatchMap;
    }

    @Override
    public List<UserRoleProgramBatchDto> toUserRoleProgramBatchMapDtoList(List<UserRoleProgramBatchMap> UserProgBatch) {
        if ( UserProgBatch == null ) {
            return null;
        }

        List<UserRoleProgramBatchDto> list = new ArrayList<UserRoleProgramBatchDto>( UserProgBatch.size() );
        for ( UserRoleProgramBatchMap userRoleProgramBatchMap : UserProgBatch ) {
            list.add( userRoleProgramBatchMapToUserRoleProgramBatchDto( userRoleProgramBatchMap ) );
        }

        return list;
    }

    @Override
    public List<UserRoleMapSlimDTO> toUserRoleMapSlimDtos(List<UserRoleMap> userRoleMaps) {
        if ( userRoleMaps == null ) {
            return null;
        }

        List<UserRoleMapSlimDTO> list = new ArrayList<UserRoleMapSlimDTO>( userRoleMaps.size() );
        for ( UserRoleMap userRoleMap : userRoleMaps ) {
            list.add( toUserSlimRoleMapDto( userRoleMap ) );
        }

        return list;
    }

    private String userRoleMapRoleRoleId(UserRoleMap userRoleMap) {
        if ( userRoleMap == null ) {
            return null;
        }
        Role role = userRoleMap.getRole();
        if ( role == null ) {
            return null;
        }
        String roleId = role.getRoleId();
        if ( roleId == null ) {
            return null;
        }
        return roleId;
    }

    protected Batch userRoleProgramBatchSlimDtoToBatch(UserRoleProgramBatchSlimDto userRoleProgramBatchSlimDto) {
        if ( userRoleProgramBatchSlimDto == null ) {
            return null;
        }

        Batch batch = new Batch();

        batch.setBatchId( userRoleProgramBatchSlimDto.getBatchId() );

        return batch;
    }

    protected UserRoleProgramBatchDto userRoleProgramBatchMapToUserRoleProgramBatchDto(UserRoleProgramBatchMap userRoleProgramBatchMap) {
        if ( userRoleProgramBatchMap == null ) {
            return null;
        }

        UserRoleProgramBatchDtoBuilder userRoleProgramBatchDto = UserRoleProgramBatchDto.builder();

        return userRoleProgramBatchDto.build();
    }
}
