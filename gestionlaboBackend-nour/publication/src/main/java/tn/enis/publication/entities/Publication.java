package tn.enis.publication.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Publication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long Id;
	String type;
	String titre;
	String lien;
    @JsonFormat(pattern="yyyy-MM-dd")
	LocalDate date;
	String Sourcepdf;

}
