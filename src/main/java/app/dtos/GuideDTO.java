package app.dtos;

import app.entities.Guide;
import app.entities.Trip;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
public class GuideDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int yearsOfExperience;
    private List<Trip> trips;
    private Double totalPrice;






    public GuideDTO(Guide guide) {
        this.id = guide.getId();
        this.firstName = guide.getFirstName();
        this.lastName = guide.getLastName();
        this.email = guide.getEmail();
        this.phone = guide.getPhone();
        this.yearsOfExperience = guide.getYearsOfExperience();
        this.trips = guide.getTrips();
        this.totalPrice = guide.getTrips().stream().mapToDouble(Trip::getPrice).sum();
    }

    public static List<GuideDTO> toGuideDTOList(List<Guide> guides) {
        return guides.stream().map(GuideDTO::new).toList();
    }
}
