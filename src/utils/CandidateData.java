package utils;

public class CandidateData {
	private String name;
	private Long phone;
	private String id;
	
	public CandidateData() {
		this.setName(null);
		this.setPhone(0L);
		this.setId(null);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
