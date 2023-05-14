package model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

	List<Integer> subTasks;

	public Epic() {
		this.subTasks = new ArrayList<>();
	}

	public Epic(int id, String name, String description, Instant startTime) {
		super(id, name, Status.NEW, description, startTime, 0);
		this.subTasks = new ArrayList<>();
	}

	public Epic(int id, String name, Status status, String description, Instant startTime, int duration, List<Integer> subTasks) {
		super(id, name, status, description, startTime, duration);
		this.subTasks = subTasks;
	}

	public List<Integer> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(List<Integer> subTasks) {
		this.subTasks = subTasks;
	}
}
