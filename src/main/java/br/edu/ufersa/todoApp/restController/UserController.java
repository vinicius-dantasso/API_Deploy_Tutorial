package br.edu.ufersa.todoApp.restController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.todoApp.api.dto.CreateUserDTO;
import br.edu.ufersa.todoApp.api.dto.UpdateUserDTO;
import br.edu.ufersa.todoApp.api.dto.UserDTO;
import br.edu.ufersa.todoApp.domain.entity.User;
import br.edu.ufersa.todoApp.domain.service.UserService;

@RestController
@RequestMapping("/api/user")

public class UserController {
	
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserService service;
	
	@GetMapping
	public List<UserDTO> listar(){
		List<UserDTO> users = new ArrayList<UserDTO>();
		for(User user: service.getAll()) {
			users.add(mapper.map(user, UserDTO.class));
		}
		return users;
	}
	
	@GetMapping ("/{userId}")
	public ResponseEntity<UserDTO> buscar(@PathVariable UUID userId){
		UserDTO dto = mapper.map(service.getById(userId), UserDTO.class);
		if(dto != null) {
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> criar(@Valid @RequestBody CreateUserDTO dto){
		User user = service.createUser(mapper.map(dto, User.class));
		UserDTO criado = mapper.map(user, UserDTO.class);
		
		if(criado == null) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
			return new ResponseEntity<>(criado,HttpStatus.CREATED);
		}
	}

	@PutMapping
	public ResponseEntity<UserDTO> alterar(@Valid @RequestBody UpdateUserDTO dto){
		User user = service.updateUser(mapper.map(dto,User.class));
		UserDTO atualizado = mapper.map(user, UserDTO.class);
		
		if(atualizado!=null) {
			return new ResponseEntity<>(atualizado,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping
	public ResponseEntity<UserDTO> atualizarTudo(@Valid @RequestBody CreateUserDTO dto){
		User user = service.updateUserPatch(mapper.map(dto, User.class));
		UserDTO atualizado = mapper.map(user, UserDTO.class);
		
		if(atualizado!=null) {
			return new ResponseEntity<>(atualizado,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<UUID> deletar(@PathVariable UUID userId){
		String teste = service.deleteUser(userId);
		
		if(teste.equals("ok")) {
			return new ResponseEntity<>(userId,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
}
