package br.com.JPAproject.JPAConnection.Endpoints;
import br.com.JPAproject.JPAConnection.entity.Users;
import br.com.JPAproject.JPAConnection.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class Controller {

    @Autowired
    private RepositoryUser repositoryuser;


    @GetMapping("/getAll")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> UsersList;
        try {
            UsersList = repositoryuser.findAll();


            if (UsersList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(UsersList, HttpStatus.OK);
    }

    @GetMapping("/GetById/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
       Optional<Users> userData = repositoryuser.findById(id);

       if (userData.isPresent()){
           return new ResponseEntity<>(userData.get(), HttpStatus.OK);

       }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addUser")
    public ResponseEntity<Users> addUser(@RequestBody Users users) {
        Users user = repositoryuser.save(users);

        return new ResponseEntity<>(user, HttpStatus.OK) ;
    }


    @PostMapping("/updateUser")
    public ResponseEntity<Users> updateUsers(@RequestBody Users users){

        Optional<Users> user = Optional.of(repositoryuser.save(users));

        if (user.isPresent()){

            return new ResponseEntity<>(user.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteUserById/{id}")
        public ResponseEntity<Users> deleteUserById(@PathVariable Long id){
        repositoryuser.deleteById(id);
        return new ResponseEntity<>( HttpStatus.OK);


            }


}

