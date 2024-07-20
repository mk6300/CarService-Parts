package carservice.carserviceparts.service;

import carservice.carserviceparts.model.dto.PartDTO;

import java.util.List;

public interface PartService {
    List<PartDTO> getAllParts();
}
