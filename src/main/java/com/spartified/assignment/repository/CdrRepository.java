package com.spartified.assignment.repository;

import com.spartified.assignment.entity.CdrRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CdrRepository extends JpaRepository<CdrRecordEntity, Long> {
    // additional queries if needed
}
