package com.example.demo.rest.model;

import java.util.List;
import lombok.Data;

@Data
public class TestCaseDto {

    private Long id;
    private String description;
    private List<String> steps;
}
