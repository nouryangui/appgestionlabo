package tn.enis.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.enis.member.bean.EventBean;
import tn.enis.member.bean.PublicationBean;
import tn.enis.member.bean.ToolBean;
import tn.enis.member.dao.MemberEventRepository;
import tn.enis.member.dao.MemberPublicationRepository;
import tn.enis.member.dao.MemberRepository;
import tn.enis.member.dao.MemberToolRepository;
import tn.enis.member.dao.StudentRepository;
import tn.enis.member.dao.TeacherRepository;
import tn.enis.member.entities.EventMemberId;
import tn.enis.member.entities.Member;
import tn.enis.member.entities.MemberEvent;
import tn.enis.member.entities.MemberPublication;
import tn.enis.member.entities.MemberTool;
import tn.enis.member.entities.PublicationMemberId;
import tn.enis.member.entities.Student;
import tn.enis.member.entities.Teacher;
import tn.enis.member.entities.ToolMemberId;
import tn.enis.member.proxy.EventProxy;
import tn.enis.member.proxy.PublicationProxy;
import tn.enis.member.proxy.ToolProxy;

@Service
@Slf4j
public class MemberServiceImpl implements IMemberService {
	private final MemberRepository memberRepository;
	private final TeacherRepository teacherRepository;
	private final MemberPublicationRepository memberPublicationRepository;
	private final PublicationProxy publicationProxy;
	private final StudentRepository studentRepository;
	private final MemberToolRepository memberToolRepository;
	private final MemberEventRepository memberEventRepository;
	private final ToolProxy toolProxy;
	private final EventProxy eventProxy;

	public MemberServiceImpl(MemberRepository memberRepository, TeacherRepository teacherRepository,
			StudentRepository studentRepository, MemberPublicationRepository memberPublicationRepository,
			PublicationProxy publicationProxy, MemberToolRepository memberToolRepository, ToolProxy toolProxy,
			MemberEventRepository memberEventRepository, EventProxy eventProxy) {
		super();
		this.memberRepository = memberRepository;
		this.teacherRepository = teacherRepository;
		this.memberPublicationRepository = memberPublicationRepository;
		this.publicationProxy = publicationProxy;
		this.studentRepository = studentRepository;
		this.memberToolRepository = memberToolRepository;
		this.toolProxy = toolProxy;
		this.memberEventRepository = memberEventRepository;
		this.eventProxy = eventProxy;
	}

	@Override
	public List<Member> findAll() {

		List<Member> members = new ArrayList<>(memberRepository.findAll());

		return members;

	}

	@Override
	public Member getById(Long id) {

		Member member = memberRepository.findById(id).get();

		return member;

	}

	@Override
	public Member add(Member member) {

		return memberRepository.save(member);

	}

	@Override
	public void delete(Long id) {

		memberRepository.deleteById(id);
		log.info("deleteOneMember:Member with id = {} deleted successfully", id);

	}

	@Override
	public Member update(Member member) {

		return memberRepository.save(member);
	}

	public List<Teacher> findAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public List<Student> findAllStudents() {

		return studentRepository.findAll();
	}

	public Student affectTeacher(Long idTeacher, Long idStudent) {

		Student student = (Student) memberRepository.findById(idStudent).get();
		Teacher teacher = (Teacher) memberRepository.findById(idTeacher).get();
		student.setTeacher(teacher);
		return memberRepository.save(student);

	}

	@Override
	public List<Teacher> findByGrade(String grade) {

		List<Teacher> teacher = teacherRepository.findByGrade(grade);

		return teacher;

	}

	@Override
	public List<Teacher> findByEtablissement(String etablissement) {
		List<Teacher> teacher = teacherRepository.findByEtablissement(etablissement);

		return teacher;
	}

	@Override
	public void affectMemberToPublication(Long idMember, Long idPulication) {
		Member member = memberRepository.findById(idMember).get();
		MemberPublication memberPublication = new MemberPublication();
		memberPublication.setMember(member);
		memberPublication.setId(new PublicationMemberId(idMember, idPulication));
		memberPublicationRepository.save(memberPublication);

	}

	@Override
	public List<PublicationBean> findPublicationByMember(Long idMember) {
		List<PublicationBean> publications = new ArrayList<PublicationBean>();
		List<MemberPublication> membersPublication = memberPublicationRepository.findMemberPublicationId(idMember);
		membersPublication.forEach(s -> {
			publications.add(publicationProxy.recupererUnePublication(s.getId().getPulicationId()));

		});

		return publications;
	}

	@Override
	public void affectMemberToTool(Long idMember, Long idTool) {
		Member member = memberRepository.findById(idMember).get();
		MemberTool memberTool = new MemberTool();
		memberTool.setMember(member);
		memberTool.setToolMemberId(new ToolMemberId(idMember, idTool));
		memberToolRepository.save(memberTool);

	}

	@Override
	public List<ToolBean> findToolByMember(Long idMember) {
		List<ToolBean> tools = new ArrayList<ToolBean>();
		List<MemberTool> membersToolId = memberToolRepository.findMemberToolId(idMember);
		membersToolId.forEach(s -> {
			tools.add(toolProxy.findToolById(s.getToolMemberId().getToolId()));

		});

		return tools;
	}

	public void affectMemberToEvent(Long idMember, Long idEvent) {
		Member member = memberRepository.findById(idMember).get();
		MemberEvent memberEvent = new MemberEvent();
		memberEvent.setMember(member);
		memberEvent.setEventMemberId(new EventMemberId(idMember, idEvent));
		memberEventRepository.save(memberEvent);

	}

	@Override
	public List<EventBean> findEventByMember(Long idMember) {

		List<EventBean> events = new ArrayList<EventBean>();
		List<MemberEvent> membersEventId = memberEventRepository.findMemberEventId(idMember);
		membersEventId.forEach(s -> {
			events.add(eventProxy.findEventById(s.getEventMemberId().getEventId()));

		});

		return events;
	}
	public List<Member> findMemberByEvent(Long idEvent) {

		List<Member> members = new ArrayList<Member>();
		List<MemberEvent> membersEventId = memberEventRepository.findEventMemberId(idEvent);
		membersEventId.forEach(s -> {
			members.add(memberRepository.findById(s.getEventMemberId().getMemberId()).get());

		});

		return members;
	}
	/*public List<Member> findMemberByPublication(Long idPub) {

		List<Member> members = new ArrayList<Member>();
		List<MemberEvent> membersEventId = memberPublicationRepository.findEventMemberId(idPub);
		membersEventId.forEach(s -> {
			members.add(memberRepository.findById(s.getEventMemberId().getMemberId()).get());

		});

		return members;
	}*/

	public Page<Member> getAll(Pageable pageable) {

		Page<Member> memberPage = memberRepository.findAll(pageable);
		return new PageImpl<>(memberPage.getContent(), pageable, memberPage.getTotalElements());

	}
	
	
}
