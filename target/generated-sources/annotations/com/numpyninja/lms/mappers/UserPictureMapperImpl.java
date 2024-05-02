package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.UserPictureEntityDTO;
import com.numpyninja.lms.dto.UserPictureSlimDto;
import com.numpyninja.lms.dto.UserPictureSlimDto.UserPictureSlimDtoBuilder;
import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.entity.UserPictureEntity;
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
public class UserPictureMapperImpl implements UserPictureMapper {

    @Override
    public UserPictureEntity toUserPictureEntity(UserPictureEntityDTO userpicturedto) {
        if ( userpicturedto == null ) {
            return null;
        }

        UserPictureEntity userPictureEntity = new UserPictureEntity();

        userPictureEntity.setUser( userPictureEntityDTOToUser( userpicturedto ) );
        userPictureEntity.setUserFileId( userpicturedto.getUserFileId() );
        userPictureEntity.setUserFileType( userpicturedto.getUserFileType() );
        userPictureEntity.setUserFilePath( userpicturedto.getUserFilePath() );

        return userPictureEntity;
    }

    @Override
    public UserPictureEntityDTO toUserPictureEntityDto(UserPictureEntity userpictureentity) {
        if ( userpictureentity == null ) {
            return null;
        }

        UserPictureEntityDTO userPictureEntityDTO = new UserPictureEntityDTO();

        userPictureEntityDTO.setUserId( userpictureentityUserUserId( userpictureentity ) );
        userPictureEntityDTO.setUserFileId( userpictureentity.getUserFileId() );
        userPictureEntityDTO.setUserFileType( userpictureentity.getUserFileType() );
        userPictureEntityDTO.setUserFilePath( userpictureentity.getUserFilePath() );

        return userPictureEntityDTO;
    }

    @Override
    public UserPictureSlimDto toUserPictureSlimDto(UserPictureEntity userPictureEntity) {
        if ( userPictureEntity == null ) {
            return null;
        }

        UserPictureSlimDtoBuilder userPictureSlimDto = UserPictureSlimDto.builder();

        userPictureSlimDto.userFileId( userPictureEntity.getUserFileId() );
        userPictureSlimDto.userFileType( userPictureEntity.getUserFileType() );
        userPictureSlimDto.userFilePath( userPictureEntity.getUserFilePath() );

        return userPictureSlimDto.build();
    }

    @Override
    public List<UserPictureSlimDto> toUserPictureSlimDtoList(List<UserPictureEntity> userPictureEntityList) {
        if ( userPictureEntityList == null ) {
            return null;
        }

        List<UserPictureSlimDto> list = new ArrayList<UserPictureSlimDto>( userPictureEntityList.size() );
        for ( UserPictureEntity userPictureEntity : userPictureEntityList ) {
            list.add( toUserPictureSlimDto( userPictureEntity ) );
        }

        return list;
    }

    protected User userPictureEntityDTOToUser(UserPictureEntityDTO userPictureEntityDTO) {
        if ( userPictureEntityDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( userPictureEntityDTO.getUserId() );

        return user;
    }

    private String userpictureentityUserUserId(UserPictureEntity userPictureEntity) {
        if ( userPictureEntity == null ) {
            return null;
        }
        User user = userPictureEntity.getUser();
        if ( user == null ) {
            return null;
        }
        String userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }
}
