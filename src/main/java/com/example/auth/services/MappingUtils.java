package com.example.auth.services;
import com.example.auth.dto.TaskUserDTO;
import com.example.auth.entity.TaskUser;
import org.springframework.stereotype.Component;

@Component
public class MappingUtils {
    public TaskUserDTO mapToDto(TaskUser entity){
        TaskUserDTO taskUserDTO= new TaskUserDTO();
        taskUserDTO.setUserName(entity.getUserName());
        taskUserDTO.setUserEmail(entity.getEmail());
        taskUserDTO.setTaskList(entity.getTaskList());
        return taskUserDTO;
    }
}
