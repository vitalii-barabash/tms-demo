package com.example.demo.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("query")
public interface QueryServiceClient {

    @PostMapping(value = "/query", consumes = "application/json")
    QueryResult query(@RequestBody Query query);
}
