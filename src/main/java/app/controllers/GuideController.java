package app.controllers;

import app.daos.GuideDAO;
import app.dtos.GuideDTO;
import app.dtos.GuideOverviewDTO;
import app.entities.Guide;
import app.entities.Trip;
import app.exceptions.ApiException;
import io.javalin.http.Context;

import java.util.List;
import java.util.stream.Collectors;

public class GuideController {

    private final GuideDAO guideDAO;

    public GuideController(GuideDAO guideDAO) {
        this.guideDAO = guideDAO;
    }

    public void getAll(Context ctx) {

        List<Guide> guides = guideDAO.getAll();
        if (guides.isEmpty()) {
            throw new ApiException(404, "No guides found.");
        }
        List<GuideDTO> guideDTOs = GuideDTO.toGuideDTOList(guides);
        ctx.status(200).json(guideDTOs, GuideDTO.class);
    }

    public void create(Context ctx) {

        try {
            GuideDTO guideDTO = ctx.bodyAsClass(GuideDTO.class);
            Guide guide = guideDAO.create(guideDTO);
            ctx.status(201).json(new GuideDTO(guide));
        } catch (Exception e) {
            throw new ApiException(500, "An error occurred while creating the guide. Please try again later.");
        }

    }

    public void delete(Context ctx) {
        Integer id = Integer.parseInt(ctx.pathParam("id"));
        Guide guide = guideDAO.getById(id);
        if (guide == null) {
            throw new ApiException(404, "Guide not found.");
        }
        guideDAO.delete(id);
        ctx.status(204);
    }

    public void getGuideTripOverview(Context ctx) {
        List<GuideDTO> overview = guideDAO.getAll().stream()
                .map(GuideDTO::new)
                .collect(Collectors.toList());

        ctx.status(200).json(overview);
    }

    public void getGuideOverviewTotalPrice(Context ctx) {
        List<Guide> guides = guideDAO.getAll();

        // Map each guide to GuideOverviewDTO with only guideId and totalPrice
        List<GuideOverviewDTO> overview = guides.stream()
                .map(guide -> new GuideOverviewDTO(
                        guide.getId(),
                        guide.getTrips().stream().mapToDouble(Trip::getPrice).sum()
                ))
                .collect(Collectors.toList());

        ctx.status(200).json(overview);
    }

}
