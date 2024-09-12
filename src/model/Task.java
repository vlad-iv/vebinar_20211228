package model;

public class Task {
	private int id;
	private String name;
	private Status status;
	private String description;

	public Task(int id, String name, Status status, String description) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.description = description;
	}

	public Task(String name, Status status, String description) {
		this.name = name;
		this.status = status;
		this.description = description;
	}

	public Epic getEpic() {
		return null;
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

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Task task)) return false;

		return id == task.id;
	}

	@Override
	public int hashCode() {
		return id;
	}

	public String toString() {
		return "Task{" +
				"id=" + id +
				", name='" + name + '\'' +
				", status='" + status + '\'' +
				", description='" + description + '\'' +
				'}';
	}

}
