package model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
	List<SubTask> subTasks = new ArrayList<>();

	public Epic(int id, String name, Status status, String description, Instant startTime, int duration) {
		super(id, name, status, description, startTime, duration);
	}

	public List<SubTask> getSubTasks() {
		return subTasks;
	}

	public void addTask(SubTask subTask) {

	}

	public void removeTask(SubTask subTask) {
		subTasks.remove(subTask);
	}

	public void calculateEpicStatus() {

	}

}
