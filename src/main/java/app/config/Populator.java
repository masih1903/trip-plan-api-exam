package app.config;

import app.daos.TripDAO;
import app.dtos.TripDTO;
import app.entities.Guide;
import app.entities.Trip;
import app.enums.Category;
import app.security.entities.Role;
import app.security.entities.User;
import dk.bugelhartmann.UserDTO;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Populator {

    private static EntityManagerFactory emf;
    private static TripDAO tripDAO;

    public Populator(EntityManagerFactory emf, TripDAO tripDAO) {
        Populator.emf = emf;
        Populator.tripDAO = tripDAO;
    }

    public List<TripDTO> populateTripsWithGuides() {

        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory is not initialized");
        }

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Create sample Guide objects
            Guide g1 = new Guide("John", "Doe", "john.doe@example.com", "123-456-7890", 10);
            Guide g2 = new Guide("Jane", "Smith", "jane.smith@example.com", "987-654-3210", 15);

            em.persist(g1);
            em.persist(g2);

            // Creating sample Trip objects and associating each with a Guide
            Trip t1 = new Trip(
                    LocalDateTime.of(2024, 12, 1, 9, 0),
                    LocalDateTime.of(2024, 12, 1, 17, 0),
                    "Paris",
                    "Cultural City Tour",
                    300.00,
                    Category.CULTURAL
            );
            t1.setGuide(g1);

            Trip t2 = new Trip(
                    LocalDateTime.of(2024, 7, 10, 15, 0),
                    LocalDateTime.of(2024, 7, 20, 10, 0),
                    "Maldives",
                    "Family Beach Vacation",
                    5000.00,
                    Category.FAMILY
            );
            t2.setGuide(g2);

            Trip t3 = new Trip(
                    LocalDateTime.of(2025, 1, 15, 6, 0),
                    LocalDateTime.of(2025, 1, 25, 18, 0),
                    "Himalayas",
                    "Adventure Mountain Trek",
                    1500.00,
                    Category.ADVENTURE
            );
            t3.setGuide(g1);

            // Persisting trips to the database
            em.persist(t1);
            em.persist(t2);
            em.persist(t3);

            em.getTransaction().commit();

            // Returning list of TripDTOs
            return new ArrayList<>(List.of(new TripDTO(t1), new TripDTO(t2), new TripDTO(t3)));
        }
    }

    public static UserDTO[] populateUsers() {

        User user, admin;
        Role userRole, adminRole;

        user = new User("usertest", "user123");
        admin = new User("admintest", "admin123");
        userRole = new Role("USER");
        adminRole = new Role("ADMIN");
        user.addRole(userRole);
        admin.addRole(adminRole);

        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory is not initialized");
        }

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.persist(admin);
            em.getTransaction().commit();
        }
        UserDTO userDTO = new UserDTO(user.getUsername(), "user123");
        UserDTO adminDTO = new UserDTO(admin.getUsername(), "admin123");
        return new UserDTO[]{userDTO, adminDTO};
    }


    public void cleanUp() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory is not initialized");
        }

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM User").executeUpdate();
            em.createQuery("DELETE FROM Role").executeUpdate();
            em.createQuery("DELETE FROM Trip").executeUpdate();
            em.createQuery("DELETE FROM Guide").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE trip_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE guide_id_seq RESTART WITH 1").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
