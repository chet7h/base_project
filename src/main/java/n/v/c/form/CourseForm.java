package n.v.c.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseForm {

	private String usernameCourse;
	private String passwordCourse;
	private String discountCode;
	private long courseId;
	private String otp;
	private String price;

}
