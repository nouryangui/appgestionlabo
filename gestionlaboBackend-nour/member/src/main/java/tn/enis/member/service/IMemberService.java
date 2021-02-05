package tn.enis.member.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tn.enis.member.bean.EventBean;
import tn.enis.member.bean.PublicationBean;
import tn.enis.member.bean.ToolBean;
import tn.enis.member.entities.Member;
import tn.enis.member.entities.Student;
import tn.enis.member.entities.Teacher;

public interface IMemberService {
	List<Member> findAll();

	Member getById(Long id);

	Member add(Member member);

	void delete(Long id);

	Member update(Member member);

	List<Teacher> findByGrade(String grade);

	List<Teacher> findByEtablissement(String etablissement);

	List<Teacher> findAllTeachers();

	List<Student> findAllStudents();

	Student affectTeacher(Long idTeacher, Long idStudent);

	void affectMemberToPublication(Long idMember, Long idPublication);

	List<PublicationBean> findPublicationByMember(Long idMember);

	void affectMemberToTool(Long idMember, Long idTool);

	List<ToolBean> findToolByMember(Long idMember);

	void affectMemberToEvent(Long idMember, Long idEvent);

	List<EventBean> findEventByMember(Long idMember);

	Page<Member> getAll(Pageable pageable);

	List<Member> findMemberByEvent(Long idEvent);
}
