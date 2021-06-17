package de.ninjo.thump;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import de.ninjo.thump.domain.Record;

@SpringBootTest
@AutoConfigureMockMvc
public class DummyTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenPostLog_DataComplete_returnCreated() throws Exception {
        String json = getRecordJson(BigDecimal.TEN, "1", BigDecimal.TEN);

        mockMvc.perform(post("/log").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"deviceId\":\"1\",\"temperature\":10,\"humidity\":10}")));
    }

    private String getRecordJson(BigDecimal temperature, String deviceId, BigDecimal humidity) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(new Record(deviceId, temperature, humidity));
    }

    @Test
    void whenPostLog_DataIncomplete_returnBadRequest() throws Exception {
        String json = getRecordJson(null, "1", BigDecimal.TEN);

        mockMvc.perform(post("/log").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("{\"temperature\":\"must not be null\"}")));
    }

    @Test
    void whenPostLog_DataIncomplete2_returnBadRequest() throws Exception {
        String json = getRecordJson(BigDecimal.ONE, "1", new BigDecimal(1000));

        mockMvc.perform(post("/log").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("\"humidity\":\"must be less than or equal to 100\"")));
    }
}
