package marco.warehouse.services;

import marco.warehouse.domain.Product;
import marco.warehouse.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> listAllProducts() {
        logger.debug("listAllProducts called");
        return productRepository.findAll();
    }

    @Override
    public Iterable<Product> listAllDeletedProducts() {
        logger.debug("listAllDeletedProducts called");
        return productRepository.findAllDeleted();
    }

    @Override
    public Product getProductBySKU(Integer sku) {
        logger.debug("getProductBySKU called");
        return productRepository.findById(sku).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        logger.debug("saveProduct called");
        return productRepository.save(product);
    }

    /*
    @Override
    public void deleteProduct(Integer sku) {
        logger.debug("deleteProduct called");
        //productRepository.softDelete(sku);
    }
    */
}
