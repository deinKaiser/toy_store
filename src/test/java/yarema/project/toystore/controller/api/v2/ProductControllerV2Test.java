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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import yarema.project.toystore.dto.product.RequestProductDTO;
import yarema.project.toystore.dto.product.ResponseProductDTO;
import yarema.project.toystore.dto.product.ResponseProductDTO;
import yarema.project.toystore.entity.Product;
import yarema.project.toystore.service.v2.ProductServiceV2;
import yarema.project.toystore.service.v2.ProductServiceV2;
import yarema.project.toystore.stubs.ProductStub;
import yarema.project.toystore.stubs.ProductStub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductControllerV2Test {
    @MockBean
    private ProductServiceV2 productService;

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
        ArrayList<ResponseProductDTO> list = new ArrayList<>(Arrays.asList(ProductStub.getResponseDTO()));
        when(productService.findAll()).thenReturn(list);

        mvc.perform(get("/api/v2/product/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().string(containsString(ProductStub.getResponseDTO().getName())));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testGetById() throws Exception {
        when(productService.findById(Mockito.any())).thenReturn(ProductStub.getResponseDTO());

        mvc.perform(get("/api/v2/product/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(ProductStub.getResponseDTO().getName())));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testDeleteById() throws Exception {
        mvc.perform(delete("/api/v2/product/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testCreate() throws Exception {
        when(productService.create(Mockito.any())).thenReturn(ProductStub.getResponseDTO());

        mvc.perform(post("/api/v2/product/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(ProductStub.getRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(ProductStub.getResponseDTO().getName())));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testUpdate() throws Exception {
        when(productService.update(Mockito.any(), Mockito.any())).thenReturn(ProductStub.getResponseDTO());

        mvc.perform(put("/api/v2/product/1/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(ProductStub.getRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(ProductStub.getResponseDTO().getName())));
    }
}

//@ExtendWith({MockitoExtension.class})
//public class ProductControllerV2Test {
//    private ProductControllerV2 productControllerV2;
//    @Mock
//    private ProductServiceV2 productServiceV2;
//
//    @BeforeEach
//    void setup() {
//        productControllerV2 = new ProductControllerV2(productServiceV2);
//    }
//
//    @Test
//    void getAll() {
//        Mockito.when(productServiceV2.findAll()).thenReturn(List.of(ProductStub.getResponseDTO()));
//        assertTrue(productServiceV2.findAll().size() > 0);
//    }
//
//    @Test
//    void getById() {
//        Product product = ProductStub.getProduct();
//        try {
//            Mockito.when(productServiceV2.findById(Mockito.any())).thenReturn(ProductStub.getResponseDTO());
//            ResponseProductDTO result = productControllerV2.getById(ProductStub.ID);
//            assertAll(
//                    () -> assertEquals(product.getId(), result.getId()),
//                    () -> assertEquals(product.getName(), result.getName())
//            );
//            productServiceV2.findById(0L);
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
//            productServiceV2.deleteById(ProductStub.ID);
//
//            var captor = ArgumentCaptor.forClass(Long.class);
//            productServiceV2.create(ProductStub.getRequestDTO());
//            Mockito.verify(productServiceV2, Mockito.atLeastOnce()).deleteById(captor.capture());
//        } catch (NotFoundException e) {
//            String expectedMessage = "not found";
//            String actualMessage = e.getMessage();
//            assertTrue(actualMessage.contains(expectedMessage));
//        }
//    }
//
//    @Test
//    void update() {
//        var captor = ArgumentCaptor.forClass(RequestProductDTO.class);
//        var captorId = ArgumentCaptor.forClass(Long.class);
//        RequestProductDTO productDTO = ProductStub.getRequestDTO();
//        try {
//            Mockito.when(productServiceV2.update(Mockito.any(), Mockito.any())).thenReturn(ProductStub.getResponseDTO());
//
//            ResponseProductDTO result = productControllerV2.update(ProductStub.ID, ProductStub.getRequestDTO());
//            Mockito.verify(productServiceV2, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());
//
//            assertAll(
//                    () -> assertEquals(productDTO.getName(), result.getName())
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
//        var captor = ArgumentCaptor.forClass(RequestProductDTO.class);
//        RequestProductDTO productDTO = ProductStub.getRequestDTO();
//
//        Mockito.when(productServiceV2.create(Mockito.any())).thenReturn(ProductStub.getResponseDTO());
//        try {
//            ResponseProductDTO result = productControllerV2.create(ProductStub.getRequestDTO());
//
//            Mockito.verify(productServiceV2, Mockito.atLeastOnce()).create(captor.capture());
//
//            assertAll(
//                    () -> assertEquals(productDTO.getName(), result.getName())
//            );
//        } catch (Exception e) {
//            String expectedMessage = "not found";
//            String actualMessage = e.getMessage();
//            assertTrue(actualMessage.contains(expectedMessage));
//        }
//    }
//}
