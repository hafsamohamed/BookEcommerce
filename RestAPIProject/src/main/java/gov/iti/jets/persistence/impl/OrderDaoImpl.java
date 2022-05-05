package gov.iti.jets.persistence.impl;

import gov.iti.jets.persistence.OrderDao;
import gov.iti.jets.persistence.entities.Order;
import gov.iti.jets.persistence.util.ManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private final static EntityManagerFactory entityManagerFactory = ManagerFactory.getEntityManagerFactory();
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public List<Order> getAllOrders() {
        TypedQuery<Order> query = entityManager.createQuery("select m from Order m", Order.class);
        List<Order> messages = query.getResultList();
        return messages;
    }

    @Override
    public List<Order> getOrderByUserId(int id) {
        CriteriaBuilder cb = entityManagerFactory.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        Root<Order> userOrder = query.from(Order.class);
        Predicate predicate = cb.equal(userOrder.get("user").<Integer>get("id"), id);
        query.select(userOrder).where(predicate);
        List<Order> result = entityManager.createQuery(query).getResultList();
        return result;
    }

    @Override
    public void saveOrder(Order order) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(order);
        transaction.commit();
    }

    @Override
    public boolean deleteOrder(int userId, int orderId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String select = "SELECT p FROM Order p where p.id=:orderId and p.user.id=:userId";
        Query query = entityManager.createQuery(select);
        query.setParameter("userId", userId);
        query.setParameter("orderId", orderId);
        List<Order> cartProducts = query.getResultList();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(cartProducts.get(0));
        transaction.commit();
        entityManager.close();
        return true;
    }

    @Override
    public Order getOrderById(int userId, int orderId) {
        Order order = null;
        try {
            Query query = entityManager
                    .createQuery("SELECT p FROM Order p where p.id=:orderId and p.user.id=:userId");
            query.setParameter("userId", userId);
            query.setParameter("orderId", orderId);
            order = (Order) query.getSingleResult();
        } catch (NoResultException nre) {
        }
        return order;
    }

}
