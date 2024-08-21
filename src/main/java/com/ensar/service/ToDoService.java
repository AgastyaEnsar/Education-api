package com.ensar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensar.entity.ToDo;
import com.ensar.entity.Topic;
import com.ensar.entity.User;
import com.ensar.repository.ToDoRepository;
import com.ensar.repository.TopicRepository;
import com.ensar.repository.UserRepository;
import com.ensar.request.dto.ToDoRequestDto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ToDoService {

    private final ToDoRepository toDoRepository;
   

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;       
    }

    public ToDo createOrUpdateToDo(Optional<String> toDoId, ToDoRequestDto toDoRequestDto) {
        ToDo toDo;
        if (toDoId.isPresent()) {
            toDo = toDoRepository.findById(toDoId.get())
                .orElseThrow(() -> new RuntimeException("ToDo with id " + toDoId.get() + " not found"));
        } else {
            toDo = new ToDo();
        }        
        toDo.setTitle(toDoRequestDto.getTitle());
        toDo.setDescription(toDoRequestDto.getDescription());       
        toDo.setStatus(ToDo.ToDoStatus.valueOf(toDoRequestDto.getStatus().name()));          
        toDo.setCreatedDateTime(new Timestamp(System.currentTimeMillis()));
        return toDoRepository.save(toDo);
    }

    public ToDo getToDoById(String toDoId) {
        return toDoRepository.findById(toDoId)
                .orElseThrow(() -> new RuntimeException("ToDo with id " + toDoId + " not found"));
    }

    public List<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    public void deleteToDo(String toDoId) {
        ToDo toDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new RuntimeException("ToDo with id " + toDoId + " not found"));
        toDoRepository.delete(toDo);
    }
}

