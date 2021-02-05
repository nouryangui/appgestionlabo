package com.eureka.auth.beans;

import java.io.File;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;



@Data
@DiscriminatorColumn(name = "type_mbr", discriminatorType = DiscriminatorType.STRING)

public  class MemberBean {

	
	Long id;
	String cin;
	
	String firstName;
	String lastName;
	String username;
    @JsonFormat(pattern="yyyy-MM-dd")
	LocalDate birthDate;
	
	String email;
	String password;
	
	byte[] photo;
	
	String cv;

	private Set<RoleBean> roles = new HashSet<>();
}
