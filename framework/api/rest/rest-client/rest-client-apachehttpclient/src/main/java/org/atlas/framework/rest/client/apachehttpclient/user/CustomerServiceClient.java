package org.atlas.framework.rest.client.apachehttpclient.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.atlas.business.user.application.contract.model.CustomerDto;
import org.atlas.framework.api.client.contract.user.ICustomerServiceClient;
import org.atlas.framework.rest.client.apachehttpclient.core.HttpClientService;
import org.atlas.framework.rest.client.contract.user.ListCustomerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceClient implements ICustomerServiceClient {

    @Value("${app.rest.user.base-url:http://localhost:8081}")
    private String baseUrl;

    private final HttpClientService service;

    @Override
    public List<CustomerDto> listCustomer(List<Integer> ids) {
        String url = String.format("%s/api/customers", baseUrl);

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("ids", StringUtils.join(ids, ","));

        ListCustomerResponse response = service.doGet(url, paramsMap, null, ListCustomerResponse.class);
        return response.getData();
    }
}
