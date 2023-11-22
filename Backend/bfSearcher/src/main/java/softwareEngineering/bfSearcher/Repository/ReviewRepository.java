package softwareEngineering.bfSearcher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softwareEngineering.bfSearcher.Entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    Review save(Review review);
}
