package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.BatchDTO;
import com.numpyninja.lms.dto.BatchSlimDto;
import com.numpyninja.lms.entity.Batch;
import com.numpyninja.lms.entity.Program;
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
public class BatchMapperImpl implements BatchMapper {

    @Override
    public BatchDTO toBatchDTO(Batch batch) {
        if ( batch == null ) {
            return null;
        }

        BatchDTO batchDTO = new BatchDTO();

        batchDTO.setProgramId( batchProgramProgramId( batch ) );
        batchDTO.setProgramName( batchProgramProgramName( batch ) );
        batchDTO.setBatchId( batch.getBatchId() );
        batchDTO.setBatchName( batch.getBatchName() );
        batchDTO.setBatchDescription( batch.getBatchDescription() );
        batchDTO.setBatchStatus( batch.getBatchStatus() );
        if ( batch.getBatchNoOfClasses() != null ) {
            batchDTO.setBatchNoOfClasses( batch.getBatchNoOfClasses() );
        }

        return batchDTO;
    }

    @Override
    public Batch toBatch(BatchDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Batch batch = new Batch();

        batch.setProgram( batchDTOToProgram( dto ) );
        batch.setBatchId( dto.getBatchId() );
        batch.setBatchName( dto.getBatchName() );
        batch.setBatchDescription( dto.getBatchDescription() );
        batch.setBatchStatus( dto.getBatchStatus() );
        batch.setBatchNoOfClasses( dto.getBatchNoOfClasses() );

        return batch;
    }

    @Override
    public List<BatchDTO> toBatchDTOs(List<Batch> baches) {
        if ( baches == null ) {
            return null;
        }

        List<BatchDTO> list = new ArrayList<BatchDTO>( baches.size() );
        for ( Batch batch : baches ) {
            list.add( toBatchDTO( batch ) );
        }

        return list;
    }

    @Override
    public BatchSlimDto toBatchSlimDto(UserRoleProgramBatchMap userRoleProgramBatchMap) {
        if ( userRoleProgramBatchMap == null ) {
            return null;
        }

        BatchSlimDto batchSlimDto = new BatchSlimDto();

        batchSlimDto.setBatchId( userRoleProgramBatchMapBatchBatchId( userRoleProgramBatchMap ) );
        batchSlimDto.setBatchName( userRoleProgramBatchMapBatchBatchName( userRoleProgramBatchMap ) );
        batchSlimDto.setUserRoleProgramBatchStatus( userRoleProgramBatchMap.getUserRoleProgramBatchStatus() );

        return batchSlimDto;
    }

    @Override
    public List<BatchSlimDto> toBatchSlimDtoList(List<UserRoleProgramBatchMap> userRoleProgramBatchMapList) {
        if ( userRoleProgramBatchMapList == null ) {
            return null;
        }

        List<BatchSlimDto> list = new ArrayList<BatchSlimDto>( userRoleProgramBatchMapList.size() );
        for ( UserRoleProgramBatchMap userRoleProgramBatchMap : userRoleProgramBatchMapList ) {
            list.add( toBatchSlimDto( userRoleProgramBatchMap ) );
        }

        return list;
    }

    private Long batchProgramProgramId(Batch batch) {
        if ( batch == null ) {
            return null;
        }
        Program program = batch.getProgram();
        if ( program == null ) {
            return null;
        }
        Long programId = program.getProgramId();
        if ( programId == null ) {
            return null;
        }
        return programId;
    }

    private String batchProgramProgramName(Batch batch) {
        if ( batch == null ) {
            return null;
        }
        Program program = batch.getProgram();
        if ( program == null ) {
            return null;
        }
        String programName = program.getProgramName();
        if ( programName == null ) {
            return null;
        }
        return programName;
    }

    protected Program batchDTOToProgram(BatchDTO batchDTO) {
        if ( batchDTO == null ) {
            return null;
        }

        Program program = new Program();

        program.setProgramId( batchDTO.getProgramId() );

        return program;
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

    private String userRoleProgramBatchMapBatchBatchName(UserRoleProgramBatchMap userRoleProgramBatchMap) {
        if ( userRoleProgramBatchMap == null ) {
            return null;
        }
        Batch batch = userRoleProgramBatchMap.getBatch();
        if ( batch == null ) {
            return null;
        }
        String batchName = batch.getBatchName();
        if ( batchName == null ) {
            return null;
        }
        return batchName;
    }
}
