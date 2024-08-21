package com.ensar.request.dto;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel(value = "ToDoRequestDto", description = "DTO to create or update a ToDo")
@Accessors(chain = true)
@Setter
@Getter
public class ToDoRequestDto {

    @ApiModelProperty(notes = "Title of the ToDo", required = true)
    private String title;

    @ApiModelProperty(notes = "Description of the ToDo")
    private String description;

    @ApiModelProperty(notes = "Status of the ToDo", required = true)
    private ToDoStatus status;   

    public enum ToDoStatus {
        not_started,
        in_progress,
        completed
    }

}
