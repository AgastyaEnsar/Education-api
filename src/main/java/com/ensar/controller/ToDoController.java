
package com.ensar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ensar.entity.ToDo;
import com.ensar.request.dto.ToDoRequestDto;
import com.ensar.service.ToDoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(tags = "ToDo Management")
@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/todos")
public class ToDoController {

    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @ApiOperation(value = "Get ToDo by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getToDoById(
            @ApiParam(value = "ID of the ToDo to retrieve", required = true)
            @PathVariable String id) {
        ToDo toDo = toDoService.getToDoById(id);
        return ResponseEntity.ok(toDo);
    }

    @ApiOperation(value = "Get all ToDos")
    @GetMapping("/")
    public ResponseEntity<Map<String, List<ToDo>>> getAllToDos() {
        List<ToDo> toDos = toDoService.getAllToDos();
        Map<String, List<ToDo>> response = new HashMap<>();
        response.put("todos", toDos);
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Create a new ToDo")
    @PostMapping("/")
    public ResponseEntity<ToDo> createToDo(
            @Valid @RequestBody ToDoRequestDto toDoRequestDto) {
        ToDo savedToDo = toDoService.createOrUpdateToDo(Optional.empty(), toDoRequestDto);
        return ResponseEntity.ok(savedToDo);
    }

    @ApiOperation(value = "Update an existing ToDo")
    @PutMapping("/{id}")
    public ResponseEntity<ToDo> updateToDo(
            @ApiParam(value = "ID of the ToDo to update", required = true)
            @PathVariable String id,
            @Valid @RequestBody ToDoRequestDto toDoRequestDto) {
        ToDo updatedToDo = toDoService.createOrUpdateToDo(Optional.of(id), toDoRequestDto);
        return ResponseEntity.ok(updatedToDo);
    }

    @ApiOperation(value = "Delete a ToDo by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(
            @ApiParam(value = "ID of the ToDo to delete", required = true)
            @PathVariable String id) {
        toDoService.deleteToDo(id);
        return ResponseEntity.ok().build();
    }
}

