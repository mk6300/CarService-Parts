package carservice.carserviceparts.controllers;

import carservice.carserviceparts.model.dto.PartDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/offers")
public interface PartController {

    @GetMapping
    List<PartDTO> getAllParts();



}
