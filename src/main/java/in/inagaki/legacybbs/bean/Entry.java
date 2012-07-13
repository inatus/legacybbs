package in.inagaki.legacybbs.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class Entry implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	@NotEmpty(message = "\u6295\u7a3f\u8005\u540d\u306f\u5fc5\u305a\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044")
	private String name;
	private String email;
	private Timestamp date;
	@NotEmpty(message = "\u5185\u5bb9\u306f\u5fc5\u305a\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044")
	private String message;
	private String ip;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
