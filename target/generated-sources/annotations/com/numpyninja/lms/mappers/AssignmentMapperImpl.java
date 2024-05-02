package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.AssignmentDto;
import com.numpyninja.lms.entity.Assignment;
import com.numpyninja.lms.entity.Batch;
import com.numpyninja.lms.entity.Class;
import com.numpyninja.lms.entity.User;
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
public class AssignmentMapperImpl implements AssignmentMapper {

    @Override
    public AssignmentDto toAssignmentDto(Assignment assignment) {
        if ( assignment == null ) {
            return null;
        }

        AssignmentDto assignmentDto = new AssignmentDto();

        assignmentDto.setBatchId( assignmentBatchBatchId( assignment ) );
        assignmentDto.setCreatedBy( assignmentUserUserId( assignment ) );
        assignmentDto.setGraderId( assignmentUser1UserId( assignment ) );
        assignmentDto.setCsId( assignmentAclassCsId( assignment ) );
        assignmentDto.setAssignmentId( assignment.getAssignmentId() );
        assignmentDto.setAssignmentName( assignment.getAssignmentName() );
        assignmentDto.setAssignmentDescription( assignment.getAssignmentDescription() );
        assignmentDto.setComments( assignment.getComments() );
        assignmentDto.setDueDate( assignment.getDueDate() );
        assignmentDto.setPathAttachment1( assignment.getPathAttachment1() );
        assignmentDto.setPathAttachment2( assignment.getPathAttachment2() );
        assignmentDto.setPathAttachment3( assignment.getPathAttachment3() );
        assignmentDto.setPathAttachment4( assignment.getPathAttachment4() );
        assignmentDto.setPathAttachment5( assignment.getPathAttachment5() );

        return assignmentDto;
    }

    @Override
    public Assignment toAssignment(AssignmentDto assignmentDto) {
        if ( assignmentDto == null ) {
            return null;
        }

        Assignment assignment = new Assignment();

        assignment.setBatch( assignmentDtoToBatch( assignmentDto ) );
        assignment.setUser( assignmentDtoToUser( assignmentDto ) );
        assignment.setUser1( assignmentDtoToUser1( assignmentDto ) );
        assignment.setAclass( assignmentDtoToClass( assignmentDto ) );
        assignment.setAssignmentId( assignmentDto.getAssignmentId() );
        assignment.setAssignmentName( assignmentDto.getAssignmentName() );
        assignment.setAssignmentDescription( assignmentDto.getAssignmentDescription() );
        assignment.setComments( assignmentDto.getComments() );
        assignment.setDueDate( assignmentDto.getDueDate() );
        assignment.setPathAttachment1( assignmentDto.getPathAttachment1() );
        assignment.setPathAttachment2( assignmentDto.getPathAttachment2() );
        assignment.setPathAttachment3( assignmentDto.getPathAttachment3() );
        assignment.setPathAttachment4( assignmentDto.getPathAttachment4() );
        assignment.setPathAttachment5( assignmentDto.getPathAttachment5() );

        return assignment;
    }

    @Override
    public List<AssignmentDto> toAssignmentDtoList(List<Assignment> assignments) {
        if ( assignments == null ) {
            return null;
        }

        List<AssignmentDto> list = new ArrayList<AssignmentDto>( assignments.size() );
        for ( Assignment assignment : assignments ) {
            list.add( toAssignmentDto( assignment ) );
        }

        return list;
    }

    @Override
    public List<Assignment> toAssignmentList(List<AssignmentDto> AssignmentDtos) {
        if ( AssignmentDtos == null ) {
            return null;
        }

        List<Assignment> list = new ArrayList<Assignment>( AssignmentDtos.size() );
        for ( AssignmentDto assignmentDto : AssignmentDtos ) {
            list.add( toAssignment( assignmentDto ) );
        }

        return list;
    }

    private Integer assignmentBatchBatchId(Assignment assignment) {
        if ( assignment == null ) {
            return null;
        }
        Batch batch = assignment.getBatch();
        if ( batch == null ) {
            return null;
        }
        Integer batchId = batch.getBatchId();
        if ( batchId == null ) {
            return null;
        }
        return batchId;
    }

    private String assignmentUserUserId(Assignment assignment) {
        if ( assignment == null ) {
            return null;
        }
        User user = assignment.getUser();
        if ( user == null ) {
            return null;
        }
        String userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private String assignmentUser1UserId(Assignment assignment) {
        if ( assignment == null ) {
            return null;
        }
        User user1 = assignment.getUser1();
        if ( user1 == null ) {
            return null;
        }
        String userId = user1.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private Long assignmentAclassCsId(Assignment assignment) {
        if ( assignment == null ) {
            return null;
        }
        Class aclass = assignment.getAclass();
        if ( aclass == null ) {
            return null;
        }
        Long csId = aclass.getCsId();
        if ( csId == null ) {
            return null;
        }
        return csId;
    }

    protected Batch assignmentDtoToBatch(AssignmentDto assignmentDto) {
        if ( assignmentDto == null ) {
            return null;
        }

        Batch batch = new Batch();

        batch.setBatchId( assignmentDto.getBatchId() );

        return batch;
    }

    protected User assignmentDtoToUser(AssignmentDto assignmentDto) {
        if ( assignmentDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( assignmentDto.getCreatedBy() );

        return user;
    }

    protected User assignmentDtoToUser1(AssignmentDto assignmentDto) {
        if ( assignmentDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( assignmentDto.getGraderId() );

        return user;
    }

    protected Class assignmentDtoToClass(AssignmentDto assignmentDto) {
        if ( assignmentDto == null ) {
            return null;
        }

        Class class1 = new Class();

        class1.setCsId( assignmentDto.getCsId() );

        return class1;
    }
}
