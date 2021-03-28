package yarema.project.toystore.controller.api.v2;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import yarema.project.toystore.dto.waybill.RequestWaybillDTO;
import yarema.project.toystore.dto.waybill.ResponseWaybillDTO;
import yarema.project.toystore.entity.Waybill;
import yarema.project.toystore.service.v2.WaybillServiceV2;
import yarema.project.toystore.stubs.WaybillStub;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
public class WaybillControllerV2Test {
    private WaybillControllerV2 waybillControllerV2;
    @Mock
    private WaybillServiceV2 waybillServiceV2;

    @BeforeEach
    void setup() {
        waybillControllerV2 = new WaybillControllerV2(waybillServiceV2);
    }

    @Test
    void getAll() {
        Mockito.when(waybillServiceV2.findAll()).thenReturn(List.of(WaybillStub.getResponseDTO()));
        assertTrue(waybillServiceV2.findAll().size() > 0);
    }

    @Test
    void getById() {
        Waybill waybill = WaybillStub.getWaybill();
        try {
            Mockito.when(waybillServiceV2.findById(Mockito.any())).thenReturn(WaybillStub.getResponseDTO());
            ResponseWaybillDTO result = waybillControllerV2.getById(WaybillStub.ID);
            assertAll(
                    () -> assertEquals(waybill.getId(), result.getId()),
                    () -> assertEquals(waybill.getWaybillCode(), result.getWaybillCode())
            );
            waybillServiceV2.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void deleteById() {
        try {
            waybillServiceV2.deleteById(WaybillStub.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            waybillServiceV2.create(WaybillStub.getRequestDTO());
            Mockito.verify(waybillServiceV2, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(RequestWaybillDTO.class);
        var captorId = ArgumentCaptor.forClass(Long.class);
        RequestWaybillDTO waybillDTO = WaybillStub.getRequestDTO();
        try {
            Mockito.when(waybillServiceV2.update(Mockito.any(), Mockito.any())).thenReturn(WaybillStub.getResponseDTO());

            ResponseWaybillDTO result = waybillControllerV2.update(WaybillStub.ID, WaybillStub.getRequestDTO());
            Mockito.verify(waybillServiceV2, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());

            assertAll(
                    () -> assertEquals(waybillDTO.getWaybillCode(), result.getWaybillCode())
            );
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void create() throws NotFoundException {
        var captor = ArgumentCaptor.forClass(RequestWaybillDTO.class);
        RequestWaybillDTO waybillDTO = WaybillStub.getRequestDTO();

        Mockito.when(waybillServiceV2.create(Mockito.any())).thenReturn(WaybillStub.getResponseDTO());
        try {
            ResponseWaybillDTO result = waybillControllerV2.create(WaybillStub.getRequestDTO());

            Mockito.verify(waybillServiceV2, Mockito.atLeastOnce()).create(captor.capture());

            assertAll(
                    () -> assertEquals(waybillDTO.getWaybillCode(), result.getWaybillCode())
            );
        } catch (Exception e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}
