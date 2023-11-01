package softwareEngineering.bfSearcher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softwareEngineering.bfSearcher.Entity.Location;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {

    @Override
    List<Location> findAll();
}
