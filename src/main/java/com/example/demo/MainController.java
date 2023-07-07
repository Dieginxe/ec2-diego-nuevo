package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.String;




@Controller	
@RequestMapping(path="/api/curso")
public class MainController {
	@Autowired 
	private UserRepository userRepository;

	

	@PostMapping(path="/nuevo") 
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam Integer credito) {
		User n = new User();
		n.setName(name);
		n.setCredito(credito);
		userRepository.save(n);
		return "Saved";
	}

	@DeleteMapping(path="/eliminar")
	public @ResponseBody String delUser (@RequestParam Integer id) {
		User n = new User();
		n.setId(id);
		userRepository.delete(n);
		return "Deleted";
	}

	@GetMapping(path="/listar")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}


}
