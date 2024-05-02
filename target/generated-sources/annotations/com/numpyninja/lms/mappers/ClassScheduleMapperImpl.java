package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.ClassDto;
import com.numpyninja.lms.dto.ClassRecordingDTO;
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
public class ClassScheduleMapperImpl implements ClassScheduleMapper {

    @Override
    public ClassDto toClassSchdDTO(Class savedEntity) {
        if ( savedEntity == null ) {
            return null;
        }

        ClassDto classDto = new ClassDto();

        classDto.setBatchId( savedEntityBatchInClassBatchId( savedEntity ) );
        classDto.setClassStaffId( savedEntityStaffInClassUserId( savedEntity ) );
        classDto.setBatchName( savedEntityBatchInClassBatchName( savedEntity ) );
        classDto.setCsId( savedEntity.getCsId() );
        classDto.setClassNo( savedEntity.getClassNo() );
        classDto.setClassDate( savedEntity.getClassDate() );
        classDto.setClassTopic( savedEntity.getClassTopic() );
        classDto.setClassStatus( savedEntity.getClassStatus() );
        classDto.setClassDescription( savedEntity.getClassDescription() );
        classDto.setClassComments( savedEntity.getClassComments() );
        classDto.setClassNotes( savedEntity.getClassNotes() );
        classDto.setClassRecordingPath( savedEntity.getClassRecordingPath() );

        return classDto;
    }

    @Override
    public Class toClassScheduleEntity(ClassDto classSchdDTO) {
        if ( classSchdDTO == null ) {
            return null;
        }

        Class class1 = new Class();

        class1.setBatchInClass( classDtoToBatch( classSchdDTO ) );
        class1.setStaffInClass( classDtoToUser( classSchdDTO ) );
        class1.setCsId( classSchdDTO.getCsId() );
        class1.setClassNo( classSchdDTO.getClassNo() );
        class1.setClassDate( classSchdDTO.getClassDate() );
        class1.setClassTopic( classSchdDTO.getClassTopic() );
        class1.setClassStatus( classSchdDTO.getClassStatus() );
        class1.setClassDescription( classSchdDTO.getClassDescription() );
        class1.setClassComments( classSchdDTO.getClassComments() );
        class1.setClassNotes( classSchdDTO.getClassNotes() );
        class1.setClassRecordingPath( classSchdDTO.getClassRecordingPath() );

        return class1;
    }

    @Override
    public List<ClassDto> toClassScheduleDTOList(List<Class> classSchdEntites) {
        if ( classSchdEntites == null ) {
            return null;
        }

        List<ClassDto> list = new ArrayList<ClassDto>( classSchdEntites.size() );
        for ( Class class1 : classSchdEntites ) {
            list.add( toClassSchdDTO( class1 ) );
        }

        return list;
    }

    @Override
    public List<Class> toClassScheduleEntityList(List<ClassDto> classSchdDTOs) {
        if ( classSchdDTOs == null ) {
            return null;
        }

        List<Class> list = new ArrayList<Class>( classSchdDTOs.size() );
        for ( ClassDto classDto : classSchdDTOs ) {
            list.add( toClassScheduleEntity( classDto ) );
        }

        return list;
    }

    @Override
    public List<ClassRecordingDTO> toClassRecordingDtoList(List<Class> ClassRecords) {
        if ( ClassRecords == null ) {
            return null;
        }

        List<ClassRecordingDTO> list = new ArrayList<ClassRecordingDTO>( ClassRecords.size() );
        for ( Class class1 : ClassRecords ) {
            list.add( classToClassRecordingDTO( class1 ) );
        }

        return list;
    }

    private Integer savedEntityBatchInClassBatchId(Class class1) {
        if ( class1 == null ) {
            return null;
        }
        Batch batchInClass = class1.getBatchInClass();
        if ( batchInClass == null ) {
            return null;
        }
        Integer batchId = batchInClass.getBatchId();
        if ( batchId == null ) {
            return null;
        }
        return batchId;
    }

    private String savedEntityStaffInClassUserId(Class class1) {
        if ( class1 == null ) {
            return null;
        }
        User staffInClass = class1.getStaffInClass();
        if ( staffInClass == null ) {
            return null;
        }
        String userId = staffInClass.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private String savedEntityBatchInClassBatchName(Class class1) {
        if ( class1 == null ) {
            return null;
        }
        Batch batchInClass = class1.getBatchInClass();
        if ( batchInClass == null ) {
            return null;
        }
        String batchName = batchInClass.getBatchName();
        if ( batchName == null ) {
            return null;
        }
        return batchName;
    }

    protected Batch classDtoToBatch(ClassDto classDto) {
        if ( classDto == null ) {
            return null;
        }

        Batch batch = new Batch();

        batch.setBatchId( classDto.getBatchId() );
        batch.setBatchName( classDto.getBatchName() );

        return batch;
    }

    protected User classDtoToUser(ClassDto classDto) {
        if ( classDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( classDto.getClassStaffId() );

        return user;
    }

    protected ClassRecordingDTO classToClassRecordingDTO(Class class1) {
        if ( class1 == null ) {
            return null;
        }

        ClassRecordingDTO classRecordingDTO = new ClassRecordingDTO();

        classRecordingDTO.setCsId( class1.getCsId() );
        classRecordingDTO.setClassRecordingPath( class1.getClassRecordingPath() );

        return classRecordingDTO;
    }
}
