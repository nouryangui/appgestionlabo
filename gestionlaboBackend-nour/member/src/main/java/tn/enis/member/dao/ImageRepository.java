package tn.enis.member.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enis.member.entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findById(Long Id);

}
