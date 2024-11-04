package app.daos;

import app.dtos.GuideDTO;
import app.entities.Guide;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class GuideDAO {

private final EntityManagerFactory emf;

    public GuideDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Guide getById(Integer id) {

        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Guide.class, id);
        }
    }

    public List<Guide> getAll() {

        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT g FROM Guide g", Guide.class).getResultList();
        }
    }

    public Guide create(GuideDTO guideDTO) {

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Guide guide = new Guide(guideDTO);
            em.persist(guide);
            em.getTransaction().commit();
            return guide;
        }
    }
}
