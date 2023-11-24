package softwareEngineering.bfSearcher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softwareEngineering.bfSearcher.Entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    Review save(Review review);

    List<Review> findByLocationId(int location_id);
}
