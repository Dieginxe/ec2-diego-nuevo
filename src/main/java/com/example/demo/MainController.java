package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
// imports for use List, Map, String and Object
import java.util.List;
import java.util.Map;
import java.lang.String;
import java.lang.Object;

import org.springframework.jdbc.core.JdbcTemplate;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/api") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;

	//Tabla: cursos:                             #
	//- id: int (strategy=GenerationType.AUTO) #
	//- nombre: string                         #
	//- creditos: int 
	@Autowired
  	private JdbcTemplate jdbcTemplate;

	@PostMapping(path="/nuevo") // Map ONLY POST Requests
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

	@PutMapping(path="/edit")
	public @ResponseBody String editUser (@RequestParam Integer id, @RequestParam String name
	, @RequestParam Integer credito) {
		User n = new User();
		n.setId(id);
		n.setName(name);
		n.setCredito(credito);
		userRepository.save(n);
		return "Updated";
	}


	@GetMapping(path="/listar")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path="/get/{id}")
	public @ResponseBody User getOneUser(@PathVariable Integer id) {
		return userRepository.findById(id).orElse(null);
	}


	@GetMapping(path="/get/report")
	public @ResponseBody List<Map<String, Object>> getReport() {
		List<Map<String, Object>> queryResult = jdbcTemplate.queryForList("SELECT CONCAT(name, ' ==> ', apellido) as mycol FROM user");
		return queryResult;
	}


}
