package tn.enis.member.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Data;

@Entity
@Data
public class MemberPublication {
	@EmbeddedId
	private PublicationMemberId id;
	@ManyToOne
	@MapsId("memberId")
	private Member member;

}
