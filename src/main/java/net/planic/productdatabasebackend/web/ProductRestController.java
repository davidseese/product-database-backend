package net.planic.productdatabasebackend.web;

import net.planic.productdatabasebackend.service.ProductService;
import net.planic.productdatabasebackend.web.api.Product;
import net.planic.productdatabasebackend.web.api.ProductManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "products")
    public ResponseEntity<List<Product>> fetchProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping(path = "product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        var product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
    @PostMapping(path = "create")
    public ResponseEntity<String> createProduct(@RequestBody ProductManipulationRequest request) throws URISyntaxException {
        var product = productService.createProduct(request);
        URI uri = new URI("/api/v1/products/" + product.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "edit/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody ProductManipulationRequest request){
        var product = productService.updateProduct(id,request);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        boolean productFound = productService.deleteProduct(id);
        return productFound ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
