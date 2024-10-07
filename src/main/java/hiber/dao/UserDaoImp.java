package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      TypedQuery<Car> query = sessionFactory.getCurrentSession()
              .createQuery("from Car where model = :model and series = :series", Car.class);
      query.setParameter("model", model);
      query.setParameter("series", series);

      return getUserByCar(query.getSingleResult());
   }

   @Override
   public User getUserByCar(Car car) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("from User where car.id = :car", User.class);
      query.setParameter("car", car.getId());

      return query.getSingleResult();
   }

}
