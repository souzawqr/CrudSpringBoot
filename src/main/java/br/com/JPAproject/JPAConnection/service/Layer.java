package br.com.JPAproject.JPAConnection.service;

import br.com.JPAproject.JPAConnection.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



public class Layer {

    @Service
    public class usersService {

        @Autowired
        private RepositoryUser repositoryuser;
    }

}
