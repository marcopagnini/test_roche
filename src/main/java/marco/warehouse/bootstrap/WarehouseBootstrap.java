package marco.warehouse.bootstrap;

import marco.warehouse.domain.ClientOrder;
import marco.warehouse.domain.Product;
import marco.warehouse.repositories.ClientOrderRepository;
import marco.warehouse.repositories.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class WarehouseBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductRepository productRepository;
    private ClientOrderRepository clientOrderRepository;

    private final Logger log = LogManager.getLogger(WarehouseBootstrap.class);

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setClientOrderRepository(ClientOrderRepository clientOrderRepository) {
        this.clientOrderRepository = clientOrderRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
        loadOrder();
    }

    private void loadProducts() {
        Product shirt1 = new Product();
        shirt1.setName("Shirt 1");
        shirt1.setPrice(new BigDecimal("18.95"));
        shirt1.setCreatedAt(LocalDateTime.now());
        productRepository.save(shirt1);

        log.info("Saved Shirt - sku: " + shirt1.getSku());

        Product shirt2 = new Product();
        shirt2.setName("Shirt 2");
        shirt2.setPrice(new BigDecimal("39.95"));
        shirt2.setCreatedAt(LocalDateTime.now());
        productRepository.save(shirt2);

        log.info("Saved Shirt - sku: " + shirt2.getSku());

        Product trousers = new Product();
        trousers.setName("Trousers 1");
        trousers.setCreatedAt(LocalDateTime.now());
        trousers.setPrice(new BigDecimal("70.95"));
        productRepository.save(trousers);

        log.info("Saved Trousers - sku:" + trousers.getSku());
    }

    private void loadOrder() {
        ClientOrder order = new ClientOrder();
        order.setBuyerEmail("marco@google.com");
        for (Product p : productRepository.findAll()) {
            order.addProduct(p);
        }
        order.setBuyingDate(new Date(System.currentTimeMillis()));
        clientOrderRepository.save(order);

        log.info("Saved Order - ID: " + order.getId());
    }
}



