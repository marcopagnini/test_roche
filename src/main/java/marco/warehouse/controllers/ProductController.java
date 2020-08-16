package marco.warehouse.controllers;

import marco.warehouse.domain.Product;
import marco.warehouse.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/product")
@Api(value="onlinestore", tags="Operations pertaining to products in Online Store")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "View a list of available products",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public Iterable<Product> list(Model model){
        Iterable<Product> productList = productService.listAllProducts();
        return productList;
    }
    @ApiOperation(value = "Search a product by SKU",response = Product.class)
    @RequestMapping(value = "/show/{sku}", method= RequestMethod.GET, produces = "application/json")
    public Product showProduct(@PathVariable Integer sku, Model model){
       Product product = productService.getProductBySKU(sku);
        return product;
    }

    @ApiOperation(value = "Add a product")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/plain")
    public ResponseEntity addProduct(@RequestParam String name, @RequestParam BigDecimal price){
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setCreatedAt(LocalDateTime.now());
        productService.saveProduct(newProduct);
        return new ResponseEntity("Product saved successfully with SKU " + newProduct.getSku(), HttpStatus.OK);
    }

    @ApiOperation(value = "Update a product")
    @RequestMapping(value = "/update/{sku}", method = RequestMethod.PUT, produces = "text/plain")
    public ResponseEntity updateProduct(@PathVariable Integer sku, @RequestParam String name, @RequestParam BigDecimal price){
        Product storedProduct = productService.getProductBySKU(sku);
        storedProduct.setName(name);
        storedProduct.setPrice(price);
        storedProduct.setUpdatedAt(LocalDateTime.now());
        productService.saveProduct(storedProduct);
        return new ResponseEntity("Product updated successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a product")
    @RequestMapping(value="/delete/{sku}", method = RequestMethod.DELETE, produces = "text/plain")
    public ResponseEntity delete(@PathVariable Integer sku){
        //productService.deleteProduct(sku);
        Product storedProduct = productService.getProductBySKU(sku);
        storedProduct.setActive(false);
        productService.saveProduct(storedProduct);
        return new ResponseEntity("Product deleted successfully", HttpStatus.OK);
    }

}
