package yarema.project.toystore.controller.api.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import yarema.project.toystore.dto.waybill.ResponseWaybillDTO;
import yarema.project.toystore.dto.waybill.RequestWaybillDTO;
import yarema.project.toystore.dto.waybill.ResponseWaybillDTO;
import yarema.project.toystore.entity.Waybill;
import yarema.project.toystore.service.v2.WaybillServiceV2;
import yarema.project.toystore.service.v2.WaybillServiceV2;
import yarema.project.toystore.stubs.WaybillStub;
import yarema.project.toystore.stubs.WaybillStub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WaybillControllerV2Test {
    @MockBean
    private WaybillServiceV2 waybillService;

    @Autowired
    private MockMvc mvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testGetAll() throws Exception {
        ArrayList<ResponseWaybillDTO> list = new ArrayList<>(Arrays.asList(WaybillStub.getResponseDTO()));
        when(waybillService.findAll()).thenReturn(list);

        mvc.perform(get("/api/v2/waybill/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().string(containsString(WaybillStub.getResponseDTO().getWaybillCode())));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testGetById() throws Exception {
        when(waybillService.findById(Mockito.any())).thenReturn(WaybillStub.getResponseDTO());

        mvc.perform(get("/api/v2/waybill/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(WaybillStub.getResponseDTO().getWaybillCode())));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testDeleteById() throws Exception {
        mvc.perform(delete("/api/v2/waybill/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testCreate() throws Exception {
        when(waybillService.create(Mockito.any())).thenReturn(WaybillStub.getResponseDTO());

        mvc.perform(post("/api/v2/waybill/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(WaybillStub.getRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(WaybillStub.getResponseDTO().getWaybillCode())));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testUpdate() throws Exception {
        when(waybillService.update(Mockito.any(), Mockito.any())).thenReturn(WaybillStub.getResponseDTO());

        mvc.perform(put("/api/v2/waybill/1/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(WaybillStub.getRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(WaybillStub.getResponseDTO().getWaybillCode())));
    }
}


//
//@ExtendWith({MockitoExtension.class})
//public class WaybillControllerV2Test {
//    private WaybillControllerV2 waybillControllerV2;
//    @Mock
//    private WaybillServiceV2 waybillServiceV2;
//
//    @BeforeEach
//    void setup() {
//        waybillControllerV2 = new WaybillControllerV2(waybillServiceV2);
//    }
//
//    @Test
//    void getAll() {
//        Mockito.when(waybillServiceV2.findAll()).thenReturn(List.of(WaybillStub.getResponseDTO()));
//        assertTrue(waybillServiceV2.findAll().size() > 0);
//    }
//
//    @Test
//    void getById() {
//        Waybill waybill = WaybillStub.getWaybill();
//        try {
//            Mockito.when(waybillServiceV2.findById(Mockito.any())).thenReturn(WaybillStub.getResponseDTO());
//            ResponseWaybillDTO result = waybillControllerV2.getById(WaybillStub.ID);
//            assertAll(
//                    () -> assertEquals(waybill.getId(), result.getId()),
//                    () -> assertEquals(waybillgetWaybillCode(), resultgetWaybillCode())
//            );
//            waybillServiceV2.findById(0L);
//        } catch (NotFoundException e) {
//            String expectedMessage = "not found";
//            String actualMessage = e.getMessage();
//            assertTrue(actualMessage.contains(expectedMessage));
//        }
//    }
//
//    @Test
//    void deleteById() {
//        try {
//            waybillServiceV2.deleteById(WaybillStub.ID);
//
//            var captor = ArgumentCaptor.forClass(Long.class);
//            waybillServiceV2.create(WaybillStub.getRequestDTO());
//            Mockito.verify(waybillServiceV2, Mockito.atLeastOnce()).deleteById(captor.capture());
//        } catch (NotFoundException e) {
//            String expectedMessage = "not found";
//            String actualMessage = e.getMessage();
//            assertTrue(actualMessage.contains(expectedMessage));
//        }
//    }
//
//    @Test
//    void update() {
//        var captor = ArgumentCaptor.forClass(RequestWaybillDTO.class);
//        var captorId = ArgumentCaptor.forClass(Long.class);
//        RequestWaybillDTO waybillDTO = WaybillStub.getRequestDTO();
//        try {
//            Mockito.when(waybillServiceV2.update(Mockito.any(), Mockito.any())).thenReturn(WaybillStub.getResponseDTO());
//
//            ResponseWaybillDTO result = waybillControllerV2.update(WaybillStub.ID, WaybillStub.getRequestDTO());
//            Mockito.verify(waybillServiceV2, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());
//
//            assertAll(
//                    () -> assertEquals(waybillDTOgetWaybillCode(), resultgetWaybillCode())
//            );
//        } catch (NotFoundException e) {
//            String expectedMessage = "not found";
//            String actualMessage = e.getMessage();
//            assertTrue(actualMessage.contains(expectedMessage));
//        }
//    }
//
//    @Test
//    void create() throws NotFoundException {
//        var captor = ArgumentCaptor.forClass(RequestWaybillDTO.class);
//        RequestWaybillDTO waybillDTO = WaybillStub.getRequestDTO();
//
//        Mockito.when(waybillServiceV2.create(Mockito.any())).thenReturn(WaybillStub.getResponseDTO());
//        try {
//            ResponseWaybillDTO result = waybillControllerV2.create(WaybillStub.getRequestDTO());
//
//            Mockito.verify(waybillServiceV2, Mockito.atLeastOnce()).create(captor.capture());
//
//            assertAll(
//                    () -> assertEquals(waybillDTOgetWaybillCode(), resultgetWaybillCode())
//            );
//        } catch (Exception e) {
//            String expectedMessage = "not found";
//            String actualMessage = e.getMessage();
//            assertTrue(actualMessage.contains(expectedMessage));
//        }
//    }
//}
