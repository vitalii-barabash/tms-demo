package com.example.demo.dataaccess.db;

import com.example.demo.dataaccess.model.TestCase;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

    @Query("select t from TestCase t where t.description like %:description%")
    List<TestCase> findByDescription(@Param("description") String description);
}
