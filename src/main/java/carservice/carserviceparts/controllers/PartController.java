package carservice.carserviceparts.controllers;

import carservice.carserviceparts.model.dto.AddPartDTO;
import carservice.carserviceparts.model.dto.PartDTO;
import carservice.carserviceparts.service.PartService;
import carservice.carserviceparts.service.exception.PartNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/parts")
public class PartController {

    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }


    @GetMapping
    public ResponseEntity<List<PartDTO>> getAllParts() {
        return ResponseEntity.ok(
                partService.getAllParts());
    }

    @GetMapping("/{id}")
    ResponseEntity<PartDTO> getPartById(@PathVariable Long id) {
        return ResponseEntity.ok(partService.getPartById(id));
    }


    @DeleteMapping("/{id}")
    ResponseEntity<PartDTO> deletePart(@PathVariable Long id) {
        partService.deletePart(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/add-part")
    ResponseEntity<PartDTO> addPart(@RequestBody AddPartDTO addPartDTO) {

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

    @PutMapping("/edit-part")
    ResponseEntity<PartDTO> editPart(@RequestBody PartDTO partDTO) {
        return ResponseEntity.ok(partService.editPart(partDTO));
    }


    @DeleteMapping("/remove-all/{id}")
    ResponseEntity<PartDTO> deleteAllPartsFromSupplier(@PathVariable UUID id) {
        partService.deleteAllPartsFromSupplier(id);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PartNotFoundException.class)
    @ResponseBody
    public NotFoundErrorInfo handleApiObjectNotFoundException( PartNotFoundException partNotFoundException) {
        return new NotFoundErrorInfo("Part not found");
    }
    public record NotFoundErrorInfo(String code) {

    }

}
