package tn.enis.member.bean;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PublicationBean {
	private Long id;
	private String titre;
	private String type;
	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate date;
	private String lien;
	private String sourcePdf;
}
