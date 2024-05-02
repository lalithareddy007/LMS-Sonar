package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.ProgramDTO;
import com.numpyninja.lms.entity.Program;
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
public class ProgramMapperImpl implements ProgramMapper {

    @Override
    public ProgramDTO toProgramDTO(Program savedEntity) {
        if ( savedEntity == null ) {
            return null;
        }

        ProgramDTO programDTO = new ProgramDTO();

        programDTO.setProgramId( savedEntity.getProgramId() );
        programDTO.setProgramName( savedEntity.getProgramName() );
        programDTO.setProgramDescription( savedEntity.getProgramDescription() );
        programDTO.setProgramStatus( savedEntity.getProgramStatus() );
        programDTO.setCreationTime( savedEntity.getCreationTime() );
        programDTO.setLastModTime( savedEntity.getLastModTime() );

        return programDTO;
    }

    @Override
    public Program toProgramEntity(ProgramDTO progDTO) {
        if ( progDTO == null ) {
            return null;
        }

        Program program = new Program();

        program.setProgramId( progDTO.getProgramId() );
        program.setProgramName( progDTO.getProgramName() );
        program.setProgramDescription( progDTO.getProgramDescription() );
        program.setProgramStatus( progDTO.getProgramStatus() );
        program.setCreationTime( progDTO.getCreationTime() );
        program.setLastModTime( progDTO.getLastModTime() );

        return program;
    }

    @Override
    public List<ProgramDTO> toProgramDTOList(List<Program> programEntities) {
        if ( programEntities == null ) {
            return null;
        }

        List<ProgramDTO> list = new ArrayList<ProgramDTO>( programEntities.size() );
        for ( Program program : programEntities ) {
            list.add( toProgramDTO( program ) );
        }

        return list;
    }

    @Override
    public List<Program> toPogramEntityList(List<ProgramDTO> ProgramDTOs) {
        if ( ProgramDTOs == null ) {
            return null;
        }

        List<Program> list = new ArrayList<Program>( ProgramDTOs.size() );
        for ( ProgramDTO programDTO : ProgramDTOs ) {
            list.add( toProgramEntity( programDTO ) );
        }

        return list;
    }
}
