package yarema.project.toystore.controller.api.v2;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import yarema.project.toystore.dto.product.RequestProductDTO;
import yarema.project.toystore.dto.product.ResponseProductDTO;
import yarema.project.toystore.entity.Product;
import yarema.project.toystore.service.v2.ProductServiceV2;
import yarema.project.toystore.stubs.ProductStub;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
public class ProductControllerV2Test {
    private ProductControllerV2 productControllerV2;
    @Mock
    private ProductServiceV2 productServiceV2;

    @BeforeEach
    void setup() {
        productControllerV2 = new ProductControllerV2(productServiceV2);
    }

    @Test
    void getAll() {
        Mockito.when(productServiceV2.findAll()).thenReturn(List.of(ProductStub.getResponseDTO()));
        assertTrue(productServiceV2.findAll().size() > 0);
    }

    @Test
    void getById() {
        Product product = ProductStub.getProduct();
        try {
            Mockito.when(productServiceV2.findById(Mockito.any())).thenReturn(ProductStub.getResponseDTO());
            ResponseProductDTO result = productControllerV2.getById(ProductStub.ID);
            assertAll(
                    () -> assertEquals(product.getId(), result.getId()),
                    () -> assertEquals(product.getName(), result.getName())
            );
            productServiceV2.findById(0L);
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void deleteById() {
        try {
            productServiceV2.deleteById(ProductStub.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            productServiceV2.create(ProductStub.getRequestDTO());
            Mockito.verify(productServiceV2, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void update() {
        var captor = ArgumentCaptor.forClass(RequestProductDTO.class);
        var captorId = ArgumentCaptor.forClass(Long.class);
        RequestProductDTO productDTO = ProductStub.getRequestDTO();
        try {
            Mockito.when(productServiceV2.update(Mockito.any(), Mockito.any())).thenReturn(ProductStub.getResponseDTO());

            ResponseProductDTO result = productControllerV2.update(ProductStub.ID, ProductStub.getRequestDTO());
            Mockito.verify(productServiceV2, Mockito.atLeastOnce()).update(captorId.capture(), captor.capture());

            assertAll(
                    () -> assertEquals(productDTO.getName(), result.getName())
            );
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }

    @Test
    void create() throws NotFoundException {
        var captor = ArgumentCaptor.forClass(RequestProductDTO.class);
        RequestProductDTO productDTO = ProductStub.getRequestDTO();

        Mockito.when(productServiceV2.create(Mockito.any())).thenReturn(ProductStub.getResponseDTO());
        try {
            ResponseProductDTO result = productControllerV2.create(ProductStub.getRequestDTO());

            Mockito.verify(productServiceV2, Mockito.atLeastOnce()).create(captor.capture());

            assertAll(
                    () -> assertEquals(productDTO.getName(), result.getName())
            );
        } catch (Exception e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}
