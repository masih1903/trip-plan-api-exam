package app.routes;

import app.config.HibernateConfig;
import app.controllers.GuideController;
import app.daos.GuideDAO;
import app.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class GuideRoute {

    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private final GuideDAO guideDAO = new GuideDAO(emf);
    private final GuideController guideController = new GuideController(guideDAO);

    public EndpointGroup getGuideRoutes() {
        return () ->
        {

            post("/", guideController::create, Role.ADMIN);
            get("/", guideController::getAll, Role.ANYONE);
            get("/overview", guideController::getGuideTripOverview, Role.ANYONE);
            delete("/{id}", guideController::delete, Role.ADMIN);
            get("/totalprice", guideController::getGuideOverviewTotalPrice, Role.ANYONE);


        };
    }
}
