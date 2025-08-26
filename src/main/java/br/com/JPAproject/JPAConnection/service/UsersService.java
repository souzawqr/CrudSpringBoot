package br.com.JPAproject.JPAConnection.service;

import br.com.JPAproject.JPAConnection.entity.Users;
import br.com.JPAproject.JPAConnection.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private RepositoryUser repositoryUser;

    public List<Users> getAllUsers() {
        return repositoryUser.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return repositoryUser.findById(id);
    }

    public Users createUser(Users user) {
        return repositoryUser.save(user);
    }

    public Optional<Users> updateUser(Users user) {
        if (user.getId() != null && repositoryUser.existsById(user.getId())) {
            return Optional.of(repositoryUser.save(user));
        }
        return Optional.empty();
    }

    public boolean deleteUser(Long id) {
        if (repositoryUser.existsById(id)) {
            repositoryUser.deleteById(id);
            return true;
        }
        return false;
    }
}
