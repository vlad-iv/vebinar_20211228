package model;

public class SubTask extends Task {
	private Integer epicId;

	public SubTask(String name, Status status, String description, Integer epicId) {
		super(name, status, description);
		this.epicId = epicId;
	}

	@Override
	public Integer getEpicId() {
		return epicId;
	}

	public void setEpicId(Integer epicId) {
		this.epicId = epicId;
	}
}
