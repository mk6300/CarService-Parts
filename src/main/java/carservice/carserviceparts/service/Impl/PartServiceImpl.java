package carservice.carserviceparts.service.Impl;

import carservice.carserviceparts.model.dto.AddPartDTO;
import carservice.carserviceparts.model.dto.PartDTO;
import carservice.carserviceparts.model.entity.Part;
import carservice.carserviceparts.repository.PartRepository;
import carservice.carserviceparts.service.PartService;
import carservice.carserviceparts.service.exception.PartNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
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
                .orElseThrow(PartNotFoundException::new);
    }

    @Override
    @Transactional
    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }


    @Override
    @Transactional
    public PartDTO addPart(AddPartDTO addPartDTO) {
        Part part = new Part() ;
        part.setName(addPartDTO.getName());
        part.setPrice(addPartDTO.getPrice());
        part.setSupplierId(addPartDTO.getSupplierId());
        part.setDescription(addPartDTO.getDescription());
        partRepository.save(part);
        return map(part);
    }

    @Override
    @Transactional
    public PartDTO editPart(PartDTO partDTO) {
        Part part = partRepository.findById(partDTO.getId())
                .orElseThrow(PartNotFoundException::new);
        part.setName(partDTO.getName());
        part.setPrice(partDTO.getPrice());
        part.setSupplierId(partDTO.getSupplierId());
        part.setDescription(partDTO.getDescription());
        partRepository.save(part);
        return map(part);
    }

    @Override
    @Transactional
    public void deleteAllPartsFromSupplier(UUID id) {
        partRepository.deleteAllBySupplierId(id);

    }

    private PartDTO map(Part part) {
                return modelMapper.map(part, PartDTO.class);
    }
}
