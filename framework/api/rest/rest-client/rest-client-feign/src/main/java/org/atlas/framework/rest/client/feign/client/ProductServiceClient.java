package org.atlas.framework.rest.client.feign.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.framework.api.client.contract.IProductServiceClient;
import org.atlas.framework.rest.client.contract.product.ListProductByIdsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceClient implements IProductServiceClient {

    private final ProductFeignClient feignClient;

    @Override
    public List<ProductDto> listProductByIds(List<Integer> ids) {
        ListProductByIdsResponse response = feignClient.listProductByIds(ids);
        return response.getData();
    }
}
