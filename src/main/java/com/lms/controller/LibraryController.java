package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.LibraryDTO;
import com.lms.service.LibraryService;


@RestController
@RequestMapping("library")
public class LibraryController {
	
	@Autowired
	private LibraryService libraryService;
	
	@PostMapping("/addlibrary")
	public ResponseEntity<String> addLibrary(@RequestBody LibraryDTO libraryDTO){
		return libraryService.addLibrary(libraryDTO);
	}
	@GetMapping("/getlibrarybyid/{id}")
	public ResponseEntity<LibraryDTO> getLibrary(@PathVariable("id") Long id){
		return libraryService.getLibrary(id);
	}
	@PutMapping("/updatelibrarybyid/{id}")
	public ResponseEntity<String> updateLibrary(@PathVariable("id") Long id,@RequestBody LibraryDTO libraryDTO){
		return libraryService.updatelibrary(id,libraryDTO);
	}
	@DeleteMapping("/deletelibrarybyid/{id}")
	public ResponseEntity<String> deleteLibrary(@PathVariable("id") Long id){
		return libraryService.deletelLibrary(id);
	}
	

}
