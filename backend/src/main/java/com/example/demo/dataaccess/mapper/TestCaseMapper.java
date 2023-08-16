package com.example.demo.dataaccess.mapper;

import com.example.demo.dataaccess.model.TestCase;
import com.example.demo.rest.model.TestCaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TestCaseMapper {

    @Mapping(source = "steps", target = "steps")
    TestCaseDto toDto(TestCase testCase);

    @Mapping(source = "steps", target = "steps")
    TestCase toEntity(TestCaseDto testCaseDto);
}
