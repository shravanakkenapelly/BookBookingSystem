package com.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.LibraryDTO;
import com.lms.entity.Library;
import com.lms.exception.LibraryNotFound;
import com.lms.repository.LibraryRepository;

@Service
public class LibraryService {

	@Autowired
	private LibraryRepository libraryRepository;

	public ResponseEntity<String> addLibrary(LibraryDTO libraryDTO) {
		
		Library library=new Library();
		library.setLibraryname(libraryDTO.getLibraryname());
		library.setLibrarylocation(libraryDTO.getLibrarylocation());
		libraryRepository.save(library);
		return new ResponseEntity<String>("Library Added Successfully",HttpStatus.CREATED);
	}

	public ResponseEntity<LibraryDTO> getLibrary(Long id) {
		Library library=libraryRepository.findById(id).orElseThrow(
				()->new LibraryNotFound(String.format("Library %d is not Found",id))
				);
		LibraryDTO libraryDTO=new LibraryDTO();
		libraryDTO.setLibraryname(library.getLibraryname());
		libraryDTO.setLibrarylocation(library.getLibrarylocation());
		return new ResponseEntity<LibraryDTO>(libraryDTO,HttpStatus.OK);
	}

	public ResponseEntity<String> updatelibrary(Long id, LibraryDTO libraryDTO) {
		Library libraryid=libraryRepository.findById(id).orElseThrow(
				()->new LibraryNotFound(String.format("Library id %d is Not Found",id))
				);
		Library library=new Library();
		library.setLibraryname(libraryDTO.getLibraryname());
		library.setLibrarylocation(libraryDTO.getLibrarylocation());
		library.setId(id);
		libraryRepository.save(library);
		
		return new ResponseEntity<String>("Updated Successfully",HttpStatus.OK);
	}

	public ResponseEntity<String> deletelLibrary(Long id) {
		Library library=libraryRepository.findById(id).orElseThrow(
				()->new LibraryNotFound(String.format("Library id %d is not Found", id))
				);
		libraryRepository.delete(library);
		return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
	}

}
