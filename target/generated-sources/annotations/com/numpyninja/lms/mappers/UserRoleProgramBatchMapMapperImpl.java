package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.UserRoleProgramBatchMapDto;
import com.numpyninja.lms.entity.Batch;
import com.numpyninja.lms.entity.Program;
import com.numpyninja.lms.entity.Role;
import com.numpyninja.lms.entity.User;
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
public class UserRoleProgramBatchMapMapperImpl implements UserRoleProgramBatchMapMapper {

    @Override
    public UserRoleProgramBatchMapDto toUserRoleProgramBatchMapDto(UserRoleProgramBatchMap userRoleProgramBatchMap) {
        if ( userRoleProgramBatchMap == null ) {
            return null;
        }

        UserRoleProgramBatchMapDto userRoleProgramBatchMapDto = new UserRoleProgramBatchMapDto();

        userRoleProgramBatchMapDto.setUserId( userRoleProgramBatchMapUserUserId( userRoleProgramBatchMap ) );
        userRoleProgramBatchMapDto.setRoleId( userRoleProgramBatchMapRoleRoleId( userRoleProgramBatchMap ) );
        userRoleProgramBatchMapDto.setProgramId( userRoleProgramBatchMapProgramProgramId( userRoleProgramBatchMap ) );
        userRoleProgramBatchMapDto.setBatchId( userRoleProgramBatchMapBatchBatchId( userRoleProgramBatchMap ) );
        userRoleProgramBatchMapDto.setUserRoleProgramBatchStatus( userRoleProgramBatchMap.getUserRoleProgramBatchStatus() );

        return userRoleProgramBatchMapDto;
    }

    @Override
    public UserRoleProgramBatchMap toUserRoleProgramBatchMap(UserRoleProgramBatchMapDto userRoleProgramBatchMapDto) {
        if ( userRoleProgramBatchMapDto == null ) {
            return null;
        }

        UserRoleProgramBatchMap userRoleProgramBatchMap = new UserRoleProgramBatchMap();

        userRoleProgramBatchMap.setUser( userRoleProgramBatchMapDtoToUser( userRoleProgramBatchMapDto ) );
        userRoleProgramBatchMap.setRole( userRoleProgramBatchMapDtoToRole( userRoleProgramBatchMapDto ) );
        userRoleProgramBatchMap.setProgram( userRoleProgramBatchMapDtoToProgram( userRoleProgramBatchMapDto ) );
        userRoleProgramBatchMap.setBatch( userRoleProgramBatchMapDtoToBatch( userRoleProgramBatchMapDto ) );
        userRoleProgramBatchMap.setUserRoleProgramBatchStatus( userRoleProgramBatchMapDto.getUserRoleProgramBatchStatus() );

        return userRoleProgramBatchMap;
    }

    @Override
    public List<UserRoleProgramBatchMapDto> toUserRoleProgramBatchMapDtoList(List<UserRoleProgramBatchMap> userRoleProgramBatchMaps) {
        if ( userRoleProgramBatchMaps == null ) {
            return null;
        }

        List<UserRoleProgramBatchMapDto> list = new ArrayList<UserRoleProgramBatchMapDto>( userRoleProgramBatchMaps.size() );
        for ( UserRoleProgramBatchMap userRoleProgramBatchMap : userRoleProgramBatchMaps ) {
            list.add( toUserRoleProgramBatchMapDto( userRoleProgramBatchMap ) );
        }

        return list;
    }

    @Override
    public List<UserRoleProgramBatchMap> toUserRoleProgramBatchMapList(List<UserRoleProgramBatchMapDto> userRoleProgramBatchMapDtos) {
        if ( userRoleProgramBatchMapDtos == null ) {
            return null;
        }

        List<UserRoleProgramBatchMap> list = new ArrayList<UserRoleProgramBatchMap>( userRoleProgramBatchMapDtos.size() );
        for ( UserRoleProgramBatchMapDto userRoleProgramBatchMapDto : userRoleProgramBatchMapDtos ) {
            list.add( toUserRoleProgramBatchMap( userRoleProgramBatchMapDto ) );
        }

        return list;
    }

    private String userRoleProgramBatchMapUserUserId(UserRoleProgramBatchMap userRoleProgramBatchMap) {
        if ( userRoleProgramBatchMap == null ) {
            return null;
        }
        User user = userRoleProgramBatchMap.getUser();
        if ( user == null ) {
            return null;
        }
        String userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private String userRoleProgramBatchMapRoleRoleId(UserRoleProgramBatchMap userRoleProgramBatchMap) {
        if ( userRoleProgramBatchMap == null ) {
            return null;
        }
        Role role = userRoleProgramBatchMap.getRole();
        if ( role == null ) {
            return null;
        }
        String roleId = role.getRoleId();
        if ( roleId == null ) {
            return null;
        }
        return roleId;
    }

    private Long userRoleProgramBatchMapProgramProgramId(UserRoleProgramBatchMap userRoleProgramBatchMap) {
        if ( userRoleProgramBatchMap == null ) {
            return null;
        }
        Program program = userRoleProgramBatchMap.getProgram();
        if ( program == null ) {
            return null;
        }
        Long programId = program.getProgramId();
        if ( programId == null ) {
            return null;
        }
        return programId;
    }

    private Integer userRoleProgramBatchMapBatchBatchId(UserRoleProgramBatchMap userRoleProgramBatchMap) {
        if ( userRoleProgramBatchMap == null ) {
            return null;
        }
        Batch batch = userRoleProgramBatchMap.getBatch();
        if ( batch == null ) {
            return null;
        }
        Integer batchId = batch.getBatchId();
        if ( batchId == null ) {
            return null;
        }
        return batchId;
    }

    protected User userRoleProgramBatchMapDtoToUser(UserRoleProgramBatchMapDto userRoleProgramBatchMapDto) {
        if ( userRoleProgramBatchMapDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userRoleProgramBatchMapDto.getUserId() );

        return user;
    }

    protected Role userRoleProgramBatchMapDtoToRole(UserRoleProgramBatchMapDto userRoleProgramBatchMapDto) {
        if ( userRoleProgramBatchMapDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleId( userRoleProgramBatchMapDto.getRoleId() );

        return role;
    }

    protected Program userRoleProgramBatchMapDtoToProgram(UserRoleProgramBatchMapDto userRoleProgramBatchMapDto) {
        if ( userRoleProgramBatchMapDto == null ) {
            return null;
        }

        Program program = new Program();

        program.setProgramId( userRoleProgramBatchMapDto.getProgramId() );

        return program;
    }

    protected Batch userRoleProgramBatchMapDtoToBatch(UserRoleProgramBatchMapDto userRoleProgramBatchMapDto) {
        if ( userRoleProgramBatchMapDto == null ) {
            return null;
        }

        Batch batch = new Batch();

        batch.setBatchId( userRoleProgramBatchMapDto.getBatchId() );

        return batch;
    }
}
