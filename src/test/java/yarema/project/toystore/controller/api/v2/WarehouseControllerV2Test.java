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
import yarema.project.toystore.dto.warehouse.ResponseWarehouseDTO;
import yarema.project.toystore.dto.warehouse.RequestWarehouseDTO;
import yarema.project.toystore.dto.warehouse.ResponseWarehouseDTO;
import yarema.project.toystore.entity.Warehouse;
import yarema.project.toystore.service.v2.WarehouseServiceV2;
import yarema.project.toystore.service.v2.WarehouseServiceV2;
import yarema.project.toystore.stubs.WarehouseStub;
import yarema.project.toystore.stubs.WarehouseStub;

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
class WarehouseControllerV2Test {
    @MockBean
    private WarehouseServiceV2 warehouseService;

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
        ArrayList<ResponseWarehouseDTO> list = new ArrayList<>(Arrays.asList(WarehouseStub.getResponseDTO()));
        when(warehouseService.findAll()).thenReturn(list);

        mvc.perform(get("/api/v2/warehouse/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().string(containsString(WarehouseStub.getResponseDTO().getName())));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testGetById() throws Exception {
        when(warehouseService.findById(Mockito.any())).thenReturn(WarehouseStub.getResponseDTO());

        mvc.perform(get("/api/v2/warehouse/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(WarehouseStub.getResponseDTO().getName())));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testDeleteById() throws Exception {
        mvc.perform(delete("/api/v2/warehouse/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testCreate() throws Exception {
        when(warehouseService.create(Mockito.any())).thenReturn(WarehouseStub.getResponseDTO());

        mvc.perform(post("/api/v2/warehouse/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(WarehouseStub.getRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(WarehouseStub.getResponseDTO().getName())));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testUpdate() throws Exception {
        when(warehouseService.update(Mockito.any(), Mockito.any())).thenReturn(WarehouseStub.getResponseDTO());

        mvc.perform(put("/api/v2/warehouse/1/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(WarehouseStub.getRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(WarehouseStub.getResponseDTO().getName())));
    }
}
//@ExtendWith({MockitoExtension.class})
//public class WarehouseControllerV2Test {
//    private WarehouseControllerV2 warehouseControllerV2;
//    @Mock
//    private WarehouseServiceV2 warehouseServiceV2;
//
//    @BeforeEach
//    void setup() {
//        warehouseControllerV2 = new WarehouseControllerV2(warehouseServiceV2);
//    }
//
//    @Test
//    void getAll() {
//        Mockito.when(warehouseServiceV2.findAll()).thenReturn(List.of(WarehouseStub.getResponseDTO()));
//        assertTrue(warehouseServiceV2.findAll().size() > 0);
//    }
//
//    @Test
//    void getById() {
//        Warehouse warehouse = WarehouseStub.getWarehouse();
//        try {
//            Mockito.when(warehouseServiceV2.findById(Mockito.any())).thenReturn(WarehouseStub.getResponseDTO());
//            ResponseWarehouseDTO result = warehouseControllerV2.getById(WarehouseStub.ID);
//            assertAll(
//                    () -> assertEquals(warehouse.getName(), result.getName())
//            );
//            warehouseServiceV2.findById(0L);
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
//            warehouseServiceV2.deleteById(WarehouseStub.ID);
//
//            var captor = ArgumentCaptor.forClass(Long.class);
//            warehouseServiceV2.create(WarehouseStub.getRequestDTO());
//            Mockito.verify(warehouseServiceV2, Mockito.atLeastOnce()).deleteById(captor.capture());
//        } catch (NotFoundException e) {
//            String expectedMessage = "not found";
//            String actualMessage = e.getMessage();
//            assertTrue(actualMessage.contains(expectedMessage));
//        }
//    }
//
//    @Test
//    void update() {
//        var captor = ArgumentCaptor.forClass(RequestWarehouseDTO.class);
//        var captorId = ArgumentCaptor.forClass(Long.class);
//        RequestWarehouseDTO warehouseDTO = WarehouseStub.getRequestDTO();
//        try {
//            Mockito.when(warehouseServiceV2.update(Mockito.any(), Mockito.any())).thenReturn(WarehouseStub.getResponseDTO());
//
//            ResponseWarehouseDTO result = warehouseControllerV2.update(WarehouseStub.ID, WarehouseStub.getRequestDTO());
//            Mockito.verify(warehouseServiceV2, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());
//
//            assertAll(
//                    () -> assertEquals(warehouseDTO.getName(), result.getName())
//            );
//        } catch (NotFoundException e) {
//            String expectedMessage = "not found";
//            String actualMessage = e.getMessage();
//            assertTrue(actualMessage.contains(expectedMessage));
//        }
//    }
//
//    @Test
//    void create() {
//        var captor = ArgumentCaptor.forClass(RequestWarehouseDTO.class);
//        RequestWarehouseDTO warehouseDTO = WarehouseStub.getRequestDTO();
//
//        Mockito.when(warehouseServiceV2.create(Mockito.any())).thenReturn(WarehouseStub.getResponseDTO());
//        try {
//            ResponseWarehouseDTO result = warehouseControllerV2.create(WarehouseStub.getRequestDTO());
//
//            Mockito.verify(warehouseServiceV2, Mockito.atLeastOnce()).create(captor.capture());
//
//            assertAll(
//                    () -> assertEquals(warehouseDTO.getName(), result.getName())
//            );
//        } catch (Exception e) {
//            String expectedMessage = "not found";
//            String actualMessage = e.getMessage();
//            assertTrue(actualMessage.contains(expectedMessage));
//        }
//    }
//}
