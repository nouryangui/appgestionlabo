package tn.enis.member.entities;

import java.io.File;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import tn.enis.member.bean.EventBean;
import tn.enis.member.bean.PublicationBean;
import tn.enis.member.bean.ToolBean;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@DiscriminatorColumn(name = "type_mbr", discriminatorType = DiscriminatorType.STRING)

public abstract class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@NonNull
	String cin;
	@NonNull
	String firstName;
	@NonNull
	String lastName;
	@NonNull
	String username;
    @JsonFormat(pattern="yyyy-MM-dd")
	LocalDate birthDate;
	@NonNull
	String email;
	@NonNull
	
	String password;
	@Column(name = "picByte",length=1000000000)
	
	byte[] photo;
	
	String cv;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@Transient
	List<PublicationBean> publications;
	@Transient
	List<ToolBean> tools;
	@Transient
	List<EventBean> events;
	
	@NonNull
	String type;
}
