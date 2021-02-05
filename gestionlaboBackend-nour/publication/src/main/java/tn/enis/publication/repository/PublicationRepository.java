package tn.enis.publication.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enis.publication.entities.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
	List<Publication> findByType(String type);

	List<Publication> findByDate(LocalDate date);

}
