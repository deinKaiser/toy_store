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
import yarema.project.toystore.dto.waybill.RequestWaybillDTO;
import yarema.project.toystore.dto.waybill.ResponseWaybillDTO;
import yarema.project.toystore.dto.waybill.RequestWaybillDTO;
import yarema.project.toystore.dto.waybill.ResponseWaybillDTO;
import yarema.project.toystore.entity.Waybill;
import yarema.project.toystore.entity.Waybill;
import yarema.project.toystore.repository.ProductRepository;
import yarema.project.toystore.repository.WaybillRepository;
import yarema.project.toystore.repository.UserRepository;
import yarema.project.toystore.repository.WaybillRepository;
import yarema.project.toystore.stubs.WaybillStub;
import yarema.project.toystore.stubs.WaybillStub;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
public class WaybilServiceV2Test {
    private WaybillServiceV2 waybillServiceV2;
    @Mock
    private WaybillRepository waybillRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        waybillServiceV2 = new WaybillServiceV2(new ModelMapper(), waybillRepository,userRepository,productRepository );
    }

    @Test
    void findAll() {
        Waybill waybill = WaybillStub.getWaybill();

        Mockito.when(waybillRepository.findAll()).thenReturn(List.of(waybill));

        assertTrue(waybillServiceV2.findAll().size() > 0);
    }

    @Test
    void findById() {
        Waybill waybill = WaybillStub.getWaybill();

        //Mockito.when(waybillRepository.findById(Mockito.any())).thenReturn(Optional.of(waybill));

        try {
            ResponseWaybillDTO result = waybillServiceV2.findById(WaybillStub.ID);
            assertAll(
                    () -> assertEquals(waybill.getId(), result.getId())

            );
            waybillServiceV2.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(Waybill.class);
        RequestWaybillDTO waybillDTO = WaybillStub.getRequestDTO();
        try {
            ResponseWaybillDTO result = waybillServiceV2.update(WaybillStub.ID, WaybillStub.getRequestDTO());
            Mockito.verify(waybillRepository, Mockito.atLeastOnce()).save(captor.capture());

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
    void create() {
        var captor = ArgumentCaptor.forClass(Waybill.class);
        RequestWaybillDTO waybillDTO = WaybillStub.getRequestDTO();

        try {
            ResponseWaybillDTO result = waybillServiceV2.create(WaybillStub.getRequestDTO());

            Mockito.verify(waybillRepository, Mockito.atLeastOnce()).save(captor.capture());

            assertAll(
                    () -> assertEquals(waybillDTO.getProductId(), result.getProduct().getId()),
                    () -> assertEquals(waybillDTO.getUserId(), result.getUser().getId())
            );
        } catch (Exception e) {
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
            Mockito.verify(waybillRepository, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}
