package tn.enis.member.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.enis.member.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	List<Teacher> findByGrade(String grade);

	List<Teacher> findByEtablissement(String etablissement);


}
