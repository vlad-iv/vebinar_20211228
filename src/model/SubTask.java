package model;

import java.time.Instant;

public class SubTask extends Task {
	Integer epicId;

	public SubTask(int id, String name, Status status, String description, Instant startTime, int duration, Integer epicId) {
		super(id, name, status, description, startTime, duration);
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
