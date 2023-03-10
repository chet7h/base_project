package n.v.c.entity;

import java.time.LocalDate;

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
@Table(name = "course")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name_course", nullable = false)
	private String nameCourse;
	
	@Column(name = "username_register", nullable = false)
	private String usernameRegister;

	@Column(name = "username_course", nullable = false)
	private String usernameCourse;

	@Column(name = "password_course", nullable = false)
	private String passwordCourse;

	@Column(name = "discount_code", nullable = false)
	private String discountCode;

	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "price", nullable = false)
	private String price;
	
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
	
	@Column(name = "otp", nullable = false)
	private String otp;

}
