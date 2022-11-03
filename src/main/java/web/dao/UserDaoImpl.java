package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao{

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Transactional
    @Override
    public void saveUser(String name, String lastName, int age) {
        sessionFactory.getCurrentSession().save(new User(name, lastName, age));

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
        return  sessionFactory.getCurrentSession().get(User.class, id);
    }
    @Transactional
    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(User.class, id));
    }
}
