package model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements Serializable {

	private static final long serialVersionUID = -5822209320482420372L;

	private String email;
	private String firstName;
	private String lastName;
	private String password;

	public User() {
	}
	
	public User(String email, String firstName, String lastName, String password) {
		setEmail(email);
		setFirstName(firstName);
		setLastName(lastName);
		setPassword(password);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	// email check
	public static boolean isEmail(String email) {
		Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = emailPattern.matcher(email);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	
	// name check
	public static boolean isName(String name) {
		Pattern emailPattern = Pattern.compile("[A-Z][a-z]+( [A-Z][a-z]+)?");
		Matcher matcher = emailPattern.matcher(name);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	
	// pwd check
	public static boolean isValidPwd(String pwd) {
		Pattern emailPattern = Pattern.compile("^(?=.*?[a-z])(?=.*?[0-9]).{8,}$");
		Matcher matcher = emailPattern.matcher(pwd);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

}
