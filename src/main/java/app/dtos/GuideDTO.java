package app.dtos;

import app.entities.Guide;
import app.entities.Trip;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class GuideDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int yearsOfExperience;
    @JsonIgnore
    private List<TripDTO> trips;
    private Double totalPrice;


    public GuideDTO(Guide guide) {
        this.id = guide.getId();
        this.firstName = guide.getFirstName();
        this.lastName = guide.getLastName();
        this.email = guide.getEmail();
        this.phone = guide.getPhone();
        this.yearsOfExperience = guide.getYearsOfExperience();
        this.totalPrice = calculateTotalPrice(guide.getTrips());

        // Convert each Trip entity to a TripDTO and store in the list
        this.trips = guide.getTrips().stream()
                .map(TripDTO::new)
                .collect(Collectors.toList());
    }


    private double calculateTotalPrice(List<Trip> trips) {
        return trips.stream().mapToDouble(Trip::getPrice).sum();
    }


    public static List<GuideDTO> toGuideDTOList(List<Guide> guides) {
        return guides.stream().map(GuideDTO::new).toList();
    }
}
