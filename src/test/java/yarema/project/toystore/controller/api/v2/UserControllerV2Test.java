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
import yarema.project.toystore.dto.user.RequestUserDTO;
import yarema.project.toystore.dto.user.ResponseUserDTO;
import yarema.project.toystore.entity.User;
import yarema.project.toystore.service.v2.UserServiceV2;
import yarema.project.toystore.stubs.UserStub;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerV2Test {
    @MockBean
    private UserServiceV2 userService;

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
        ArrayList<ResponseUserDTO> list = new ArrayList<>(Arrays.asList(UserStub.getResponseDTO()));
        when(userService.findAll()).thenReturn(list);

        mvc.perform(get("/api/v2/user/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().string(containsString(UserStub.getResponseDTO().getName())));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    void testGetById() throws Exception {
        when(userService.findById(Mockito.any())).thenReturn(UserStub.getResponseDTO());

        mvc.perform(get("/api/v2/user/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(UserStub.getResponseDTO().getName())));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testDeleteById() throws Exception {
        mvc.perform(delete("/api/v2/user/1/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testCreate() throws Exception {
        when(userService.create(Mockito.any())).thenReturn(UserStub.getResponseDTO());

        mvc.perform(post("/api/v2/user/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(UserStub.getRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(UserStub.getResponseDTO().getName())));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    void testUpdate() throws Exception {
        when(userService.update(Mockito.any(), Mockito.any())).thenReturn(UserStub.getResponseDTO());

        mvc.perform(put("/api/v2/user/1/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(UserStub.getRequestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().string(containsString(UserStub.getResponseDTO().getName())));
    }
}


//@ExtendWith({MockitoExtension.class})
//public class UserControllerV2Test {
//    private UserControllerV2 userControllerV2;
//    @Mock
//    private UserServiceV2 userServiceV2;
//
//    @BeforeEach
//    void setup() {
//        userControllerV2 = new UserControllerV2(userServiceV2);
//    }
//
//    @Test
//    void getAll() {
//        Mockito.when(userServiceV2.findAll()).thenReturn(List.of(UserStub.getResponseDTO()));
//        assertTrue(userServiceV2.findAll().size() > 0);
//    }
//
//    @Test
//    void getById() {
//        User user = UserStub.getUser();
//        try {
//            Mockito.when(userServiceV2.findById(Mockito.any())).thenReturn(UserStub.getResponseDTO());
//            ResponseUserDTO result = userControllerV2.getById(UserStub.ID);
//            assertAll(
//                    () -> assertEquals(user.getId(), result.getId()),
//                    () -> assertEquals(user.getName(), result.getName())
//            );
//            userServiceV2.findById(0L);
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
//            userServiceV2.deleteById(UserStub.ID);
//
//            var captor = ArgumentCaptor.forClass(Long.class);
//            userServiceV2.create(UserStub.getRequestDTO());
//            Mockito.verify(userServiceV2, Mockito.atLeastOnce()).deleteById(captor.capture());
//        } catch (NotFoundException e) {
//            String expectedMessage = "not found";
//            String actualMessage = e.getMessage();
//            assertTrue(actualMessage.contains(expectedMessage));
//        }
//    }
//
//    @Test
//    void update() {
//        var captor = ArgumentCaptor.forClass(RequestUserDTO.class);
//        var captorId = ArgumentCaptor.forClass(Long.class);
//        RequestUserDTO userDTO = UserStub.getRequestDTO();
//        try {
//            Mockito.when(userServiceV2.update(Mockito.any(), Mockito.any())).thenReturn(UserStub.getResponseDTO());
//
//            ResponseUserDTO result = userControllerV2.update(UserStub.ID, UserStub.getRequestDTO());
//            Mockito.verify(userServiceV2, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());
//
//            assertAll(
//                    () -> assertEquals(userDTO.getName(), result.getName())
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
//        var captor = ArgumentCaptor.forClass(RequestUserDTO.class);
//        RequestUserDTO userDTO = UserStub.getRequestDTO();
//
//        Mockito.when(userServiceV2.create(Mockito.any())).thenReturn(UserStub.getResponseDTO());
//        try {
//            ResponseUserDTO result = userControllerV2.create(UserStub.getRequestDTO());
//
//            Mockito.verify(userServiceV2, Mockito.atLeastOnce()).create(captor.capture());
//
//            assertAll(
//                    () -> assertEquals(userDTO.getName(), result.getName())
//            );
//        } catch (Exception e) {
//            String expectedMessage = "not found";
//            String actualMessage = e.getMessage();
//            assertTrue(actualMessage.contains(expectedMessage));
//        }
//    }
//}
