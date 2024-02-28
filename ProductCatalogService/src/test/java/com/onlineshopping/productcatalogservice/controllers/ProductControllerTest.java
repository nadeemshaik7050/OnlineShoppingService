package com.onlineshopping.productcatalogservice.controllers;

import com.onlineshopping.productcatalogservice.Exceptions.ProductNotFoundException;
import com.onlineshopping.productcatalogservice.models.Product;
import com.onlineshopping.productcatalogservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;
    @MockBean
    private ProductService productService;

    @Test
    void getProductById() throws ProductNotFoundException {
        //Arrange
        Product dummyProd = Product.builder().build();
        dummyProd.setId(1L);
        dummyProd.setTitle("dummyProd");
        when(productService.getProductById(1L)).thenReturn(dummyProd);

        //Act
        Product prod = productController.getProductById(1L);

        //Assert
        assertEquals(1L, prod.getId());


    }

    @Test
    void getProductByIdThrowsException() throws ProductNotFoundException {
        //Arrange
        when(productService.getProductById(99L)).thenThrow(new ProductNotFoundException("Product Not Found"));

        //Act
        assertThrows(ProductNotFoundException.class, () -> productController.getProductById(99L));

    }
}