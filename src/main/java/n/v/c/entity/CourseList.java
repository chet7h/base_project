package n.v.c.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_list")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseList {
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "course_name", nullable = false)
	private String courseName;

	@Column(name = "course_code", nullable = false)
	private String courseCode;

	@Column(name = "job_title", nullable = false)
	private String jobTitle;

	@Column(name = "duration", nullable = false)
	private int duration;

	@Column(name = "link", nullable = false)
	private String link;

	@Column(name = "price", nullable = false)
	private String price;

}
