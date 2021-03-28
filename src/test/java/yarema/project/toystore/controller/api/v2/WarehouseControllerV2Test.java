package yarema.project.toystore.controller.api.v2;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import yarema.project.toystore.dto.warehouse.RequestWarehouseDTO;
import yarema.project.toystore.dto.warehouse.ResponseWarehouseDTO;
import yarema.project.toystore.entity.Warehouse;
import yarema.project.toystore.service.v2.WarehouseServiceV2;
import yarema.project.toystore.stubs.WarehouseStub;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
public class WarehouseControllerV2Test {
    private WarehouseControllerV2 warehouseControllerV2;
    @Mock
    private WarehouseServiceV2 warehouseServiceV2;

    @BeforeEach
    void setup() {
        warehouseControllerV2 = new WarehouseControllerV2(warehouseServiceV2);
    }

    @Test
    void getAll() {
        Mockito.when(warehouseServiceV2.findAll()).thenReturn(List.of(WarehouseStub.getResponseDTO()));
        assertTrue(warehouseServiceV2.findAll().size() > 0);
    }

    @Test
    void getById() {
        Warehouse warehouse = WarehouseStub.getWarehouse();
        try {
            Mockito.when(warehouseServiceV2.findById(Mockito.any())).thenReturn(WarehouseStub.getResponseDTO());
            ResponseWarehouseDTO result = warehouseControllerV2.getById(WarehouseStub.ID);
            assertAll(
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
    void deleteById() {
        try {
            warehouseServiceV2.deleteById(WarehouseStub.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            warehouseServiceV2.create(WarehouseStub.getRequestDTO());
            Mockito.verify(warehouseServiceV2, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(RequestWarehouseDTO.class);
        var captorId = ArgumentCaptor.forClass(Long.class);
        RequestWarehouseDTO warehouseDTO = WarehouseStub.getRequestDTO();
        try {
            Mockito.when(warehouseServiceV2.update(Mockito.any(), Mockito.any())).thenReturn(WarehouseStub.getResponseDTO());

            ResponseWarehouseDTO result = warehouseControllerV2.update(WarehouseStub.ID, WarehouseStub.getRequestDTO());
            Mockito.verify(warehouseServiceV2, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());

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
        var captor = ArgumentCaptor.forClass(RequestWarehouseDTO.class);
        RequestWarehouseDTO warehouseDTO = WarehouseStub.getRequestDTO();

        Mockito.when(warehouseServiceV2.create(Mockito.any())).thenReturn(WarehouseStub.getResponseDTO());
        try {
            ResponseWarehouseDTO result = warehouseControllerV2.create(WarehouseStub.getRequestDTO());

            Mockito.verify(warehouseServiceV2, Mockito.atLeastOnce()).create(captor.capture());

            assertAll(
                    () -> assertEquals(warehouseDTO.getName(), result.getName())
            );
        } catch (Exception e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}
