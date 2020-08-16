package marco.warehouse.services;

import marco.warehouse.domain.Product;
import marco.warehouse.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;



public class ProductServiceImplMockTest {

    private ProductServiceImpl productServiceImpl;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private Product product;
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        productServiceImpl=new ProductServiceImpl();
        productServiceImpl.setProductRepository(productRepository);
    }
    @Test
    public void shouldReturnProduct_whenGetProductByIdIsCalled() throws Exception {
        // Arrange
        when(productRepository.findById(5)).thenReturn(Optional.of(product));
        // Act
        Product retrievedProduct = productServiceImpl.getProductBySKU(5);
        // Assert
        assertThat(retrievedProduct, is(equalTo(product)));

    }
    @Test
    public void shouldReturnProduct_whenSaveProductIsCalled() throws Exception {
        // Arrange
        when(productRepository.save(product)).thenReturn(product);
        // Act
        Product savedProduct = productServiceImpl.saveProduct(product);
        // Assert
        assertThat(savedProduct, is(equalTo(product)));
    }
}