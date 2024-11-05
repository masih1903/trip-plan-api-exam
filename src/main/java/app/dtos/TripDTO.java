package app.dtos;


import app.entities.Trip;
import app.enums.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class TripDTO {

    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;
    private String startPosition;
    private String name;
    private double price;
    private Category category;
    private List<PackingItemDTO> packingItems;


    public TripDTO(LocalDateTime startTime, LocalDateTime endTime, String startPosition, String name, double price, Category category) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.startPosition = startPosition;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public TripDTO(Trip trip) {
        this.id = trip.getId();
        this.startTime = trip.getStartTime();
        this.endTime = trip.getEndTime();
        this.startPosition = trip.getStartPosition();
        this.name = trip.getName();
        this.price = trip.getPrice();
        this.category = trip.getCategory();

    }

    public static List<TripDTO> toTripDTOList(List<Trip> trips) {
        return trips.stream().map(TripDTO::new).toList();
    }
}

