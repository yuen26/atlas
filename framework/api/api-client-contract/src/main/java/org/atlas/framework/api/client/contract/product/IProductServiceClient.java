package org.atlas.framework.api.client.contract.product;

import org.atlas.product.application.contract.model.ProductDto;

import java.util.List;

public interface IProductServiceClient {

    List<ProductDto> listProduct(List<Integer> ids);
}
