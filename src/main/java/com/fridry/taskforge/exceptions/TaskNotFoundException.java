package com.fridry.taskforge.exceptions;

import java.io.Serial;

public class TaskNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1;

    public TaskNotFoundException(String message) {
        super(message);
    }
}
