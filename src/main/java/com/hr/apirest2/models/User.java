package com.hr.apirest2.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = User.TABLE_NAME)
public class User {

    public interface CreateUser{
            //create this empty interfaces to guarantee
        //that is respected NotNull, NotEmpty and Size
    }

    public interface UpdateUser{

    }

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)//id 1,2,3,4 autoincrement
    @Column(name = "id", unique = true)
    private Long id;//use Integer to not get null error, Long to not get bigger number

    @Column(name = "username", length = 100, nullable = false,unique = true)
    @NotNull (groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(groups = CreateUser.class,min = 2, max = 100)
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    @NotNull (groups = {CreateUser.class,UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class,UpdateUser.class})
    @Size(groups = {CreateUser.class,UpdateUser.class},min = 4, max = 100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    //OneToMany one user can have many tasks
    //mappedBy to define who is using, use var name, not column _id
    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();


    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public List<Task> getTask() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        User user = (User) obj;
//        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
        if (obj == this){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (!(obj instanceof User)){
            return false;
        }
        User other = (User) obj;
        if (this.id == null){
            if (other.id != null){
                return false;
            } else if (!this.id.equals(other.id)){
                return false;
            }
        }
        return Objects.equals(id, other.id) && Objects.equals(username, other.username) && Objects.equals(password, other.password);
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * prime + ((this.id == null) ? 0 : this.id.hashCode());
        return result;

    }
}
