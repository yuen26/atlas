package org.atlas.framework.rest.server.product.controller;

import lombok.RequiredArgsConstructor;
import org.atlas.business.product.application.contract.command.ListProductCommand;
import org.atlas.business.product.application.contract.model.ProductDto;
import org.atlas.framework.command.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final CommandGateway commandGateway;

    @GetMapping
    public List<ProductDto> listProduct(@RequestParam("ids") List<Integer> ids) throws Exception {
        ListProductCommand command = new ListProductCommand(ids);
        return commandGateway.send(command);
    }
}
