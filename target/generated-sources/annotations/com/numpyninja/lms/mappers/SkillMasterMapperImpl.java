package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.SkillMasterDto;
import com.numpyninja.lms.entity.SkillMaster;
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
public class SkillMasterMapperImpl implements SkillMasterMapper {

    @Override
    public SkillMasterDto toSkillMasterDTO(SkillMaster savedEntity) {
        if ( savedEntity == null ) {
            return null;
        }

        SkillMasterDto skillMasterDto = new SkillMasterDto();

        skillMasterDto.setSkillId( savedEntity.getSkillId() );
        skillMasterDto.setSkillName( savedEntity.getSkillName() );
        skillMasterDto.setCreationTime( savedEntity.getCreationTime() );
        skillMasterDto.setLastModTime( savedEntity.getLastModTime() );

        return skillMasterDto;
    }

    @Override
    public SkillMaster toSkillMasterEntity(SkillMasterDto skillMasterDTO) {
        if ( skillMasterDTO == null ) {
            return null;
        }

        SkillMaster skillMaster = new SkillMaster();

        skillMaster.setSkillId( skillMasterDTO.getSkillId() );
        skillMaster.setSkillName( skillMasterDTO.getSkillName() );
        skillMaster.setCreationTime( skillMasterDTO.getCreationTime() );
        skillMaster.setLastModTime( skillMasterDTO.getLastModTime() );

        return skillMaster;
    }

    @Override
    public List<SkillMasterDto> toSkillMasterDTOList(List<SkillMaster> skillMasterEntites) {
        if ( skillMasterEntites == null ) {
            return null;
        }

        List<SkillMasterDto> list = new ArrayList<SkillMasterDto>( skillMasterEntites.size() );
        for ( SkillMaster skillMaster : skillMasterEntites ) {
            list.add( toSkillMasterDTO( skillMaster ) );
        }

        return list;
    }

    @Override
    public List<SkillMaster> toSkillMasterEntityList(List<SkillMasterDto> skillMasterDTOs) {
        if ( skillMasterDTOs == null ) {
            return null;
        }

        List<SkillMaster> list = new ArrayList<SkillMaster>( skillMasterDTOs.size() );
        for ( SkillMasterDto skillMasterDto : skillMasterDTOs ) {
            list.add( toSkillMasterEntity( skillMasterDto ) );
        }

        return list;
    }
}
