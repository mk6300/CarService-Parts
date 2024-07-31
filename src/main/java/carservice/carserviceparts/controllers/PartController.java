package carservice.carserviceparts.controllers;

import carservice.carserviceparts.model.dto.AddPartDTO;
import carservice.carserviceparts.model.dto.PartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/parts")
public interface PartController {

    @GetMapping
    ResponseEntity<List<PartDTO>> getAllParts();

    @GetMapping("/{id}")
    ResponseEntity<PartDTO> getPartById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<PartDTO> deletePart(@PathVariable Long id);

    @PostMapping("/add-part")
    ResponseEntity<PartDTO> addPart(@RequestBody AddPartDTO addPartDTO);

    @PutMapping("/edit-part")
    ResponseEntity<PartDTO> editPart(@RequestBody PartDTO partDTO);

    @DeleteMapping("/remove-all/{id}")
    ResponseEntity<PartDTO> deleteAllPartsFromSupplier(@PathVariable UUID id);


}
