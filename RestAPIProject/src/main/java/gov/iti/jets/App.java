package gov.iti.jets;

import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.services.UserService;
import gov.iti.jets.services.impl.UserServiceImpl;

/**
 * Hello world!
 *
 */

public class App 
{
    public static UserService userService = new UserServiceImpl();

    public static void main( String[] args )
    {

        List<GetUserDto> users = new ArrayList<>();
        userService.getUsers().forEach(user -> users.add(getUserDtoWithoutId(user)));
        users.forEach(user -> System.out.println(user.toString()));

    }
    private static GetUserDto getUserDtoWithoutId(User user) {
        GetUserDto userDto = new GetUserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setCartProductsList(user.getCartProductsList());
        userDto.setUserName(user.getUserName());
        userDto.setDetailedAddress(user.getDetailedAddress());
        userDto.setCity(user.getCity());
        userDto.setCountry(user.getCountry());
        userDto.setOrderList(user.getOrderList());
        userDto.setUserType(user.getUserType());
        userDto.setPassword(user.getPassword());
        userDto.setWallet(user.getWallet());
        return userDto;
    }
}
