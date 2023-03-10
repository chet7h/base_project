package n.v.c.enums;

public enum StatusCourse {
	NOT_YET_START("1", "NOT YET START"), VERIFYING("2", "VERIFYING"), START_LEARNING("3", "START LEARNING"), COMPLETED("4", "COMPLETED");

	private String code;
	private String display;

	private StatusCourse(String code, String display) {
		this.code = code;
		this.display = display;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public static String getDisplayFromCode(String text) {
		for (StatusCourse b : StatusCourse.values()) {
			if (b.getCode().equals(text)) {
				return b.getDisplay();
			}
		}
		return null;
	}
}
