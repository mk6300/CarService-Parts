package carservice.carserviceparts.service;

import carservice.carserviceparts.model.dto.AddPartDTO;
import carservice.carserviceparts.model.dto.PartDTO;
import carservice.carserviceparts.model.entity.Part;
import carservice.carserviceparts.repository.PartRepository;
import carservice.carserviceparts.service.exception.ObjectNotFoundException;
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

    @Override
    public PartDTO getPartById(Long id) {
        return partRepository.findById(id)
                .map(this::map)
                .orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }

    @Override
    public PartDTO addPart(AddPartDTO addPartDTO) {
        Part part = new Part() ;
        part.setName(addPartDTO.getName());
        part.setPrice(addPartDTO.getPrice());
        part.setSupplierId(addPartDTO.getSupplierId());
        part.setDescription(addPartDTO.getDescription());
        partRepository.save(part);
        return map(part);
    }

    private PartDTO map(Part part) {
                return modelMapper.map(part, PartDTO.class);
    }
}
