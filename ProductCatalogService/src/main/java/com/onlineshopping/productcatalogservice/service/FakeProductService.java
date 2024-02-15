package com.onlineshopping.productcatalogservice.service;

import com.onlineshopping.productcatalogservice.Exceptions.ProductNotFoundException;
import com.onlineshopping.productcatalogservice.dtos.FakeStoreProductDto;
import com.onlineshopping.productcatalogservice.models.Category;
import com.onlineshopping.productcatalogservice.models.Product;
import com.onlineshopping.productcatalogservice.thirdpartyclient.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("FakeStoreProductService")
public class FakeProductService implements ProductService {

    private final FakeStoreClient fakeStoreClient;

    @Autowired
    public FakeProductService(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return getProductFromFakeStoreDto(fakeStoreClient.getProductById(id));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreClient.getAllProducts()) {
            productList.add(getProductFromFakeStoreDto(fakeStoreProductDto));
        }
        return productList;
    }

    @Override
    public Product deleteProductById(Long id) {
        return getProductFromFakeStoreDto(fakeStoreClient.deleteProductById(id));
    }

    @Override
    public Product addProduct(Product product) {
        return getProductFromFakeStoreDto(fakeStoreClient.addProduct(getDTOFromProduct(product)));
    }

    @Override
    public Product updateProduct(Long id) {
        return getProductFromFakeStoreDto(fakeStoreClient.updateProduct(id));
    }


    public Product getProductFromFakeStoreDto(FakeStoreProductDto fakeStoreProductDto) {
        Category category = Category.builder().build();
        category.setTitle(fakeStoreProductDto.getCategory());
        Product product = Product.builder()
                .description(fakeStoreProductDto.getDescription())
                .price(fakeStoreProductDto.getPrice())
                .category(category)
                .build();
        product.setId(new UUID(fakeStoreProductDto.getId(),0L));
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
