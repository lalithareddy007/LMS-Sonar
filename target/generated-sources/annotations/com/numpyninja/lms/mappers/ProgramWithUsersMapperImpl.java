package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.ProgramWithUsersDTO;
import com.numpyninja.lms.dto.ProgramWithUsersDTO.ProgramWithUsersDTOBuilder;
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
public class ProgramWithUsersMapperImpl implements ProgramWithUsersMapper {

    @Override
    public List<ProgramWithUsersDTO> toProgramsWithUsers(List<UserRoleProgramBatchMap> userRoleProgramMap) {
        if ( userRoleProgramMap == null ) {
            return null;
        }

        List<ProgramWithUsersDTO> list = new ArrayList<ProgramWithUsersDTO>( userRoleProgramMap.size() );
        for ( UserRoleProgramBatchMap userRoleProgramBatchMap : userRoleProgramMap ) {
            list.add( userRoleProgramBatchMapToProgramWithUsersDTO( userRoleProgramBatchMap ) );
        }

        return list;
    }

    protected ProgramWithUsersDTO userRoleProgramBatchMapToProgramWithUsersDTO(UserRoleProgramBatchMap userRoleProgramBatchMap) {
        if ( userRoleProgramBatchMap == null ) {
            return null;
        }

        ProgramWithUsersDTOBuilder programWithUsersDTO = ProgramWithUsersDTO.builder();

        programWithUsersDTO.creationTime( userRoleProgramBatchMap.getCreationTime() );
        programWithUsersDTO.lastModTime( userRoleProgramBatchMap.getLastModTime() );

        return programWithUsersDTO.build();
    }
}
