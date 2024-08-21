
package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.School;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, String> {

    List<School> findByName(String name);

    List<School> findByCity(String city);

    boolean existsByName(String name);

    boolean existsByCity(String city);
}
