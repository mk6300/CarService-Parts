package carservice.carserviceparts.controller;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import carservice.carserviceparts.model.dto.PartDTO;
import carservice.carserviceparts.model.entity.Part;
import carservice.carserviceparts.repository.PartRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class PartControllerImplTest {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        partRepository.deleteAll();
    }

    @Test
    public void testGetPartById() throws Exception {
        var actual = partRepository.save(
                new Part("name", 1.0, UUID.randomUUID(), "description")
        );

        ResultActions result = mockMvc.perform(get("/parts/{id}", actual.getId())
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(actual.getId().toString()))
                .andExpect(jsonPath("$.name").value(actual.getName()))
                .andExpect(jsonPath("$.price").value(actual.getPrice()))
                .andExpect(jsonPath("$.supplierId").value(actual.getSupplierId().toString()));
    }

    @Test
    public void testGetPartByIdNotFound() throws Exception {
        mockMvc.perform(get("/parts/{id}", 10000000)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllParts() throws Exception {
        var actual = partRepository.save(
                new Part("name", 1.0, UUID.randomUUID(), "description")
        );

        mockMvc.perform(get("/parts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(actual.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(actual.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(actual.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].supplierId").value(actual.getSupplierId().toString()));
    }

    @Test
    public void testGetAllPartsEmpty() throws Exception {
        mockMvc.perform(get("/parts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    public void testDeletePart() throws Exception {
        var actual = partRepository.save(
                new Part("name", 1.0, UUID.randomUUID(), "description")
        );

        mockMvc.perform(delete("/parts/{id}", actual.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/parts/{id}", actual.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeletePartNotFound() throws Exception {
        mockMvc.perform(get("/parts/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddPart() throws Exception {
        var actual = partRepository.save(
                new Part("name", 1.0, UUID.randomUUID(), "description")
        );

        mockMvc.perform(post("/parts/add-part")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(actual)))
                .andExpect(status().isOk());
    }

    @Test
    public void testEditPart() throws Exception {
        var actual = partRepository.save(
                new Part("name", 1.0, UUID.randomUUID(), "description")
        );
        var expected = new PartDTO();
        expected.setId(actual.getId());
        expected.setName("name");
        expected.setPrice(2.0);
        expected.setDescription("description");
        expected.setSupplierId(actual.getSupplierId());


        mockMvc.perform(put("/parts/edit-part")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(expected)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", is(expected.getPrice())))
                .andExpect(jsonPath("$.description", is(expected.getDescription())));
    }

    @Test
    public void testEditPartNotFound() throws Exception {
        var actual = partRepository.save(
                new Part("name", 1.0, UUID.randomUUID(), "description")
        );
        var expected = new PartDTO();
        expected.setId(1000000L);
        mockMvc.perform(put("/parts/edit-part")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(expected)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteAllPartsFromSupplier() throws Exception {
        var actual = partRepository.save(
                new Part("name", 1.0, UUID.randomUUID(), "description")
        );

        mockMvc.perform(delete("/parts/remove-all/{id}", actual.getSupplierId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
