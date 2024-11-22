package com.example.auth.dto;
import com.example.auth.entity.Task;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class TaskUserDTO {
    private String userName;
    private String userEmail;
    private List<Task> taskList;
}
