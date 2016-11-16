package com.lauparr;

import com.lauparr.model.Customer;
import com.lauparr.model.Project;
import com.lauparr.model.User;
import com.lauparr.repository.CustomerRepository;
import com.lauparr.repository.ProjectRepository;
import com.lauparr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class AdminRepoApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(AdminRepoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        customerRepository.deleteAll();
        projectRepository.deleteAll();
        userRepository.deleteAll();

        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Smith"));
        projectRepository.save(new Project("my-ui", "Mon UI personnalisée", "https://github.com/lparrot/my-ui"));

        User user = new User();
        user.setEmail("laurent.parrot78@gmail.com");
        user.setPassword("123");
        user.setBirthday(LocalDate.of(1983, 9, 5));
        user.setNom("PARROT");
        user.setPrenom("Laurent");
        userRepository.save(user);

        System.out.println("Customers found with findAll()");
        System.out.println("------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        System.out.println("Customers found with findByFirstName('Alice'):");
        System.out.println("------------------------------");
        System.out.println(customerRepository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("------------------------------");
        for (Customer customer : customerRepository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }
}
