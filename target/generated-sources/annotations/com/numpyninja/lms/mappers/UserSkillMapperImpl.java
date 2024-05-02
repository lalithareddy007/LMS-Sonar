package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.UserSkillDTO;
import com.numpyninja.lms.dto.UserSkillSlimDto;
import com.numpyninja.lms.dto.UserSkillSlimDto.UserSkillSlimDtoBuilder;
import com.numpyninja.lms.entity.SkillMaster;
import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.entity.UserSkill;
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
public class UserSkillMapperImpl implements UserSkillMapper {

    @Override
    public UserSkillDTO toUserSkillDTO(UserSkill savedEntity) {
        if ( savedEntity == null ) {
            return null;
        }

        UserSkillDTO userSkillDTO = new UserSkillDTO();

        userSkillDTO.setUserId( savedEntityUserUserId( savedEntity ) );
        Long skillId = savedEntitySkillSkillId( savedEntity );
        if ( skillId != null ) {
            userSkillDTO.setSkillId( skillId.intValue() );
        }
        userSkillDTO.setSkillName( savedEntitySkillSkillName( savedEntity ) );
        userSkillDTO.setUserSkillId( savedEntity.getUserSkillId() );
        userSkillDTO.setMonths( savedEntity.getMonths() );

        return userSkillDTO;
    }

    @Override
    public UserSkill toUserSkillEntity(UserSkillDTO userSkillDTO) {
        if ( userSkillDTO == null ) {
            return null;
        }

        UserSkill userSkill = new UserSkill();

        userSkill.setUser( userSkillDTOToUser( userSkillDTO ) );
        userSkill.setSkill( userSkillDTOToSkillMaster( userSkillDTO ) );
        userSkill.setUserSkillId( userSkillDTO.getUserSkillId() );
        userSkill.setMonths( userSkillDTO.getMonths() );

        return userSkill;
    }

    @Override
    public List<UserSkillDTO> toUserSkillDTOList(List<UserSkill> userSkillEntities) {
        if ( userSkillEntities == null ) {
            return null;
        }

        List<UserSkillDTO> list = new ArrayList<UserSkillDTO>( userSkillEntities.size() );
        for ( UserSkill userSkill : userSkillEntities ) {
            list.add( toUserSkillDTO( userSkill ) );
        }

        return list;
    }

    @Override
    public List<UserSkill> toUserSkillEntityList(List<UserSkillDTO> UserSkillDTOs) {
        if ( UserSkillDTOs == null ) {
            return null;
        }

        List<UserSkill> list = new ArrayList<UserSkill>( UserSkillDTOs.size() );
        for ( UserSkillDTO userSkillDTO : UserSkillDTOs ) {
            list.add( toUserSkillEntity( userSkillDTO ) );
        }

        return list;
    }

    @Override
    public UserSkillSlimDto toUserSkillSlimDto(UserSkill userSkill) {
        if ( userSkill == null ) {
            return null;
        }

        UserSkillSlimDtoBuilder userSkillSlimDto = UserSkillSlimDto.builder();

        userSkillSlimDto.skillId( savedEntitySkillSkillId( userSkill ) );
        userSkillSlimDto.skillName( savedEntitySkillSkillName( userSkill ) );
        userSkillSlimDto.months( userSkill.getMonths() );

        return userSkillSlimDto.build();
    }

    @Override
    public List<UserSkillSlimDto> toUserSkillSlimDtoList(List<UserSkill> userSkillList) {
        if ( userSkillList == null ) {
            return null;
        }

        List<UserSkillSlimDto> list = new ArrayList<UserSkillSlimDto>( userSkillList.size() );
        for ( UserSkill userSkill : userSkillList ) {
            list.add( toUserSkillSlimDto( userSkill ) );
        }

        return list;
    }

    private String savedEntityUserUserId(UserSkill userSkill) {
        if ( userSkill == null ) {
            return null;
        }
        User user = userSkill.getUser();
        if ( user == null ) {
            return null;
        }
        String userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private Long savedEntitySkillSkillId(UserSkill userSkill) {
        if ( userSkill == null ) {
            return null;
        }
        SkillMaster skill = userSkill.getSkill();
        if ( skill == null ) {
            return null;
        }
        Long skillId = skill.getSkillId();
        if ( skillId == null ) {
            return null;
        }
        return skillId;
    }

    private String savedEntitySkillSkillName(UserSkill userSkill) {
        if ( userSkill == null ) {
            return null;
        }
        SkillMaster skill = userSkill.getSkill();
        if ( skill == null ) {
            return null;
        }
        String skillName = skill.getSkillName();
        if ( skillName == null ) {
            return null;
        }
        return skillName;
    }

    protected User userSkillDTOToUser(UserSkillDTO userSkillDTO) {
        if ( userSkillDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userSkillDTO.getUserId() );

        return user;
    }

    protected SkillMaster userSkillDTOToSkillMaster(UserSkillDTO userSkillDTO) {
        if ( userSkillDTO == null ) {
            return null;
        }

        SkillMaster skillMaster = new SkillMaster();

        skillMaster.setSkillId( (long) userSkillDTO.getSkillId() );
        skillMaster.setSkillName( userSkillDTO.getSkillName() );

        return skillMaster;
    }
}
