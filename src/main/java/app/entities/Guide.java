package app.entities;

import app.dtos.GuideDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private int yearsOfExperience;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    //@JsonManagedReference // Prevents infinite loop by managing this side of the relationship and shows trips also connected to guide
    @JsonIgnore
    private List<Trip> trips = new ArrayList<>();

    public Guide(String firstName, String lastName, String email, String phone, int yearsOfExperience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.yearsOfExperience = yearsOfExperience;
    }

    public Guide(GuideDTO guideDTO) {
        this.id = guideDTO.getId();
        this.firstName = guideDTO.getFirstName();
        this.lastName = guideDTO.getLastName();
        this.email = guideDTO.getEmail();
        this.phone = guideDTO.getPhone();
        this.yearsOfExperience = guideDTO.getYearsOfExperience();

    }
}
