package carservice.carserviceparts.service;

import carservice.carserviceparts.model.dto.AddPartDTO;
import carservice.carserviceparts.model.dto.PartDTO;

import java.util.List;
import java.util.Optional;

public interface PartService {
    List<PartDTO> getAllParts();

    PartDTO getPartById(Long id);

    void deletePart(Long id);

    PartDTO addPart (AddPartDTO addPartDTO);
}
