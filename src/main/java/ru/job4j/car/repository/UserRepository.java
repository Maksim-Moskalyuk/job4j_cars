package ru.job4j.car.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.Collection;
import org.hibernate.query.Query;
import ru.job4j.car.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createNativeQuery(
                            "INSERT INTO auto_user (login, password) VALUES (:login, :password)")
                    .setParameter("login", user.getLogin())
                    .setParameter("password", user.getPassword())
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            User user = new User();
            user.setId(userId);
            session.delete(user);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        try (Session session = sf.openSession();) {
            Query<User> query = session.createQuery("from User u Order by u.id");
            return query.getResultList();
        }
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int id) {
        try (Session session = sf.openSession()) {
            Query<User> query = session.createQuery("From User Where id=:id");
            query.setParameter("id", id);
            return query.uniqueResultOptional();
        }
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        try (Session session = sf.openSession()) {
            Query<User> query = session.createQuery("From User Where login like :login");
            query.setParameter("login", "%"+ key +"%");
            return query.getResultList();
        }
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        try (Session session = sf.openSession()) {
            Query<User> query = session.createQuery("from User where login = :login", User.class);
            query.setParameter("login", login).setMaxResults(1);
            return query.uniqueResultOptional();
        }
    }
}