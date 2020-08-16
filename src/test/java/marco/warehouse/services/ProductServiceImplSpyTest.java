package marco.warehouse.services;

import marco.warehouse.domain.Product;
import marco.warehouse.repositories.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplSpyTest {
    @Spy
    private ProductServiceImpl prodServiceSpy;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private Product product;

    @Test(expected=NullPointerException.class)
    public void shouldThrowNullPointerException_whenGetProductBySKUIsCalledWithoutContext() throws Exception {
        //Act
        Product retrievedProduct = prodServiceSpy.getProductBySKU(5);
        //Assert
        assertThat(retrievedProduct, is(equalTo(product)));
    }
    @Test
    public void shouldThrowNullPointerException_whenSaveProductIsCalledWithoutContext() throws Exception {
        //Arrange
        Mockito.doReturn(product).when(productRepository).save(product);
        //Act
        Product savedProduct = prodServiceSpy.saveProduct(product);
        //Assert
        assertThat(savedProduct, is(equalTo(product)));
    }

    @Test
    public void shouldVerifyThatGetProductBySKUCalled() throws Exception {
        //Arrange
        Mockito.doReturn(product).when(prodServiceSpy).getProductBySKU(5);
        //Act
        Product retrievedProduct = prodServiceSpy.getProductBySKU(5);
        //Assert
        Mockito.verify(prodServiceSpy).getProductBySKU(5);
    }
    @Test
    public void shouldVerifyThatSaveProductSKUNotCalled() throws Exception {
        //Arrange
        Mockito.doReturn(product).when(prodServiceSpy).getProductBySKU(5);
        //Act
        Product retrievedProduct = prodServiceSpy.getProductBySKU(5);
        //Assert
        Mockito.verify(prodServiceSpy,never()).saveProduct(product);
    }
}