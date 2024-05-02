package com.numpyninja.lms.mappers;

import com.numpyninja.lms.dto.UserDto;
import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.entity.UserLogin;
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
public class UserLoginMapperImpl implements UserLoginMapper {

    @Override
    public UserDto toUserDto(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUserFirstName( userLoginUserUserFirstName( userLogin ) );
        userDto.setUserLastName( userLoginUserUserLastName( userLogin ) );
        userDto.setUserMiddleName( userLoginUserUserMiddleName( userLogin ) );
        userDto.setUserPhoneNumber( userLoginUserUserPhoneNumber( userLogin ) );
        userDto.setUserTimeZone( userLoginUserUserTimeZone( userLogin ) );
        userDto.setUserLinkedinUrl( userLoginUserUserLinkedinUrl( userLogin ) );
        userDto.setUserLocation( userLoginUserUserLocation( userLogin ) );
        userDto.setUserEduUg( userLoginUserUserEduUg( userLogin ) );
        userDto.setUserEduPg( userLoginUserUserEduPg( userLogin ) );
        userDto.setUserComments( userLoginUserUserComments( userLogin ) );
        userDto.setUserVisaStatus( userLoginUserUserVisaStatus( userLogin ) );
        userDto.setUserId( userLogin.getUserId() );
        userDto.setUserLoginEmail( userLogin.getUserLoginEmail() );

        return userDto;
    }

    @Override
    public List<UserDto> toUserDTOs(List<UserLogin> userLogins) {
        if ( userLogins == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( userLogins.size() );
        for ( UserLogin userLogin : userLogins ) {
            list.add( toUserDto( userLogin ) );
        }

        return list;
    }

    private String userLoginUserUserFirstName(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }
        User user = userLogin.getUser();
        if ( user == null ) {
            return null;
        }
        String userFirstName = user.getUserFirstName();
        if ( userFirstName == null ) {
            return null;
        }
        return userFirstName;
    }

    private String userLoginUserUserLastName(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }
        User user = userLogin.getUser();
        if ( user == null ) {
            return null;
        }
        String userLastName = user.getUserLastName();
        if ( userLastName == null ) {
            return null;
        }
        return userLastName;
    }

    private String userLoginUserUserMiddleName(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }
        User user = userLogin.getUser();
        if ( user == null ) {
            return null;
        }
        String userMiddleName = user.getUserMiddleName();
        if ( userMiddleName == null ) {
            return null;
        }
        return userMiddleName;
    }

    private Long userLoginUserUserPhoneNumber(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }
        User user = userLogin.getUser();
        if ( user == null ) {
            return null;
        }
        Long userPhoneNumber = user.getUserPhoneNumber();
        if ( userPhoneNumber == null ) {
            return null;
        }
        return userPhoneNumber;
    }

    private String userLoginUserUserTimeZone(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }
        User user = userLogin.getUser();
        if ( user == null ) {
            return null;
        }
        String userTimeZone = user.getUserTimeZone();
        if ( userTimeZone == null ) {
            return null;
        }
        return userTimeZone;
    }

    private String userLoginUserUserLinkedinUrl(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }
        User user = userLogin.getUser();
        if ( user == null ) {
            return null;
        }
        String userLinkedinUrl = user.getUserLinkedinUrl();
        if ( userLinkedinUrl == null ) {
            return null;
        }
        return userLinkedinUrl;
    }

    private String userLoginUserUserLocation(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }
        User user = userLogin.getUser();
        if ( user == null ) {
            return null;
        }
        String userLocation = user.getUserLocation();
        if ( userLocation == null ) {
            return null;
        }
        return userLocation;
    }

    private String userLoginUserUserEduUg(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }
        User user = userLogin.getUser();
        if ( user == null ) {
            return null;
        }
        String userEduUg = user.getUserEduUg();
        if ( userEduUg == null ) {
            return null;
        }
        return userEduUg;
    }

    private String userLoginUserUserEduPg(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }
        User user = userLogin.getUser();
        if ( user == null ) {
            return null;
        }
        String userEduPg = user.getUserEduPg();
        if ( userEduPg == null ) {
            return null;
        }
        return userEduPg;
    }

    private String userLoginUserUserComments(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }
        User user = userLogin.getUser();
        if ( user == null ) {
            return null;
        }
        String userComments = user.getUserComments();
        if ( userComments == null ) {
            return null;
        }
        return userComments;
    }

    private String userLoginUserUserVisaStatus(UserLogin userLogin) {
        if ( userLogin == null ) {
            return null;
        }
        User user = userLogin.getUser();
        if ( user == null ) {
            return null;
        }
        String userVisaStatus = user.getUserVisaStatus();
        if ( userVisaStatus == null ) {
            return null;
        }
        return userVisaStatus;
    }
}
