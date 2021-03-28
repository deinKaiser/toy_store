package yarema.project.toystore.service.v2;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import yarema.project.toystore.dto.user.RequestUserDTO;
import yarema.project.toystore.dto.user.ResponseUserDTO;
import yarema.project.toystore.entity.User;
import yarema.project.toystore.repository.UserRepository;
import yarema.project.toystore.stubs.UserStub;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
public class UserServiceV2Test {
    private UserServiceV2 userServiceV2;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userServiceV2 = new UserServiceV2(new ModelMapper(), userRepository);
    }

    @Test
    void findAll() {
        User user = UserStub.getUser();

        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        assertTrue(userServiceV2.findAll().size() > 0);
    }

    @Test
    void findById() {
        User user = UserStub.getUser();

        //Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

        try {
            ResponseUserDTO result = userServiceV2.findById(UserStub.ID);
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
    void update() {
        var captor = ArgumentCaptor.forClass(User.class);
        RequestUserDTO userDTO = UserStub.getRequestDTO();
        try {
            ResponseUserDTO result = userServiceV2.update(UserStub.ID, UserStub.getRequestDTO());
            Mockito.verify(userRepository, Mockito.atLeastOnce()).save(captor.capture());

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
        var captor = ArgumentCaptor.forClass(User.class);
        RequestUserDTO userDTO = UserStub.getRequestDTO();

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(UserStub.getUser());

        ResponseUserDTO result = userServiceV2.create(UserStub.getRequestDTO());

        Mockito.verify(userRepository, Mockito.atLeastOnce()).save(captor.capture());

        assertAll(
                () -> assertEquals(userDTO.getName(), result.getName())
        );
    }

    @Test
    void deleteById() {
        try {
            userServiceV2.deleteById(UserStub.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            userServiceV2.create(UserStub.getRequestDTO());
            Mockito.verify(userRepository, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}
