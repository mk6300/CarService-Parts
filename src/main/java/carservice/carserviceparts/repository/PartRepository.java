package carservice.carserviceparts.repository;

import carservice.carserviceparts.model.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

     void deleteAllBySupplierId(UUID id);
}
