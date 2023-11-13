package softwareEngineering.bfSearcher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softwareEngineering.bfSearcher.Entity.MatchingLog;

public interface MatchingLogRepository extends JpaRepository<MatchingLog , Long> {

    MatchingLog save(MatchingLog matchingLog);
}
