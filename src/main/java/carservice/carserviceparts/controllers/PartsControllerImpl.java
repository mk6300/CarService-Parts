package carservice.carserviceparts.controllers;

import carservice.carserviceparts.model.dto.AddPartDTO;
import carservice.carserviceparts.model.dto.PartDTO;
import carservice.carserviceparts.service.PartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PartsControllerImpl implements PartController {
    private final PartService partService;

    public PartsControllerImpl(PartService partService) {
        this.partService = partService;
    }

    @Override
    public ResponseEntity<List<PartDTO>> getAllParts() {
        return ResponseEntity.ok(partService.getAllParts());
    }

    @Override
    public ResponseEntity<PartDTO> getPartById(Long id) {
        return ResponseEntity.ok(partService.getPartById(id));
    }

    @Override
    public ResponseEntity<PartDTO> deletePart(Long id) {
        partService.deletePart(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PartDTO> addPart(AddPartDTO addPartDTO) {
        return ResponseEntity.ok(partService.addPart(addPartDTO));
    }
}
