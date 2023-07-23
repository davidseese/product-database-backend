package net.planic.productdatabasebackend.service;

import net.planic.productdatabasebackend.persistence.ProductEntity;
import net.planic.productdatabasebackend.persistence.ProductRepository;
import net.planic.productdatabasebackend.web.api.Product;
import net.planic.productdatabasebackend.web.api.ProductManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        List<ProductEntity> products = productRepository.findAll();
        return products.stream().map(this::productEntityToProduct).collect(Collectors.toList());
    }

    public Product getProductById(Long id){
        var product = productRepository.findById(id).get();
        return productEntityToProduct(product);
    }
    public boolean deleteProduct(Long id){
        if(!productRepository.existsById(id)){
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

    public Product updateProduct(Long id,ProductManipulationRequest request){
        var entityOtionalEmpty = productRepository.findById(id);
        if (entityOtionalEmpty.isEmpty()) {
            return null;
        }

        var productEntity = entityOtionalEmpty.get();
        productEntity.setName(request.getName());
        productEntity.setPrice(request.getPrice());
        productEntity = productRepository.save(productEntity);
        return productEntityToProduct(productEntity);
    }

    public Product createProduct(ProductManipulationRequest request){
        var productEntity = new ProductEntity(request.getName(),request.getPrice());
        productEntity = productRepository.save(productEntity);
        return productEntityToProduct(productEntity);
    }
    public Product productEntityToProduct(ProductEntity productEntity){
        return new Product(productEntity.getId(),productEntity.getName(),productEntity.getPrice());
    }
}