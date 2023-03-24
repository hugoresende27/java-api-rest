package com.hr.apirest2.services;

import com.hr.apirest2.models.User;
//import com.hr.apirest2.repositories.TaskRepository;
import com.hr.apirest2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private TaskRepository taskRepository;


    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);
        //can be if (user.isPresent())
        return user.orElseThrow(() -> new RuntimeException
                ("User not found ! id : "+ id +", Type : "+ User.class.getName()));
    }


    @Transactional  //to save in DB use always
    public User create(User obj){
        obj.setId(null);                            //to make sure ID is null
        obj = this.userRepository.save(obj);        //save the user
//        this.taskRepository.saveAll(obj.getTask()); //save all tasks of the user
        return obj;
    }

    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());  //only update password
        return this.userRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try{
            this.userRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Cannot delete related entities");
        }
    }

}
