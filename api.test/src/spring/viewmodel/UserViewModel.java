package spring.viewmodel;

public class UserViewModel {
	private long id;
	private String mobile_number;
	private String first_name;
	private String last_name;
	private String date_of_birth;
	private boolean gender;
	private String email;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "UserViewModel [id=" + id + ", mobile_number=" + mobile_number + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", date_of_birth=" + date_of_birth + ", gender=" + gender + ", email="
				+ email + "]";
	}
	
}
