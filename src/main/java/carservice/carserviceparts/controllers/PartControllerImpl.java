package carservice.carserviceparts.controllers;

import carservice.carserviceparts.model.dto.AddPartDTO;
import carservice.carserviceparts.model.dto.PartDTO;
import carservice.carserviceparts.service.PartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
public class PartControllerImpl implements PartController {
    private final PartService partService;

    public PartControllerImpl(PartService partService) {
        this.partService = partService;
    }

    @Override
    public ResponseEntity<List<PartDTO>> getAllParts() {

        return ResponseEntity.ok(
                partService.getAllParts()
        );
    }

    @Override
    public ResponseEntity<PartDTO> getPartById(Long id) {
        return ResponseEntity.ok(partService.getPartById(id));
    }

    @Override
    public ResponseEntity<PartDTO> deletePart(Long id) {
        partService.deletePart(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PartDTO> addPart(AddPartDTO addPartDTO) {

        PartDTO partDTO = partService.addPart(addPartDTO);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(partDTO.getId())
                                .toUri()
                )
                .body(partDTO);
    }

    @Override
    public ResponseEntity<PartDTO> editPart(PartDTO partDTO) {
        return ResponseEntity.ok(partService.editPart(partDTO));
    }

    @Override
    public ResponseEntity<PartDTO> deleteAllPartsFromSupplier(UUID id) {
        partService.deleteAllPartsFromSupplier(id);
        return ResponseEntity.ok().build();
    }
}
