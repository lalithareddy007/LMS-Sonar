package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.AssignmentSubmitDTO;
import com.numpyninja.lms.entity.Assignment;
import com.numpyninja.lms.entity.AssignmentSubmit;
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
public class AssignmentSubmitMapperImpl implements AssignmentSubmitMapper {

    @Override
    public AssignmentSubmitDTO toAssignmentSubmitDTO(AssignmentSubmit assignmentSubmit) {
        if ( assignmentSubmit == null ) {
            return null;
        }

        AssignmentSubmitDTO assignmentSubmitDTO = new AssignmentSubmitDTO();

        assignmentSubmitDTO.setAssignmentId( assignmentSubmitAssignmentAssignmentId( assignmentSubmit ) );
        assignmentSubmitDTO.setUserId( assignmentSubmitUserUserId( assignmentSubmit ) );
        assignmentSubmitDTO.setSubmissionId( assignmentSubmit.getSubmissionId() );
        assignmentSubmitDTO.setSubDesc( assignmentSubmit.getSubDesc() );
        assignmentSubmitDTO.setSubComments( assignmentSubmit.getSubComments() );
        assignmentSubmitDTO.setSubPathAttach1( assignmentSubmit.getSubPathAttach1() );
        assignmentSubmitDTO.setSubPathAttach2( assignmentSubmit.getSubPathAttach2() );
        assignmentSubmitDTO.setSubPathAttach3( assignmentSubmit.getSubPathAttach3() );
        assignmentSubmitDTO.setSubPathAttach4( assignmentSubmit.getSubPathAttach4() );
        assignmentSubmitDTO.setSubPathAttach5( assignmentSubmit.getSubPathAttach5() );
        assignmentSubmitDTO.setSubDateTime( assignmentSubmit.getSubDateTime() );
        assignmentSubmitDTO.setGradedBy( assignmentSubmit.getGradedBy() );
        assignmentSubmitDTO.setGradedDateTime( assignmentSubmit.getGradedDateTime() );
        assignmentSubmitDTO.setGrade( assignmentSubmit.getGrade() );

        return assignmentSubmitDTO;
    }

    @Override
    public AssignmentSubmit toAssignmentSubmit(AssignmentSubmitDTO assignmentSubmitDTO) {
        if ( assignmentSubmitDTO == null ) {
            return null;
        }

        AssignmentSubmit assignmentSubmit = new AssignmentSubmit();

        assignmentSubmit.setAssignment( assignmentSubmitDTOToAssignment( assignmentSubmitDTO ) );
        assignmentSubmit.setUser( assignmentSubmitDTOToUser( assignmentSubmitDTO ) );
        assignmentSubmit.setSubmissionId( assignmentSubmitDTO.getSubmissionId() );
        assignmentSubmit.setSubDesc( assignmentSubmitDTO.getSubDesc() );
        assignmentSubmit.setSubComments( assignmentSubmitDTO.getSubComments() );
        assignmentSubmit.setSubPathAttach1( assignmentSubmitDTO.getSubPathAttach1() );
        assignmentSubmit.setSubPathAttach2( assignmentSubmitDTO.getSubPathAttach2() );
        assignmentSubmit.setSubPathAttach3( assignmentSubmitDTO.getSubPathAttach3() );
        assignmentSubmit.setSubPathAttach4( assignmentSubmitDTO.getSubPathAttach4() );
        assignmentSubmit.setSubPathAttach5( assignmentSubmitDTO.getSubPathAttach5() );
        assignmentSubmit.setSubDateTime( assignmentSubmitDTO.getSubDateTime() );
        assignmentSubmit.setGradedBy( assignmentSubmitDTO.getGradedBy() );
        assignmentSubmit.setGradedDateTime( assignmentSubmitDTO.getGradedDateTime() );
        assignmentSubmit.setGrade( assignmentSubmitDTO.getGrade() );

        return assignmentSubmit;
    }

    @Override
    public List<AssignmentSubmitDTO> toAssignmentSubmitDTOList(List<AssignmentSubmit> assignmentSubmitList) {
        if ( assignmentSubmitList == null ) {
            return null;
        }

        List<AssignmentSubmitDTO> list = new ArrayList<AssignmentSubmitDTO>( assignmentSubmitList.size() );
        for ( AssignmentSubmit assignmentSubmit : assignmentSubmitList ) {
            list.add( toAssignmentSubmitDTO( assignmentSubmit ) );
        }

        return list;
    }

    private Long assignmentSubmitAssignmentAssignmentId(AssignmentSubmit assignmentSubmit) {
        if ( assignmentSubmit == null ) {
            return null;
        }
        Assignment assignment = assignmentSubmit.getAssignment();
        if ( assignment == null ) {
            return null;
        }
        Long assignmentId = assignment.getAssignmentId();
        if ( assignmentId == null ) {
            return null;
        }
        return assignmentId;
    }

    private String assignmentSubmitUserUserId(AssignmentSubmit assignmentSubmit) {
        if ( assignmentSubmit == null ) {
            return null;
        }
        User user = assignmentSubmit.getUser();
        if ( user == null ) {
            return null;
        }
        String userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    protected Assignment assignmentSubmitDTOToAssignment(AssignmentSubmitDTO assignmentSubmitDTO) {
        if ( assignmentSubmitDTO == null ) {
            return null;
        }

        Assignment assignment = new Assignment();

        assignment.setAssignmentId( assignmentSubmitDTO.getAssignmentId() );

        return assignment;
    }

    protected User assignmentSubmitDTOToUser(AssignmentSubmitDTO assignmentSubmitDTO) {
        if ( assignmentSubmitDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( assignmentSubmitDTO.getUserId() );

        return user;
    }
}
