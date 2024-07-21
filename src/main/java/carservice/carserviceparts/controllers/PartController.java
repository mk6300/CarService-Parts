package carservice.carserviceparts.controllers;

import carservice.carserviceparts.model.dto.AddPartDTO;
import carservice.carserviceparts.model.dto.PartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
