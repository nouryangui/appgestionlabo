package tn.enis.publication.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.enis.publication.entities.Publication;
import tn.enis.publication.repository.PublicationRepository;
import tn.enis.publication.service.IpublicationService;

@RestController
@RequestMapping("/api/publications")

public class PublicationController {
	@Autowired
	IpublicationService publicationService;
	@Autowired
	PublicationRepository publicationRepository;

	@GetMapping()
	public List<Publication> findPublications() {
		return publicationService.findAll();
	}

	@GetMapping(value = "{id}")
	public Publication findPublicationById(@PathVariable Long id) {
		return publicationService.getById(id);
	}

	@GetMapping(value = "/type/{type}")
	public List<Publication> findPublicationByType(@PathVariable String type) {
		return publicationService.getByType(type);
	}
	@GetMapping(value = "/date/{date}")
	public List<Publication> findPublicationByDate(@PathVariable LocalDate date) {
		return publicationService.getByDate(date);
	}
	@GetMapping(value = "/count")
	public Long findCountPublications() {
		return publicationRepository.count();
	}
	@PostMapping()
	public Publication addPublication(@RequestBody Publication publication) {
		return publicationService.add(publication);
	}

	@DeleteMapping(value = "/{id}")
	public void deletePublication(@PathVariable Long id) {
		publicationService.delete(id);
	}

	@PutMapping()
	public Publication updatePublication(@RequestBody Publication publication) {
		return publicationService.update(publication);
	}
	@GetMapping(value = "/all")
	public Page<Publication> findAllPublicationsPaginator(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		return publicationService.getAll(PageRequest.of(page, size));
	}
}
