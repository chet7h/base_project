package n.v.c.form;

import lombok.Data;

@Data
public class RegisterForm {

	private String courseName;
	private long courseCode;

	private String cssItem1;
	private String cssItem2;
	private String cssItem3;
	private String cssItem4;

	private String btnClick;
	private String activePage;

	//
	// step 1
	private String username;
	private String password;
	private String messageItem1;
	// step 2
	private String discountCode;
	private String messageItem2;
	// step 3
	private String price;
	private String otp;

}
