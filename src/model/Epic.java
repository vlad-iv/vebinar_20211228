package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
	List<SubTask> subTasks = new ArrayList<>(); // Объектная модель
//	List<Integer> subTaskIds = new ArrayList<>(); // Плоская модель

	public Epic(String name, Status status, String description) {
		super(name, status, description);
	}

	public Epic(String name, String description) {
		super(name, Status.NEW, description);
	}

	public List<SubTask> getSubTasks() {
		return subTasks;
	}

	public void addTask(SubTask subTask) {

	}

	public void removeTask(SubTask subTask) {
		subTasks.remove(subTask);
	}

	public void removeAllTask() {
		subTasks.clear();
	}

	public void calculateEpicStatus() {

	}
}
