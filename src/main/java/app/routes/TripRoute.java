package app.routes;

import app.config.HibernateConfig;
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
    private final TripController tripController = new TripController(tripDAO);

    public EndpointGroup getTripRoutes() {
        return () ->
        {
            get("/{id}", tripController::getById, Role.ANYONE);
            get("/", tripController::getAll, Role.ANYONE );
            post("/", tripController::create, Role.ANYONE);
            put("/{id}", tripController::update, Role.ANYONE);
            delete("/{id}", tripController::delete, Role.ANYONE);
            put("/{tripId}/guides/{guideId}", tripController::addGuideToTrip, Role.ANYONE);
            get("/guides/{guideId}/trips", tripController::getTripsByGuide, Role.ANYONE);
            get("/categories/{category}", tripController::getTripsByCategory, Role.ANYONE);

        };
    }
}
