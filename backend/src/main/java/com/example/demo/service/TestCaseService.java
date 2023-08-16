package com.example.demo.service;

import com.example.demo.dataaccess.db.TestCaseRepository;
import com.example.demo.dataaccess.mapper.TestCaseMapper;
import com.example.demo.dataaccess.model.TestCase;
import com.example.demo.rest.model.TestCaseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestCaseService {

    private TestCaseRepository testCaseRepository;
    private TestCaseMapper mapper;

    public List<TestCaseDto> getAll() {
        return testCaseRepository.findAll().stream()
                .map(testCaseEntity -> mapper.toDto(testCaseEntity))
                .toList();
    }

    public TestCaseDto save(TestCaseDto testCaseDto) {
        TestCase entity = mapper.toEntity(testCaseDto);
        TestCase testCase = testCaseRepository.save(entity);
        return mapper.toDto(testCase);
    }

    public void delete(Long id) {
        testCaseRepository.deleteById(id);
    }
}
