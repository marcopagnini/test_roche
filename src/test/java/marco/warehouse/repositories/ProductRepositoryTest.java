package marco.warehouse.repositories;

import marco.warehouse.configuration.RepositoryConfiguration;
import marco.warehouse.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class ProductRepositoryTest {
    private ProductRepository productRepository;
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Test
    public void testSaveProduct(){
        //setup product
        Product product = new Product();
        product.setName("Shirt 1");
        product.setPrice(new BigDecimal("18.95"));
        product.setCreatedAt(LocalDateTime.now());
        //save product, verify has ID value after save
        assertNull(product.getSku()); //null before save
        productRepository.save(product);
        assertNotNull(product.getSku()); //not null after save
        //fetch from DB
        Product fetchedProduct = productRepository.findById(product.getSku()).orElse(null);
        //should not be null
        assertNotNull(fetchedProduct);
        //should equal
        assertEquals(product.getSku(), fetchedProduct.getSku());
        assertEquals(product.getName(), fetchedProduct.getName());
        //update description and save
        fetchedProduct.setName("New Name");
        productRepository.save(fetchedProduct);
        //get from DB, should be updated
        Product fetchedUpdatedProduct = productRepository.findById(fetchedProduct.getSku()).orElse(null);
        assertEquals(fetchedProduct.getName(), fetchedUpdatedProduct.getName());
        //verify count of products in DB
        long productCount = productRepository.count();
        assertEquals(productCount, 1);
        //get all products, list should only have one
        Iterable<Product> products = productRepository.findAll();
        int count = 0;
        for(Product p : products){
            count++;
        }
        assertEquals(count, 1);
    }
}
