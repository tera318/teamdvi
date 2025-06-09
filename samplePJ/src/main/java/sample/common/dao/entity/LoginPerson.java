package sample.common.dao.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginPerson {

	@NotNull
	@Max(10)
	private String id;

	@NotNull
	@Size(min = 3, max = 10)
	private String pass;

	public String getID() {
		return this.id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String toString() {
		return "Login(Id" + this.id + ", Pass" + this.pass + ")";
	}

}
