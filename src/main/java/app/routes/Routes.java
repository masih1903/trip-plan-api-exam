package app.routes;

import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {

    private TripRoute tripRoutes = new TripRoute();
    private GuideRoute guideRoutes = new GuideRoute();

    public EndpointGroup getApiRoutes() {
        return () ->
        {

            path("/trips", tripRoutes.getTripRoutes());
            path("/guides", guideRoutes.getGuideRoutes());


        };
    }
}
