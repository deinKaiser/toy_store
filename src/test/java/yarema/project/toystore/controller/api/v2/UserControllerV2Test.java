package yarema.project.toystore.controller.api.v2;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import yarema.project.toystore.dto.user.RequestUserDTO;
import yarema.project.toystore.dto.user.ResponseUserDTO;
import yarema.project.toystore.entity.User;
import yarema.project.toystore.service.v2.UserServiceV2;
import yarema.project.toystore.stubs.UserStub;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
public class UserControllerV2Test {
    private UserControllerV2 userControllerV2;
    @Mock
    private UserServiceV2 userServiceV2;

    @BeforeEach
    void setup() {
        userControllerV2 = new UserControllerV2(userServiceV2);
    }

    @Test
    void getAll() {
        Mockito.when(userServiceV2.findAll()).thenReturn(List.of(UserStub.getResponseDTO()));
        assertTrue(userServiceV2.findAll().size() > 0);
    }

    @Test
    void getById() {
        User user = UserStub.getUser();
        try {
            Mockito.when(userServiceV2.findById(Mockito.any())).thenReturn(UserStub.getResponseDTO());
            ResponseUserDTO result = userControllerV2.getById(UserStub.ID);
            assertAll(
                    () -> assertEquals(user.getId(), result.getId()),
                    () -> assertEquals(user.getName(), result.getName())
            );
            userServiceV2.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void deleteById() {
        try {
            userServiceV2.deleteById(UserStub.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            userServiceV2.create(UserStub.getRequestDTO());
            Mockito.verify(userServiceV2, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(RequestUserDTO.class);
        var captorId = ArgumentCaptor.forClass(Long.class);
        RequestUserDTO userDTO = UserStub.getRequestDTO();
        try {
            Mockito.when(userServiceV2.update(Mockito.any(), Mockito.any())).thenReturn(UserStub.getResponseDTO());

            ResponseUserDTO result = userControllerV2.update(UserStub.ID, UserStub.getRequestDTO());
            Mockito.verify(userServiceV2, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());

            assertAll(
                    () -> assertEquals(userDTO.getName(), result.getName())
            );
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void create() {
        var captor = ArgumentCaptor.forClass(RequestUserDTO.class);
        RequestUserDTO userDTO = UserStub.getRequestDTO();

        Mockito.when(userServiceV2.create(Mockito.any())).thenReturn(UserStub.getResponseDTO());
        try {
            ResponseUserDTO result = userControllerV2.create(UserStub.getRequestDTO());

            Mockito.verify(userServiceV2, Mockito.atLeastOnce()).create(captor.capture());

            assertAll(
                    () -> assertEquals(userDTO.getName(), result.getName())
            );
        } catch (Exception e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}
