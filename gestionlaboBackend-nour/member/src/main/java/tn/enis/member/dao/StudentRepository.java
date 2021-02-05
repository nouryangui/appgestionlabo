package tn.enis.member.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.enis.member.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
