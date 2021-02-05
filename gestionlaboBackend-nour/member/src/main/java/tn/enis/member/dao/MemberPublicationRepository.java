package tn.enis.member.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.enis.member.entities.MemberPublication;
import tn.enis.member.entities.PublicationMemberId;

public interface MemberPublicationRepository extends JpaRepository<MemberPublication, PublicationMemberId> {
	@Query("select m from MemberPublication  m where member_id=:x")
	List<MemberPublication> findMemberPublicationId(@Param("x") Long memberId);
}
