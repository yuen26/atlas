package org.atlas.framework.rest.server.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atlas.business.order.application.contract.command.ExportOrderCommand;
import org.atlas.business.order.application.contract.command.GetOrderCommand;
import org.atlas.business.order.application.contract.command.GetOrderStatusCommand;
import org.atlas.business.order.application.contract.command.ImportOrderCommand;
import org.atlas.business.order.application.contract.command.ListOrderCommand;
import org.atlas.business.order.application.contract.command.PlaceOrderCommand;
import org.atlas.business.order.application.contract.model.OrderDto;
import org.atlas.business.order.domain.shared.enums.FileType;
import org.atlas.business.order.domain.shared.enums.OrderStatus;
import org.atlas.commons.utils.DateUtil;
import org.atlas.commons.utils.paging.PageDto;
import org.atlas.framework.command.gateway.CommandGateway;
import org.atlas.framework.rest.server.core.response.SkipRestResponseHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CommandGateway commandGateway;

    @GetMapping
    public PageDto<OrderDto> listOrder(@Valid ListOrderCommand command) throws Exception {
        return commandGateway.send(command);
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable("id") Integer id) throws Exception {
        GetOrderCommand command = new GetOrderCommand(id);
        return commandGateway.send(command);
    }

    @GetMapping("/{id}/status")
    public OrderStatus getOrderStatus(@PathVariable("id") Integer id) throws Exception {
        GetOrderStatusCommand command = new GetOrderStatusCommand(id);
        return commandGateway.send(command);
    }

    @PostMapping("/place")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer placeOrder(@Valid @RequestBody PlaceOrderCommand command) throws Exception {
        return commandGateway.send(command);
    }

    @PostMapping("/import")
    public void importOrder(@RequestParam("file") MultipartFile file,
                            @RequestParam("fileType") FileType fileType) throws Exception {
        byte[] fileContent = file.getBytes();
        ImportOrderCommand command = new ImportOrderCommand(fileType, fileContent);
        commandGateway.send(command);
    }

    @GetMapping("/export")
    @SkipRestResponseHandler
    public ResponseEntity<byte[]> export(@Valid ExportOrderCommand command) throws Exception {
        byte[] bytes = commandGateway.send(command);
        HttpHeaders headers = new HttpHeaders();
        String fileName = buildFileName(command);
        headers.add("Content-Disposition", "attachment; filename=" + fileName);
        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(bytes);
    }

    private String buildFileName(ExportOrderCommand command) {
        return "export-order-" + DateUtil.now("yyyyMMddHHmmss") + "." + command.getFileType().getExtension();
    }
}
