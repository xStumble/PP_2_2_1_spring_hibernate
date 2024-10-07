package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car[] cars = {
              new Car("Model 1", 1),
              new Car("Model 2", 2),
              new Car("Model 3", 3),
              new Car("Model 4", 4),
      };

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", cars[0]));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", cars[1]));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", cars[2]));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", cars[3]));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      User user = userService.getUserByCar(cars[2].getModel(), cars[2].getSeries());
      System.out.println(user);

      context.close();
   }
}
