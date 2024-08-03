package org.atlas.framework.rest.server.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atlas.business.product.application.contract.command.ListCategoryCommand;
import org.atlas.business.product.application.contract.model.CategoryDto;
import org.atlas.framework.command.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CommandGateway commandGateway;

    @GetMapping
    public List<CategoryDto> listCategory(@Valid ListCategoryCommand request) throws Exception {
        return commandGateway.send(request);
    }
}
