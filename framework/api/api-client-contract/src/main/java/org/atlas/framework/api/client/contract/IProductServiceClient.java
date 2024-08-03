package org.atlas.framework.api.client.contract;

import org.atlas.business.product.application.contract.model.ProductDto;

import java.util.List;

public interface IProductServiceClient {

    List<ProductDto> listProductByIds(List<Integer> ids);
}
