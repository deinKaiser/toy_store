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
import yarema.project.toystore.dto.product.RequestProductDTO;
import yarema.project.toystore.dto.product.ResponseProductDTO;
import yarema.project.toystore.entity.Product;
import yarema.project.toystore.repository.ProductRepository;
import yarema.project.toystore.repository.TypeRepository;
import yarema.project.toystore.repository.WarehouseRepository;
import yarema.project.toystore.stubs.ProductStub;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
public class ProductServiceV2Test {
    private ProductServiceV2 productServiceV2;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private TypeRepository typeRepository;
    private WarehouseRepository warehouseRepository;

    @BeforeEach
    void setup() {
        productServiceV2 = new ProductServiceV2(new ModelMapper(), productRepository, typeRepository, warehouseRepository);
    }

    @Test
    void findAll() {
        Product product = ProductStub.getProduct();

        Mockito.when(productRepository.findAll()).thenReturn(List.of(product));

        assertTrue(productServiceV2.findAll().size() > 0);
    }

    @Test
    void findById() {
        Product product = ProductStub.getProduct();

        //Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(product));

        try {
            ResponseProductDTO result = productServiceV2.findById(ProductStub.ID);
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
    void update() {
        var captor = ArgumentCaptor.forClass(Product.class);
        RequestProductDTO productDTO = ProductStub.getRequestDTO();
        try {
            ResponseProductDTO result = productServiceV2.update(ProductStub.ID, ProductStub.getRequestDTO());
            Mockito.verify(productRepository, Mockito.atLeastOnce()).save(captor.capture());

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
    void create() {
        var captor = ArgumentCaptor.forClass(Product.class);
        RequestProductDTO productDTO = ProductStub.getRequestDTO();

        try {
            ResponseProductDTO result = productServiceV2.create(ProductStub.getRequestDTO());

            Mockito.verify(productRepository, Mockito.atLeastOnce()).save(captor.capture());

            assertAll(
                    () -> assertEquals(productDTO.getTypeId(), result.getType().getId()),
                    () -> assertEquals(productDTO.getWarehouseId(), result.getWarehouse().getId())
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
            productServiceV2.deleteById(ProductStub.ID);

            var captor = ArgumentCaptor.forClass(Long.class);
            productServiceV2.create(ProductStub.getRequestDTO());
            Mockito.verify(productRepository, Mockito.atLeastOnce()).deleteById(captor.capture());
        } catch (NotFoundException e) {
            String expectedMessage = "not found";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));
        }
    }
}
