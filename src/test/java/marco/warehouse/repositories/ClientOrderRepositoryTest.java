package marco.warehouse.repositories;

import marco.warehouse.configuration.RepositoryConfiguration;
import marco.warehouse.domain.ClientOrder;
import marco.warehouse.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class ClientOrderRepositoryTest {
    private ClientOrderRepository clientOrderRepository;
    @Autowired
    public void setClientOrderRepository(ClientOrderRepository clientOrderRepository) {
        this.clientOrderRepository = clientOrderRepository;
    }
    @Test
    public void testSaveOrder(){
        //setup product
        Product product = new Product();
        product.setName("Shirt 5");
        product.setPrice(new BigDecimal("28.37"));
        product.setCreatedAt(LocalDateTime.now());
        //setup client order
        ClientOrder order = new ClientOrder();
        order.setBuyerEmail("abc@xyz.com");
        order.setBuyingDate(new Date(System.currentTimeMillis()));
        order.addProduct(product);

        //save product, verify has ID value after save
        assertNull(order.getId()); //null before save
        clientOrderRepository.save(order);
        assertNotNull(order.getId()); //not null after save
        assertFalse(order.getProductList().isEmpty()); //product list not empty after save
        //fetch from DB
        ClientOrder fetchedOrder = clientOrderRepository.findById(order.getId()).orElse(null);
        //should not be null
        assertNotNull(fetchedOrder);
        //should equal
        assertEquals(order.getId(), fetchedOrder.getId());
        assertEquals(order.getBuyerEmail(), fetchedOrder.getBuyerEmail());
        assertEquals(order.getBuyingDate(), fetchedOrder.getBuyingDate());
        //update description and save
        fetchedOrder.setBuyerEmail("marco@abc.com");
        clientOrderRepository.save(fetchedOrder);
        //get from DB, should be updated
        ClientOrder fetchedUpdatedOrder = clientOrderRepository.findById(fetchedOrder.getId()).orElse(null);
        assertEquals(fetchedOrder.getBuyerEmail(), fetchedUpdatedOrder.getBuyerEmail());
        //verify count of products in DB
        long productCount = clientOrderRepository.count();
        assertEquals(productCount, 1);
        //get all products, list should only have one
        Iterable<ClientOrder> orders = clientOrderRepository.findAll();
        int count = 0;
        for(ClientOrder p : orders){
            count++;
        }
        assertEquals(count, 1);
    }
}
