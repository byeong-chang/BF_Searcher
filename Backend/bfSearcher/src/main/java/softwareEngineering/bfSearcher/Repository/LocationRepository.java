package softwareEngineering.bfSearcher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softwareEngineering.bfSearcher.Entity.Location;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location,Long> {

    @Override
    List<Location> findAll();
}
