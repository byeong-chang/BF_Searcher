package softwareEngineering.bfSearcher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softwareEngineering.bfSearcher.Entity.Recruitment;

import java.util.List;
import java.util.Optional;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    @Override
    Optional<Recruitment> findById(Long aLong);
    Recruitment save(Recruitment recruitment);
    List<Recruitment> findTop5ByOrderByIdDesc();
    List<Recruitment> findByFlag(Long flag);
}
