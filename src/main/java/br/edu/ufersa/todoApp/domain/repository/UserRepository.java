package br.edu.ufersa.todoApp.domain.repository;

import br.edu.ufersa.todoApp.domain.entity.User;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Long>{

	User findByUuid(UUID id);

	User findByEmail(String email);

}
