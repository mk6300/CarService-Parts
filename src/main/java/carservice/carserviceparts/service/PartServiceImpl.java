package carservice.carserviceparts.service;

import carservice.carserviceparts.model.dto.PartDTO;
import carservice.carserviceparts.model.entity.Part;
import carservice.carserviceparts.repository.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService {
private final PartRepository partRepository;
private final ModelMapper modelMapper;

    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<PartDTO> getAllParts() {
        return partRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private PartDTO map (Part part) {
        return modelMapper.map(part, PartDTO.class);
    }
}
