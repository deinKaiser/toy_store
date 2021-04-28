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
import yarema.project.toystore.dto.type.ResponseTypeDTO;
import yarema.project.toystore.dto.type.RequestTypeDTO;
import yarema.project.toystore.dto.type.ResponseTypeDTO;
import yarema.project.toystore.entity.Type;
import yarema.project.toystore.service.v2.TypeServiceV2;
import yarema.project.toystore.service.v2.TypeServiceV2;
import yarema.project.toystore.stubs.TypeStub;
import yarema.project.toystore.stubs.TypeStub;

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
class TypeControllerV2Test {
    @MockBean
    private TypeServiceV2 typeService;

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
        ArrayList<ResponseTypeDTO> list = new ArrayList<>(Arrays.asList(TypeStub.getResponseDTO()));
        when(typeService.findAll()).thenReturn(list);

        mvc.perform(get("/api/v2/type/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().string(containsString(TypeStub.getResponseDTO().getName())));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testGetById() throws Exception {
        when(typeService.findById(Mockito.any())).thenReturn(TypeStub.getResponseDTO());

        mvc.perform(get("/api/v2/type/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(TypeStub.getResponseDTO().getName())));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testDeleteById() throws Exception {
        mvc.perform(delete("/api/v2/type/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testCreate() throws Exception {
        when(typeService.create(Mockito.any())).thenReturn(TypeStub.getResponseDTO());

        mvc.perform(post("/api/v2/type/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(TypeStub.getRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(TypeStub.getResponseDTO().getName())));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testUpdate() throws Exception {
        when(typeService.update(Mockito.any(), Mockito.any())).thenReturn(TypeStub.getResponseDTO());

        mvc.perform(put("/api/v2/type/1/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(TypeStub.getRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(TypeStub.getResponseDTO().getName())));
    }
}


//
//@ExtendWith({MockitoExtension.class})
//public class TypeControllerV2Test {
//    private TypeControllerV2 typeControllerV2;
//    @Mock
//    private TypeServiceV2 typeServiceV2;
//
//    @BeforeEach
//    void setup() {
//        typeControllerV2 = new TypeControllerV2(typeServiceV2);
//    }
//
//    @Test
//    void getAll() {
//        Mockito.when(typeServiceV2.findAll()).thenReturn(List.of(TypeStub.getResponseDTO()));
//        assertTrue(typeServiceV2.findAll().size() > 0);
//    }
//
//    @Test
//    void getById() {
//        Type type = TypeStub.getType();
//        try {
//            Mockito.when(typeServiceV2.findById(Mockito.any())).thenReturn(TypeStub.getResponseDTO());
//            ResponseTypeDTO result = typeControllerV2.getById(TypeStub.ID);
//            assertAll(
//                    () -> assertEquals(type.getId(), result.getId()),
//                    () -> assertEquals(type.getName(), result.getName())
//            );
//            typeServiceV2.findById(0L);
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
//            typeServiceV2.deleteById(TypeStub.ID);
//
//            var captor = ArgumentCaptor.forClass(Long.class);
//            typeServiceV2.create(TypeStub.getRequestDTO());
//            Mockito.verify(typeServiceV2, Mockito.atLeastOnce()).deleteById(captor.capture());
//        } catch (NotFoundException e) {
//            String expectedMessage = "not found";
//            String actualMessage = e.getMessage();
//            assertTrue(actualMessage.contains(expectedMessage));
//        }
//    }
//
//    @Test
//    void update() {
//        var captor = ArgumentCaptor.forClass(RequestTypeDTO.class);
//        var captorId = ArgumentCaptor.forClass(Long.class);
//        RequestTypeDTO typeDTO = TypeStub.getRequestDTO();
//        try {
//            Mockito.when(typeServiceV2.update(Mockito.any(), Mockito.any())).thenReturn(TypeStub.getResponseDTO());
//
//            ResponseTypeDTO result = typeControllerV2.update(TypeStub.ID, TypeStub.getRequestDTO());
//            Mockito.verify(typeServiceV2, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());
//
//            assertAll(
//                    () -> assertEquals(typeDTO.getName(), result.getName())
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
//        var captor = ArgumentCaptor.forClass(RequestTypeDTO.class);
//        RequestTypeDTO typeDTO = TypeStub.getRequestDTO();
//
//        Mockito.when(typeServiceV2.create(Mockito.any())).thenReturn(TypeStub.getResponseDTO());
//        try {
//            ResponseTypeDTO result = typeControllerV2.create(TypeStub.getRequestDTO());
//
//            Mockito.verify(typeServiceV2, Mockito.atLeastOnce()).create(captor.capture());
//
//            assertAll(
//                    () -> assertEquals(typeDTO.getName(), result.getName())
//            );
//        } catch (Exception e) {
//            String expectedMessage = "not found";
//            String actualMessage = e.getMessage();
//            assertTrue(actualMessage.contains(expectedMessage));
//        }
//    }
//}
