package studentApp;

public class Student {
	private String name;
	private String korScore;
	private String mathScore;
	private String engScore;
	
	public Student() {} // 없어도 되나?
	
	public Student(String name, String korScore, String mathScore, String engScore) {
		super();
		this.name = name;
		this.korScore = korScore;
		this.mathScore = mathScore;
		this.engScore = engScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKorScore() {
		return korScore;
	}

	public void setKorScore(String korScore) {
		this.korScore = korScore;
	}

	public String getMathScore() {
		return mathScore;
	}

	public void setMathScore(String mathScore) {
		this.mathScore = mathScore;
	}

	public String getEngScore() {
		return engScore;
	}

	public void setEngScore(String engScore) {
		this.engScore = engScore;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", korScore=" + korScore + ", mathScore=" + mathScore + ", engScore="
				+ engScore + "]";
	}
	
	
	
}
