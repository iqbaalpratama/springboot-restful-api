package com.iqbaal.restful.repository;
import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iqbaal.restful.entity.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, BigInteger> {
    List<Tutorial> findByPublished(Boolean published);
    List<Tutorial> findByTitleContainingIgnoreCase(String title);
}
