package gov.iti.jets.services;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.presentation.dtos.UserDto;
import jakarta.persistence.NoResultException;

import java.util.List;

public interface UserService {
    boolean checkEmail(String email);
    boolean addUser (User user);
    User getUser (int id);
    List<User> getUsers ();
    User login(String email, String password) throws NoResultException;
    boolean updateUser(UserDto user, int id);
    boolean removeUser(int id);

}
