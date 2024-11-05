package app.controllers;

import app.config.Populator;
import app.daos.TripDAO;
import app.dtos.PackingItemDTO;
import app.dtos.PackingListDTO;
import app.dtos.TripDTO;
import app.entities.Trip;
import app.enums.Category;
import app.exceptions.ApiException;
import app.services.PackingService;
import io.javalin.http.Context;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TripController implements IController {

    private final TripDAO tripDAO;
    private final Populator populator;

    public TripController(TripDAO tripDAO, Populator populator) {
        this.tripDAO = tripDAO;
        this.populator = populator;
    }

    @Override
    public void getById(Context ctx) {

        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Trip trip = tripDAO.getById(id);

            if (trip == null) {
                throw new ApiException(404, "Trip with ID " + id + " not found");
            }

            TripDTO tripDTO = new TripDTO(trip);
            // Fetch packing items based on the trip's category
            List<PackingItemDTO> packingItems = fetchPackingItemsByCategory(trip.getCategory().name().toLowerCase());
            // Set packing items in TripDTO
            tripDTO.setPackingItems(packingItems);
            // Respond with the enriched TripDTO
            ctx.res().setStatus(200);
            ctx.json(tripDTO, TripDTO.class);

        } catch (NumberFormatException e) {
            throw new ApiException(400, "Invalid ID format. Please provide a numeric ID.");
        } catch (Exception e) {
            throw new ApiException(500, "Error fetching trip or packing items: " + e.getMessage());
        }
    }

    @Override
    public void getAll(Context ctx) {

        List<Trip> trips = tripDAO.getAll();
        if (trips.isEmpty()) {
            throw new ApiException(404, "No trips found.");
        }
        List<TripDTO> tripDTOs = TripDTO.toTripDTOList(trips);
        ctx.status(200).json(tripDTOs, TripDTO.class);


    }

    @Override
    public void create(Context ctx) {

        try {
            TripDTO tripDTO = ctx.bodyAsClass(TripDTO.class);
            Trip trip = tripDAO.create(tripDTO);
            ctx.status(201).json(new TripDTO(trip));
        } catch (Exception e) {
            throw new ApiException(500, "An error occurred while creating the trip. Please try again later.");
        }

    }

    @Override
    public void update(Context ctx) {

        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            TripDTO tripDTO = ctx.bodyAsClass(TripDTO.class);
            Trip updatedTrip = tripDAO.update(id, tripDTO);
            if (updatedTrip == null) {
                throw new ApiException(404, "Trip with ID " + id + " not found");
            }
            ctx.status(200).json(updatedTrip, TripDTO.class);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Invalid ID format. Please provide a numeric ID.");
        }

    }

    @Override
    public void delete(Context ctx) {

        try {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Trip trip = tripDAO.getById(id);
            if (trip == null) {
                throw new ApiException(404, "Trip with ID " + id + " not found");
            }
            tripDAO.delete(id);
            ctx.status(204);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Invalid ID format. Please provide a numeric ID.");
        }
    }

    public void addGuideToTrip(Context ctx) {
        try {
            Integer tripId = Integer.parseInt(ctx.pathParam("tripId"));
            Integer guideId = Integer.parseInt(ctx.pathParam("guideId"));

            tripDAO.addGuideToTrip(tripId, guideId);
            ctx.status(200).result("Guide with ID " + guideId + " added to Trip with ID " + tripId);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Invalid ID format. Please provide numeric IDs.");
        } catch (Exception e) {
            throw new ApiException(500, "An error occurred while adding the guide to the trip.");
        }
    }

    public void getTripsByGuide(Context ctx) {
        try {
            Integer guideId = Integer.parseInt(ctx.pathParam("guideId"));
            Set<TripDTO> trips = tripDAO.getTripsByGuide(guideId);

            if (trips.isEmpty()) {
                throw new ApiException(404, "No trips found for guide with ID " + guideId);
            }
            ctx.status(200).json(trips);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Invalid ID format. Please provide a numeric ID.");
        }
    }

    public void getTripsByCategory(Context ctx) {
        try {
            String categoryParam = ctx.pathParam("category");
            Category category = Category.valueOf(categoryParam.toUpperCase());

            // Filter trips by the specified category
            List<Trip> trips = tripDAO.getAll().stream()
                    .filter(trip -> trip.getCategory() == category)
                    .collect(Collectors.toList());

            // Convert the filtered trips to DTOs
            List<TripDTO> tripDTOs = trips.stream().map(TripDTO::new).collect(Collectors.toList());

            // Return the filtered trips as JSON
            ctx.status(200).json(tripDTOs);
        } catch (IllegalArgumentException e) {
            throw new ApiException(400, "Invalid category specified.");
        } catch (Exception e) {
            throw new ApiException(500, "An error occurred while retrieving trips by category.");
        }
    }

    public void getPackingItemsTotalWeight(Context ctx) {
        int tripId = Integer.parseInt(ctx.pathParam("id"));
        Trip trip = tripDAO.getById(tripId);

        if (trip != null) {
            String category = trip.getCategory().toString().toLowerCase();
            PackingListDTO packingListDTO = PackingService.getPackingItemsByCategory(category);

            if (packingListDTO != null) {
                int totalWeight = packingListDTO.getItems().stream()
                        .mapToInt(item -> item.getWeightInGrams() * item.getQuantity())
                        .sum();

                ctx.json(totalWeight);
            } else {
                ctx.status(404).result("Packing items not found for this category");
            }
        } else {
            ctx.status(404).result("Trip not found");
        }
    }

    public void populateDB(Context ctx) {
        populator.populateTripsWithGuides();
        ctx.res().setStatus(200);
        ctx.result("Database populated with sample trips and guides.");
    }

    // Method to fetch packing items by category
    private List<PackingItemDTO> fetchPackingItemsByCategory(String category) {
        return PackingService.getPackingItemsByCategory(category).getItems();
    }
}



