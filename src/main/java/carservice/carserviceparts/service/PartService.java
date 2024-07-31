package carservice.carserviceparts.service;

import carservice.carserviceparts.model.dto.AddPartDTO;
import carservice.carserviceparts.model.dto.PartDTO;

import java.util.List;
import java.util.UUID;


public interface PartService {
    List<PartDTO> getAllParts();

    PartDTO getPartById(Long id);

    void deletePart(Long id);

    PartDTO addPart (AddPartDTO addPartDTO);

    PartDTO editPart(PartDTO partDTO);

    void deleteAllPartsFromSupplier(UUID id);
}
