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
import yarema.project.toystore.dto.type.RequestTypeDTO;
import yarema.project.toystore.dto.type.ResponseTypeDTO;
import yarema.project.toystore.entity.Type;
import yarema.project.toystore.repository.TypeRepository;
import yarema.project.toystore.stubs.TypeStub;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
public class TypeServiceV2Test {
    private TypeServiceV2 typeServiceV2;
    @Mock
    private TypeRepository typeRepository;

    @BeforeEach
    void setup() {
        typeServiceV2 = new TypeServiceV2(new ModelMapper(), typeRepository);
    }

    @Test
    void findAll() {
        Type type = TypeStub.getType();

        Mockito.when(typeRepository.findAll()).thenReturn(List.of(type));

        assertTrue(typeServiceV2.findAll().size() > 0);
    }

    @Test
    void findById() {
        Type type = TypeStub.getType();

        //Mockito.when(typeRepository.findById(Mockito.any())).thenReturn(Optional.of(type));

        try {
            ResponseTypeDTO result = typeServiceV2.findById(TypeStub.ID);
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
    void update() {
        var captor = ArgumentCaptor.forClass(Type.class);
        RequestTypeDTO typeDTO = TypeStub.getRequestDTO();
        try {
            ResponseTypeDTO result = typeServiceV2.update(TypeStub.ID, TypeStub.getRequestDTO());
            Mockito.verify(typeRepository, Mockito.atLeastOnce()).save(captor.capture());

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
        var captor = ArgumentCaptor.forClass(Type.class);
        RequestTypeDTO typeDTO = TypeStub.getRequestDTO();

        Mockito.when(typeRepository.save(Mockito.any())).thenReturn(TypeStub.getType());

        ResponseTypeDTO result = typeServiceV2.create(TypeStub.getRequestDTO());

        Mockito.verify(typeRepository, Mockito.atLeastOnce()).save(captor.capture());

        assertAll(
                () -> assertEquals(typeDTO.getName(), result.getName())
        );
    }

    @Test
    void deleteById() {
        try {
            typeServiceV2.deleteById(TypeStub.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            typeServiceV2.create(TypeStub.getRequestDTO());
            Mockito.verify(typeRepository, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}
