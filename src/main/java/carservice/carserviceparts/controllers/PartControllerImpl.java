package carservice.carserviceparts.controllers;



import carservice.carserviceparts.model.dto.PartDTO;
import carservice.carserviceparts.service.PartService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PartControllerImpl implements PartController {

    private final PartService partService;

    public PartControllerImpl(PartService partService) {
        this.partService = partService;
    }


    @Override
    public List<PartDTO> getAllParts() {
        return partService.getAllParts();
    }
}
