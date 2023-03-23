package com.hr.apirest2.repositories;

import com.hr.apirest2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



// to create a repository, extend JpaRepository or CrudRepository class
// with the model and the model identifier type
// contains all methods, i can overwrite
// Jpa as paginator
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
