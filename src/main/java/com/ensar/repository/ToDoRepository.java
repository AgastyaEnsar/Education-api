package com.ensar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensar.entity.ToDo;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, String> {

 }
