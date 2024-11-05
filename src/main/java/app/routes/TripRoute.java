package app.routes;

import app.config.HibernateConfig;
import app.config.Populator;
import app.controllers.TripController;
import app.daos.TripDAO;
import app.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

public class TripRoute {

    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private final TripDAO tripDAO = new TripDAO(emf);
    private final Populator populator = new Populator(emf, tripDAO);
    private final TripController tripController = new TripController(tripDAO, populator);

    public EndpointGroup getTripRoutes() {
        return () ->
        {
            get("/{id}", tripController::getById, Role.ANYONE);
            get("/", tripController::getAll, Role.ANYONE);
            post("/", tripController::create, Role.ADMIN);
            put("/{id}", tripController::update, Role.ADMIN);
            delete("/{id}", tripController::delete, Role.ADMIN);

            put("/{tripId}/guides/{guideId}", tripController::addGuideToTrip, Role.ADMIN);
            get("/guides/{guideId}/trips", tripController::getTripsByGuide, Role.ANYONE);
            get("/categories/{category}", tripController::getTripsByCategory, Role.ANYONE);
            post("/populate", tripController::populateDB, Role.ADMIN);
            get("/{id}/packing-items/total-weight", tripController::getPackingItemsTotalWeight, Role.ANYONE);

        };
    }
}
