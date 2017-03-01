package com.manukyan.geocode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GeocodeIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getGeocodeAddress() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/geocode-address/33.969601/-84.100033")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("2651 Satellite Blvd, Duluth, GA 30096, USA")));
    }

    @Test
    public void getGeocode() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/geocode/33.969601/-84.100033")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith("{\"longitude\":-84.100033,\"latitude\":33.969601,\"address\":\"2651 Satellite Blvd, Duluth, GA 30096, USA\"")));
    }

    @Test
    public void getLast10Geocodes() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/geocodes-last10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
        getGeocode();
        mvc.perform(MockMvcRequestBuilders.get("/geocodes-last10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(not(equalTo("[]"))));
    }

}