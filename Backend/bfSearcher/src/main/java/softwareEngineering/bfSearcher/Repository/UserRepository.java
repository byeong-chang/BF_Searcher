package softwareEngineering.bfSearcher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softwareEngineering.bfSearcher.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
