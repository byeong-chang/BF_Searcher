package softwareEngineering.bfSearcher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softwareEngineering.bfSearcher.Entity.DisabledCategory;

public interface DisabledCategoryRepository extends JpaRepository<DisabledCategory,Integer> {

    DisabledCategory findByDisabled(String disabled);
}
