package az.gov.mia.grp.repository.medal;

import az.gov.mia.grp.entity.medal.MedalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedalRepository extends JpaRepository<MedalEntity, Long> {
    List<MedalEntity> getAllByIdNotNullOrderById();

    Optional<MedalEntity> findById(long id);

    boolean existsByNameIgnoreCase(String name);

    MedalEntity findByNameIgnoreCase(String name);
}
