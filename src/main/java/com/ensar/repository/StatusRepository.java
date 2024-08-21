package com.ensar.repository;

import com.ensar.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, String> {

    @Query("SELECT s.name FROM Status s")
    List<String> findAllStatusNames();
}
