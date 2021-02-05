package tn.enis.member.entities;

import java.time.LocalDate;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@DiscriminatorValue("student")

public class Student extends Member {
    @JsonFormat(pattern="yyyy-MM-dd")
	LocalDate dateInscription;
	String diplome;
	@ManyToOne
	Teacher teacher;

}
