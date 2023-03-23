package com.hr.apirest2.repositories;


import com.hr.apirest2.models.Task;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //Optional to handle something that is null
//    Optional <Task> findById(Long id);

    List<Task> findByUser_Id(Long id);


//    @Query(value = "SELECT t FROM Task t WHERE t.user.id =  :id")
//    List<Task> findByUserId(@Param("id") Long id);



//    @Query(value = "SELECT * FROM task t WHERE t.user_id" , nativeQuery = true)
//    List<Task> findByUserId(@Param("id") Long id);


}
