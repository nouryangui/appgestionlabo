package tn.enis.member.bean;

import java.time.LocalDate;
import lombok.Data;

@Data
public class EventBean {

	Long id;
	String title;
	LocalDate date;
	String location;

}
