package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
	private List<SubTask> subTasks = new ArrayList<>(); // Set
//	List<Integer> subTaskIds = new ArrayList<>(); // Set

	public Epic(String name, String description) {
		super(name, Status.NEW, description);
	}

	public Epic(String name, Status status, String description) {
		super(name, status, description);
	}

	public List<SubTask> getSubTasks() {
		return subTasks;
	}

	public void addTask(SubTask subTask) {

	}

	public void removeTask(SubTask subTask) {

	}

	public void updateStatus() {
		status = Status.NEW; // TODO Calculate status
	}
}
