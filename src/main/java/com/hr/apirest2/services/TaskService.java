package com.hr.apirest2.services;

import com.hr.apirest2.models.Task;
import com.hr.apirest2.models.User;
import com.hr.apirest2.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException
                ("Task not found, Id : "+ id + " Type: "+ Task.class.getName()));
    }

    public List<Task> findAllByUserId(Long userId){
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }


    @Transactional
    public Task create (Task obj){
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);    //make sure ID is not passed
        obj.setUser(user);  //set task associated with user
        obj = this.taskRepository.save(obj);    //save task
        return obj;
    }

    @Transactional
    public Task update(Task obj){
        Task newObj = findById(obj.getId());//get object to update
        newObj.setDescription(obj.getDescription());    //set desc with get desc from front
        return this.taskRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        //don't need try catch because is not primary key of nothing
        //delete user need try catch
        try{
            this.taskRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Cannot delete related entities");
        }
    }
}
