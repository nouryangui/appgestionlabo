package tn.enis.event.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@NonNull
	String title;
    @JsonFormat(pattern="yyyy-MM-dd")
	LocalDate date;
	@NonNull
	String location;

}
