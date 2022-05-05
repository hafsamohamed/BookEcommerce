package gov.iti.jets.persistence.impl;

import gov.iti.jets.persistence.UserDao;
import gov.iti.jets.persistence.entities.User;
import gov.iti.jets.persistence.util.ManagerFactory;
import gov.iti.jets.presentation.dtos.UserDto;
import jakarta.persistence.*;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private final static EntityManagerFactory entityManagerFactory = ManagerFactory.getEntityManagerFactory();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public boolean checkEmail(String email) {
        List<User> userList = entityManager.createQuery("select e from User e where e.email = ?1")
                .setParameter(1, email)
                .getResultList();
        return userList.size() > 0;
    }

    @Override
    public boolean addUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        return true;
    }

    @Override
    public User login(String email, String password) throws NoResultException {
        User user = null;
        try {
            Query query = entityManager.createQuery("SELECT user FROM User user WHERE user.email=:email");
            query.setParameter("email", email);
            user = (User) query.getSingleResult();
        } catch (NoResultException nre) {
        }
        return user;
    }

    @Override
    public User getUser(int id) {
        User user = null;
        try {
            user = entityManager.find(User.class, id);
        } catch (NoResultException nre) {
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = entityManager.createQuery("select m from User m", User.class);
        List<User> messages = query.getResultList();
        return messages;
    }

    @Override
    public boolean updateUser(UserDto user, int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String select = "SELECT user FROM User user WHERE user.id=:id";

        Query query = entityManager.createQuery(select);
        query.setParameter("id", id);

        User updateduser = (User) query.getSingleResult();

        updateduser.setBirthDate(user.getBirthDate());
        updateduser.setEmail(user.getEmail());
        updateduser.setPhoneNumber(user.getPhoneNumber());
        updateduser.setUserName(user.getUserName());
        updateduser.setDetailedAddress(user.getDetailedAddress());
        updateduser.setCity(user.getCity());
        updateduser.setCountry(user.getCountry());
        updateduser.setUserType(user.getUserType());
        updateduser.setPassword(user.getPassword());
        updateduser.setWallet(user.getWallet());

        entityManager.persist(updateduser);
        transaction.commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean removeUser(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String select = "SELECT p FROM User p where p.id=:id";
        Query query = entityManager.createQuery(select);
        query.setParameter("id", id);
        List<User> users = query.getResultList();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(users.get(0));
        transaction.commit();
        entityManager.close();
        return true;
    }
}
