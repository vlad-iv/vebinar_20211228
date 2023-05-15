package model;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Task {
	private int id;
	private String name;
	private Status status;
	private String description;

	private Instant startTime;
	private int duration; // минуты
	private Instant endTime;

	public Task() {
		this.startTime = Instant.now();
		this.duration = 0;
	}

	public Task(String name) {
		this.name = name;
		this.startTime = Instant.now();
		this.duration = 0;
	}
	public Task(int id, String name, Status status, String description, Instant startTime, int duration) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.description = description;
		this.startTime = startTime;
		this.duration = duration;
		this.endTime = startTime.plus(duration, ChronoUnit.MINUTES);
	}


	public Task(Task task) {
		this.id = task.id;
		this.name = task.name;
		this.status = task.status;
		this.description = task.description;
		this.startTime = task.startTime;
		this.duration = task.duration;
		this.endTime = task.endTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskType getType() {
		return TaskType.TASK;
	}

	public Instant getStartTime() {
		return startTime;
	}

	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Instant getEndTime() {
		return endTime;
	}

	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Task{" +
				"id=" + id +
				", name='" + name + '\'' +
				", status=" + status +
				", description='" + description + '\'' +
				", startTime=" + startTime +
				", duration=" + duration +
				", endTime=" + endTime +
				'}';
	}

}
