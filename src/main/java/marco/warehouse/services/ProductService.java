package marco.warehouse.services;


import marco.warehouse.domain.Product;

public interface ProductService {
    Iterable<Product> listAllProducts();

    Iterable<Product> listAllDeletedProducts();

    Product getProductBySKU(Integer sku);

    Product saveProduct(Product product);

    //void deleteProduct(Integer sku);
}
