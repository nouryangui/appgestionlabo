package tn.enis.member.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.enis.member.entities.MemberTool;
import tn.enis.member.entities.ToolMemberId;

public interface MemberToolRepository extends JpaRepository<MemberTool, ToolMemberId> {
	@Query("select m from MemberTool  m where member_id=:membreId")
	List<MemberTool> findMemberToolId(@Param("membreId") Long memberId);

}
