package com.onlineshopping.productcatalogservice.service;

import com.onlineshopping.productcatalogservice.Exceptions.ProductNotFoundException;
import com.onlineshopping.productcatalogservice.dtos.FakeStoreProductDto;
import com.onlineshopping.productcatalogservice.models.Category;
import com.onlineshopping.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeProductService implements ProductService {


    private final RestTemplateBuilder restTemplateBuilder;

    private String getProdUrl = "https://fakestoreapi.com/products/{id}";
    private String genericUrl
 = "https://fakestoreapi.com/products/";

    @Autowired
    public FakeProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        /*
         * getForEntity(getProdUrl, FakeStoreProductDto.class) == Converts Json to Object with help of objectMapper(jackson)
         *
         */
        ResponseEntity<FakeStoreProductDto> entity = restTemplate.getForEntity(getProdUrl, FakeStoreProductDto.class, id);
        if (entity.getBody() == null) {
            throw new ProductNotFoundException("No Product Available with Given Id");
        }
        return getProductFromFakeStoreDto(entity.getBody());
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> entity = restTemplate.getForEntity(genericUrl
, FakeStoreProductDto[].class);
        List<Product> productList = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : entity.getBody()) {
            productList.add(getProductFromFakeStoreDto(fakeStoreProductDto));
        }
        return productList;
    }

    @Override
    public String deleteProductById(Long id) {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> entity=restTemplate.postForEntity(genericUrl,getDTOFromProduct(product),FakeStoreProductDto.class);
        return getProductFromFakeStoreDto(entity.getBody());
    }


    public Product getProductFromFakeStoreDto(FakeStoreProductDto fakeStoreProductDto) {
        Category category = Category.builder().build();
        category.setTitle(fakeStoreProductDto.getCategory());
        Product product = Product.builder()
                .description(fakeStoreProductDto.getDescription())
                .price(fakeStoreProductDto.getPrice())
                .category(category)
                .build();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        return product;
    }

    public FakeStoreProductDto getDTOFromProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto=FakeStoreProductDto.builder()
                .title(product.getTitle())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(product.getCategory().getTitle())
                .build();
        return  fakeStoreProductDto;
    }
}
