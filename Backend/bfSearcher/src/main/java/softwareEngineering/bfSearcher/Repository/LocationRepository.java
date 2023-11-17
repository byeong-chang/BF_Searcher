package softwareEngineering.bfSearcher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softwareEngineering.bfSearcher.Entity.Location;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

    @Override
    Optional<Location> findById(Long aLong);

    @Override
    List<Location> findAll();

    List<Location> findByBrailleGuideOrAudioGuideOrGuideDogPermitted(int brailleGuide, int audioGuide, int guideDogPermitted);
    List<Location> findByParkForDisabledOrToiletForDisabledOrWheelchairRental(int park_for_disabled, int toilet_for_disabled, int wheelchair_rental);
    List<Location> findByDoorForDisabledOrToiletForDisabled(int door_for_disabled, int toilet_for_disabled);

}
