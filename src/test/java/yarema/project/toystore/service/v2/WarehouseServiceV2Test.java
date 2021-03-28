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
import yarema.project.toystore.dto.warehouse.RequestWarehouseDTO;
import yarema.project.toystore.dto.warehouse.ResponseWarehouseDTO;
import yarema.project.toystore.entity.Warehouse;
import yarema.project.toystore.repository.WarehouseRepository;
import yarema.project.toystore.stubs.WarehouseStub;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
public class WarehouseServiceV2Test {
    private WarehouseServiceV2 warehouseServiceV2;
    @Mock
    private WarehouseRepository warehouseRepository;

    @BeforeEach
    void setup() {
        warehouseServiceV2 = new WarehouseServiceV2(new ModelMapper(), warehouseRepository);
    }

    @Test
    void findAll() {
        Warehouse warehouse = WarehouseStub.getWarehouse();

        Mockito.when(warehouseRepository.findAll()).thenReturn(List.of(warehouse));

        assertTrue(warehouseServiceV2.findAll().size() > 0);
    }

    @Test
    void findById() {
        Warehouse warehouse = WarehouseStub.getWarehouse();

        //Mockito.when(warehouseRepository.findById(Mockito.any())).thenReturn(Optional.of(warehouse));

        try {
            ResponseWarehouseDTO result = warehouseServiceV2.findById(WarehouseStub.ID);
            assertAll(
                    () -> assertEquals(warehouse.getId(), result.getId()),
                    () -> assertEquals(warehouse.getName(), result.getName())
            );
            warehouseServiceV2.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(Warehouse.class);
        RequestWarehouseDTO warehouseDTO = WarehouseStub.getRequestDTO();
        try {
            ResponseWarehouseDTO result = warehouseServiceV2.update(WarehouseStub.ID, WarehouseStub.getRequestDTO());
            Mockito.verify(warehouseRepository, Mockito.atLeastOnce()).save(captor.capture());

            assertAll(
                    () -> assertEquals(warehouseDTO.getName(), result.getName())
            );
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void create() {
        var captor = ArgumentCaptor.forClass(Warehouse.class);
        RequestWarehouseDTO warehouseDTO = WarehouseStub.getRequestDTO();

        Mockito.when(warehouseRepository.save(Mockito.any())).thenReturn(WarehouseStub.getWarehouse());

        ResponseWarehouseDTO result = warehouseServiceV2.create(WarehouseStub.getRequestDTO());

        Mockito.verify(warehouseRepository, Mockito.atLeastOnce()).save(captor.capture());

        assertAll(
                () -> assertEquals(warehouseDTO.getName(), result.getName())
        );
    }

    @Test
    void deleteById() {
        try {
            warehouseServiceV2.deleteById(WarehouseStub.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            warehouseServiceV2.create(WarehouseStub.getRequestDTO());
            Mockito.verify(warehouseRepository, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}
