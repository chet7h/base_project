package n.v.c.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

	private String customerName;
	private String nameCourse;
	private LocalDate startDate;
	private String status;
	private String price;
	private String otp;
	private String usernameCourse;
	private String passwordCourse;
	private String paymentCode;

}
