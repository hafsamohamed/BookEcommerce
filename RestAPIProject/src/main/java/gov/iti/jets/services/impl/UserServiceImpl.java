package gov.iti.jets.services.impl;

import gov.iti.jets.persistence.UserDao;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.impl.UserDaoImpl;
import gov.iti.jets.presentation.dtos.UserDto;
import gov.iti.jets.services.UserService;
import jakarta.persistence.NoResultException;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public boolean checkEmail(String email) {
        UserDao userDao = new UserDaoImpl();
        return userDao.checkEmail(email);
    }

    @Override
    public boolean addUser(User user) {
        UserDao userDao = new UserDaoImpl();
        return userDao.addUser(user);
    }

    @Override
    public User getUser(int id) {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUser(id);
    }

    @Override
    public List<User> getUsers() {
        UserDao userDao = new UserDaoImpl();
        return userDao.getUsers();
    }

    @Override
    public User login(String email, String password) throws NoResultException {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.login(email, password);
        return user;
    }

    @Override
    public boolean updateUser(UserDto user, int id) {
        UserDao userDao = new UserDaoImpl();
        return userDao.updateUser(user, id);
    }

    @Override
    public boolean removeUser(int id) {
        UserDao userDao = new UserDaoImpl();
        return userDao.removeUser(id);
    }

}
