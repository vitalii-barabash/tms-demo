package com.example.demo.rest;

import com.example.demo.external.QueryResult;
import com.example.demo.external.Query;
import com.example.demo.external.QueryServiceClient;
import com.example.demo.rest.model.TestCaseDto;
import com.example.demo.service.TestCaseService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testcases")
@AllArgsConstructor
public class TestCaseController {

    private TestCaseService testCaseService;
    private QueryServiceClient queryServiceClient;

    @GetMapping
    public List<TestCaseDto> getAll() {
        return testCaseService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public TestCaseDto create(@RequestBody TestCaseDto testCaseDto) {
        return testCaseService.save(testCaseDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        testCaseService.delete(id);
    }

    @PostMapping(path = "/q")
    public QueryResult query(@RequestBody Query query) {
        return queryServiceClient.query(query);
    }
}
