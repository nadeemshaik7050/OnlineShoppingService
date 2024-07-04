package com.onlineshopping.productcatalogservice.service;

import com.onlineshopping.productcatalogservice.Exceptions.ProductNotFoundException;
import com.onlineshopping.productcatalogservice.dtos.FakeStoreProductDto;
import com.onlineshopping.productcatalogservice.models.Category;
import com.onlineshopping.productcatalogservice.models.Product;
import com.onlineshopping.productcatalogservice.thirdpartyclient.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("FakeStoreProductService")
public class FakeProductService implements ProductService {

    private final FakeStoreClient fakeStoreClient;

    private final RedisTemplate<String, Object> redisTemplate;

    private final String FKEY = "FPRODUCT";
    @Autowired
    public FakeProductService(FakeStoreClient fakeStoreClient,RedisTemplate<String, Object> redisTemplate) {
        this.fakeStoreClient = fakeStoreClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto  = (FakeStoreProductDto) redisTemplate.opsForHash().get(FKEY, id);
        if(fakeStoreProductDto!=null){
            return getProductFromFakeStoreDto(fakeStoreProductDto);
        }
        fakeStoreProductDto = fakeStoreClient.getProductById(id);
        redisTemplate.opsForHash().put(FKEY, id, fakeStoreProductDto);
        return getProductFromFakeStoreDto(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Object> listFromRedis=redisTemplate.opsForHash().values(FKEY);
        List<Product> productList = new ArrayList<>();

        if(!listFromRedis.isEmpty()){
            for (Object obj : listFromRedis) {
                FakeStoreProductDto fakeStoreProductDto = (FakeStoreProductDto) obj;
                productList.add(getProductFromFakeStoreDto(fakeStoreProductDto));
            }
            return productList;
        }

        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreClient.getAllProducts()) {
            redisTemplate.opsForHash().put(FKEY, fakeStoreProductDto.getId(), fakeStoreProductDto);
            productList.add(getProductFromFakeStoreDto(fakeStoreProductDto));
        }
        return productList;
    }

    @Override
    public Product deleteProductById(Long id) {
        redisTemplate.opsForHash().delete(FKEY, id);
        return getProductFromFakeStoreDto(fakeStoreClient.deleteProductById(id));
    }

    @Override
    public Product addProduct(Product product) {
        redisTemplate.opsForHash().put(FKEY, product.getId(), getDTOFromProduct(product));
        return getProductFromFakeStoreDto(fakeStoreClient.addProduct(getDTOFromProduct(product)));
    }

    @Override
    public Product updateProduct(Long id,Product product) {
        redisTemplate.opsForHash().put(FKEY, id, getDTOFromProduct(product));
        return fakeStoreClient.updateProduct(id,product);
    }

    @Override
    public Page<Product> getAllProducts(int pageNum, int size,String sortBy,String sortOrder) {
        /*
        * Since it is an external API call, we cannot implement pagination and sorting here
        */

        return null;
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
