package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// imports for use List, Map, String and Object
import java.lang.String;


@Controller	
@RequestMapping(path="/api/curso")
public class MainController {
	@Autowired 
	private UserRepository userRepository;

		@GetMapping(path="/listar")
	public @ResponseBody Iterable<User> listar() {
		return userRepository.findAll();
	}

	@PostMapping(path="/nuevo") 
	public @ResponseBody String nuevo (@RequestParam String name
			, @RequestParam Integer credito) {
		User n = new User();
		n.setName(name);
		n.setCredito(credito);
		userRepository.save(n);
		return "Saved";
	}

	@DeleteMapping(path="/eliminar")
	public @ResponseBody String eliminar (@RequestParam Integer id) {
		User n = new User();
		n.setId(id);
		userRepository.delete(n);
		return "Deleted";
	}




}
