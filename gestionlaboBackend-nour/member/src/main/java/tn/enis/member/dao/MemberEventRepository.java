package tn.enis.member.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.enis.member.entities.EventMemberId;
import tn.enis.member.entities.MemberEvent;

public interface MemberEventRepository extends JpaRepository<MemberEvent, EventMemberId> {
	@Query("select m from MemberEvent  m where member_id=:x")
	List<MemberEvent> findMemberEventId(@Param("x")Long idMember);
	
	@Query("select m from MemberEvent  m where event_id=:x")
	List<MemberEvent> findEventMemberId(@Param("x")Long idEvent);

}
