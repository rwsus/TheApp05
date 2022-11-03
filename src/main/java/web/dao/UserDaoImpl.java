package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("select a from User a", User.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void saveUser(String name, String lastName, int age) {
        entityManager.persist(new User(name, lastName, age));
    }

    @Transactional
    @Override
    public void updateUser(long id, User updatedUser) {
        User userToBeUpdated = findUserById(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setLastname(updatedUser.getLastname());
        userToBeUpdated.setAge(updatedUser.getAge());
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
