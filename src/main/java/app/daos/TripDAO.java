package app.daos;

import app.dtos.TripDTO;
import app.entities.Guide;
import app.entities.Trip;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TripDAO implements IDAO<Trip, TripDTO>, ITripGuideDAO {

    private final EntityManagerFactory emf;

    public TripDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Trip getById(Integer id) {

        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Trip.class, id);
        }
    }

    @Override
    public List<Trip> getAll() {

        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT t FROM Trip t", Trip.class).getResultList();
        }
    }

    @Override
    public Trip create(TripDTO tripDTO) {

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Trip trip = new Trip(tripDTO);
            em.persist(trip);
            em.getTransaction().commit();
            return trip;
        }
    }

    @Override
    public Trip update(Integer id, TripDTO tripDTO) {

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Trip trip = em.find(Trip.class, id);
            trip.setStartTime(tripDTO.getStartTime());
            trip.setEndTime(tripDTO.getEndTime());
            trip.setStartPosition(tripDTO.getStartPosition());
            trip.setName(tripDTO.getName());
            trip.setPrice(tripDTO.getPrice());
            trip.setCategory(tripDTO.getCategory());
            em.merge(trip);
            em.getTransaction().commit();
            return trip;
        }
    }

    @Override
    public void delete(Integer id) {

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Trip trip = em.find(Trip.class, id);
            em.remove(trip);
            em.getTransaction().commit();
        }
    }

    @Override
    public void addGuideToTrip(int tripId, int guideId) {

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Trip trip = em.find(Trip.class, tripId);
            Guide guide = em.find(Guide.class, guideId);
            if (trip != null && guide != null) {
                trip.setGuide(guide);
                em.merge(trip);
            }
            em.getTransaction().commit();
        }
    }

    @Override
    public Set<TripDTO> getTripsByGuide(int guideId) {
        try (EntityManager em = emf.createEntityManager()) {
            List<Trip> trips = em.createQuery("SELECT t FROM Trip t WHERE t.guide.id = :guideId", Trip.class)
                    .setParameter("guideId", guideId)
                    .getResultList();
            return trips.stream().map(TripDTO::new).collect(Collectors.toSet());
        }
    }

}

