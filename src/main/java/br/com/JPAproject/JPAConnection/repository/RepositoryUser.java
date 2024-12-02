package br.com.JPAproject.JPAConnection.repository;

import br.com.JPAproject.JPAConnection.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<Users, Long> {



}
