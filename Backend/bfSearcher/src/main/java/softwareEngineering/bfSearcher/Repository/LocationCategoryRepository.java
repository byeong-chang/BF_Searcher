package softwareEngineering.bfSearcher.Repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import softwareEngineering.bfSearcher.Entity.LocationCategory;

import java.util.Optional;
import java.util.function.Function;

public interface LocationCategoryRepository extends JpaRepository<LocationCategory, Long> {


    LocationCategory findByLocation(String location);
}
