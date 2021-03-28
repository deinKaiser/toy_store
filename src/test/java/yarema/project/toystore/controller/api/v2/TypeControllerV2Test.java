package yarema.project.toystore.controller.api.v2;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import yarema.project.toystore.dto.type.RequestTypeDTO;
import yarema.project.toystore.dto.type.ResponseTypeDTO;
import yarema.project.toystore.entity.Type;
import yarema.project.toystore.service.v2.TypeServiceV2;
import yarema.project.toystore.stubs.TypeStub;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
public class TypeControllerV2Test {
    private TypeControllerV2 typeControllerV2;
    @Mock
    private TypeServiceV2 typeServiceV2;

    @BeforeEach
    void setup() {
        typeControllerV2 = new TypeControllerV2(typeServiceV2);
    }

    @Test
    void getAll() {
        Mockito.when(typeServiceV2.findAll()).thenReturn(List.of(TypeStub.getResponseDTO()));
        assertTrue(typeServiceV2.findAll().size() > 0);
    }

    @Test
    void getById() {
        Type type = TypeStub.getType();
        try {
            Mockito.when(typeServiceV2.findById(Mockito.any())).thenReturn(TypeStub.getResponseDTO());
            ResponseTypeDTO result = typeControllerV2.getById(TypeStub.ID);
            assertAll(
                    () -> assertEquals(type.getId(), result.getId()),
                    () -> assertEquals(type.getName(), result.getName())
            );
            typeServiceV2.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void deleteById() {
        try {
            typeServiceV2.deleteById(TypeStub.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            typeServiceV2.create(TypeStub.getRequestDTO());
            Mockito.verify(typeServiceV2, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(RequestTypeDTO.class);
        var captorId = ArgumentCaptor.forClass(Long.class);
        RequestTypeDTO typeDTO = TypeStub.getRequestDTO();
        try {
            Mockito.when(typeServiceV2.update(Mockito.any(), Mockito.any())).thenReturn(TypeStub.getResponseDTO());

            ResponseTypeDTO result = typeControllerV2.update(TypeStub.ID, TypeStub.getRequestDTO());
            Mockito.verify(typeServiceV2, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());

            assertAll(
                    () -> assertEquals(typeDTO.getName(), result.getName())
            );
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void create() {
        var captor = ArgumentCaptor.forClass(RequestTypeDTO.class);
        RequestTypeDTO typeDTO = TypeStub.getRequestDTO();

        Mockito.when(typeServiceV2.create(Mockito.any())).thenReturn(TypeStub.getResponseDTO());
        try {
            ResponseTypeDTO result = typeControllerV2.create(TypeStub.getRequestDTO());

            Mockito.verify(typeServiceV2, Mockito.atLeastOnce()).create(captor.capture());

            assertAll(
                    () -> assertEquals(typeDTO.getName(), result.getName())
            );
        } catch (Exception e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}
