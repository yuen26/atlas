package org.atlas.framework.rest.client.feign.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.framework.api.client.contract.product.IProductServiceClient;
import org.atlas.framework.rest.client.contract.product.ListProductResponse;
import org.atlas.framework.rest.client.feign.client.ProductFeignClient;
import org.atlas.product.application.contract.model.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceClient implements IProductServiceClient {

    private final ProductFeignClient feignClient;

    @Override
    public List<ProductDto> listProduct(List<Integer> ids) {
        ListProductResponse response = feignClient.listProduct(ids);
        return response.getData();
    }
}
