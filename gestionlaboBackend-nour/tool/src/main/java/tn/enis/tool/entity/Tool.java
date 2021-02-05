package tn.enis.tool.entity;

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
public class Tool {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
    @JsonFormat(pattern="yyyy-MM-dd")
	@NonNull
	LocalDate date;
	@NonNull
	String source;

}
