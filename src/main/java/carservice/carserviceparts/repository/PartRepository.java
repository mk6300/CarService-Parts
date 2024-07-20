package carservice.carserviceparts.repository;

import carservice.carserviceparts.model.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
}
