package gov.iti.jets.persistence.util.mappers;

import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.presentation.dtos.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-04T19:12:40+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.13 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDtoToEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setBirthDate( userDto.getBirthDate() );
        user.setUserType( userDto.getUserType() );
        user.setUserName( userDto.getUserName() );
        user.setEmail( userDto.getEmail() );
        user.setCountry( userDto.getCountry() );
        user.setCity( userDto.getCity() );
        user.setDetailedAddress( userDto.getDetailedAddress() );
        user.setPhoneNumber( userDto.getPhoneNumber() );
        user.setWallet( userDto.getWallet() );
        user.setPassword( userDto.getPassword() );

        return user;
    }

    @Override
    public UserDto userToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setBirthDate( user.getBirthDate() );
        userDto.setUserType( user.getUserType() );
        userDto.setUserName( user.getUserName() );
        userDto.setEmail( user.getEmail() );
        userDto.setCountry( user.getCountry() );
        userDto.setCity( user.getCity() );
        userDto.setDetailedAddress( user.getDetailedAddress() );
        userDto.setPhoneNumber( user.getPhoneNumber() );
        userDto.setWallet( user.getWallet() );
        userDto.setPassword( user.getPassword() );

        return userDto;
    }
}
