package com.onlineshopping.productcatalogservice.thirdpartyclient;

import com.onlineshopping.productcatalogservice.Exceptions.ProductNotFoundException;
import com.onlineshopping.productcatalogservice.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {


    private final RestTemplateBuilder restTemplateBuilder;

    private final String getProdUrl;
    private final String genericUrl;

    @Autowired
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder,
                           @Value("${fakestore.url.specificIdurl}") String getProdUrl,
                           @Value("${fakestore.url.genericurl}") String genericUrl) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.genericUrl = genericUrl;
        this.getProdUrl = getProdUrl;
    }

    public FakeStoreProductDto getProductById(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        /*
         * getForEntity(getProdUrl, FakeStoreProductDto.class) == Converts Json to Object with help of objectMapper(jackson)
         *
         */
        ResponseEntity<FakeStoreProductDto> entity = restTemplate.getForEntity(getProdUrl, FakeStoreProductDto.class, id);
        if (entity.getBody() == null) {
            throw new ProductNotFoundException("No Product Available with Given Id");
        }
        return entity.getBody();
    }

    public FakeStoreProductDto[] getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> entity = restTemplate.getForEntity(genericUrl
                , FakeStoreProductDto[].class);
        return entity.getBody();
    }

    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> entity = restTemplate.execute(getProdUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return entity.getBody();
    }

    public FakeStoreProductDto addProduct(FakeStoreProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> entity = restTemplate.postForEntity(genericUrl, product, FakeStoreProductDto.class);
        return entity.getBody();
    }

}
