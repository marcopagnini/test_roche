package marco.warehouse.services;

import marco.warehouse.domain.ClientOrder;
import marco.warehouse.repositories.ClientOrderRepository;
import marco.warehouse.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ClientOrderRepository clientOrderRepository;
    private ProductRepository productRepository;

    @Autowired
    public void setClientOrderRepository(ClientOrderRepository clientOrderRepository) {
        this.clientOrderRepository = clientOrderRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<ClientOrder> getAllClientOrdersInRange(Date start, Date end) {
        logger.debug("getAllClientOrdersInRange called");
        return clientOrderRepository.findAllInRange(start, end);
    }

    @Override
    public ClientOrder saveNewClientOrder(String email, Integer[] productSkuList) {
        logger.debug("saveNewClientOrder called");
        ClientOrder order = new ClientOrder();
        order.setBuyerEmail(email);
        order.setBuyingDate(new Date(System.currentTimeMillis()));
        Arrays.stream(productSkuList).forEach(
                sku -> productRepository.findById(sku).ifPresent(order::addProduct));
        return clientOrderRepository.save(order);
    }

    @Override
    public ClientOrder getClientOrderById(Integer id) {
        logger.debug("getClientOrderById called");
        return clientOrderRepository.findById(id).orElse(null);
    }
}
