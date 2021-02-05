package tn.enis.member.bean;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ToolBean {
	Long id;
	LocalDate date;
	String source;
}
