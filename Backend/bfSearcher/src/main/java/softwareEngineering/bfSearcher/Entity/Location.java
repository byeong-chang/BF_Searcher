package softwareEngineering.bfSearcher.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_name")
    private String locationName;

    @ManyToOne
    @JoinColumn(name = "hobby_category_id")
    private HobbyCategory hobbyCategory;

    @ManyToOne
    @JoinColumn(name = "location_category_id")
    private LocationCategory locationCategory;

    private Double latitude;
    private Double longitude;
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;
    private String homepage;

    @Column(name = "closed_day")
    private String closedDay;

    private String runtime;

    @Column(name = "free_park")
    private Integer freePark;

    @Column(name = "paid_park")
    private Integer paidPark;

    @Column(name = "door_for_disabled")
    private Integer doorForDisabled;

    @Column(name = "wheelchair_rental")
    private Integer wheelchairRental;

    @Column(name = "toilet_for_disabled")
    private Integer toiletForDisabled;

    @Column(name = "park_for_disabled")
    private Integer parkForDisabled;

    @Column(name = "big_park")
    private Integer bigPark;

    @Column(name = "guideDogPermitted")
    private Integer guideDogPermitted;

    @Column(name = "braille_guide")
    private Integer brailleGuide;

    @Column(name = "audio_guide")
    private Integer audioGuide;

    @Column(name = "star_rating")
    private Double starRating;
}
