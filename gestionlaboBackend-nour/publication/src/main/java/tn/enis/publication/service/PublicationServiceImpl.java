package tn.enis.publication.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tn.enis.publication.entities.Publication;
import tn.enis.publication.repository.PublicationRepository;

@Service
public class PublicationServiceImpl implements IpublicationService {
	@Autowired
	PublicationRepository publicationRepository;

	@Override
	public List<Publication> findAll() {
		return publicationRepository.findAll();
	}

	@Override
	public Publication getById(Long id) {
		return publicationRepository.findById(id).get();
	}

	@Override
	public List<Publication> getByType(String type) {

		List<Publication> publications = publicationRepository.findByType(type);
		return publications;
	}

	@Override
	public List<Publication> getByDate(LocalDate date) {
		List<Publication> publications = publicationRepository.findByDate(date);
		return publications;
	}

	@Override
	public Publication add(Publication publication) {
		return publicationRepository.save(publication);
	}

	@Override
	public void delete(Long id) {
		publicationRepository.deleteById(id);

	}

	@Override
	public Publication update(Publication publication) {
		return publicationRepository.save(publication);

	}
	public Page<Publication> getAll(Pageable pageable) {

		Page<Publication> publicationPage= publicationRepository.findAll(pageable);
		return new PageImpl<>(publicationPage.getContent(), pageable, publicationPage.getTotalElements());

	}

}
