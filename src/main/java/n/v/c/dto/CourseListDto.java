package n.v.c.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseListDto {
	private Long id;
	private String courseCode;
	private String courseName;
	private String jobTitle;
	private int duration;
	private String price;
	private String action;
	private String link;
}
