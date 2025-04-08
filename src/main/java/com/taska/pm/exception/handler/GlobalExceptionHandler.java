package com.taska.pm.exception.handler;

import com.taska.pm.exception.ProjectNotFoundException;
import com.taska.pm.exception.TaskNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String projectNotFound(ProjectNotFoundException e) {
        log.error(e.getMessage(), e);
        return "error";
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String taskNotFound(TaskNotFoundException e) {
        log.error(e.getMessage(), e);
        return "error";
    }
}
