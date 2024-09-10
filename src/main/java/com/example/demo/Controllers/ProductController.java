package com.example.demo.Controllers;

import com.example.demo.DTOs.ProductRecordDTO;
import com.example.demo.Repositories.ProductRepository;
import com.example.demo.models.ProductModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDTO, productModel);
        return  ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProduct(){
        var listProducts = productRepository.findAll();
        if(!listProducts.isEmpty()){
            for(var product: listProducts){
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return  ResponseEntity.status(HttpStatus.OK).body(listProducts);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value ="id") UUID id){
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }
        product.get().add(linkTo(methodOn(ProductController.class).getAllProduct()).withSelfRel());
        return  ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    @PutMapping("/products")
    public ResponseEntity<Object> updateProduct(@PathVariable(value ="id") UUID id,
                                                @RequestBody ProductRecordDTO productRecordDTO){

        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }
        var productModel = product.get();
        BeanUtils.copyProperties(productRecordDTO, productModel);
        return  ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @DeleteMapping("/products")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value ="id") UUID id){
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }
        productRepository.delete(product.get());
        return  ResponseEntity.status(HttpStatus.OK).body("Product delected succefully");
    }
}
