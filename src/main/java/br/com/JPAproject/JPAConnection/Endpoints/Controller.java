package br.com.JPAproject.JPAConnection.Endpoints;
import br.com.JPAproject.JPAConnection.entity.Users;
import br.com.JPAproject.JPAConnection.service.UsersService;
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
    private UsersService usersService;


    @GetMapping("/getAll")
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            List<Users> usersList = usersService.getAllUsers();

            if (usersList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(usersList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Users> getById(@PathVariable Long id){
       Optional<Users> userData = usersService.getUserById(id);

       if (userData.isPresent()){
           return new ResponseEntity<>(userData.get(), HttpStatus.OK);
       }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addUser")
    public ResponseEntity<Users> addUser(@RequestBody Users users) {
        try {
            Users user = usersService.createUser(users);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/updateUser")
    public ResponseEntity<Users> updateUsers(@RequestBody Users users){
        Optional<Users> updatedUser = usersService.updateUser(users);

        if (updatedUser.isPresent()){
            return new ResponseEntity<>(updatedUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        boolean deleted = usersService.deleteUser(id);
        
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

