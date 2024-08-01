package carservice.carserviceparts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import carservice.carserviceparts.model.entity.Part;
import carservice.carserviceparts.repository.PartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

@SpringBootTest
@AutoConfigureMockMvc
public class PartControllerImplTest {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPartById() {
        var actual = partRepository.save(
                new Part("name", 1.0, UUID.randomUUID(), "description")
        );

        mockMvc.perform(get("/parts/{id}", actual.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
