package model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
	List<SubTask> subTasks = new ArrayList<>();

	public List<SubTask> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(List<SubTask> subTasks) {
		this.subTasks = subTasks;
	}
}
