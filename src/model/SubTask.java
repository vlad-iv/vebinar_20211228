package model;

public class SubTask extends Task {
	Integer epicId;

	public SubTask(String name, Status status, String description) {
		super(name, status, description);
	}

	public Integer getEpicId() {
		return epicId;
	}

	public void setEpicId(Integer epicId) {
		this.epicId = epicId;
	}
}
