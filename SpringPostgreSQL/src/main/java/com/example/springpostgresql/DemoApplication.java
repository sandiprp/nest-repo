package com.example.springpostgresql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Autowired
	ApplicationMetaRepository applicationMetaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		ApplicationMeta applicationMeta = new ApplicationMeta();
		applicationMeta.setName("MyNewApp");
		applicationMeta.setDescription("This is a compeletely new application");
		applicationMetaRepository.save(applicationMeta);
		
		
		applicationMeta = new ApplicationMeta();
		applicationMeta.setName("CoolApp");
		applicationMeta.setDescription("This is a cool new application");
		applicationMetaRepository.save(applicationMeta);
		
		applicationMeta = new ApplicationMeta();
		applicationMeta.setName("KidsApp");
		applicationMeta.setDescription("This is a kids new application");
		applicationMetaRepository.save(applicationMeta);
		
		
	}

	@RestController
	public static class ApplicationMetaController{
		
		@Autowired
		private ApplicationMetaRepository applicationMetaRepository;
		
		@GetMapping(value = "/apps/{name}")
		public ResponseEntity<ApplicationMeta> applicationData(@PathVariable String name){
			
			ApplicationMeta applicationMeta = applicationMetaRepository.findByName(name);
			
			return ResponseEntity.ok().body(applicationMeta);
		}
		
		@GetMapping(value = "/apps")
		public ResponseEntity<List<ApplicationMeta>> apps(){
			List<ApplicationMeta> applicationMetaList = applicationMetaRepository.findAll();
			
			return ResponseEntity.ok(applicationMetaList);
		}
		
	}
}

