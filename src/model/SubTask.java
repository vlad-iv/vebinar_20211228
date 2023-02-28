package model;

public class SubTask extends Task {
	Epic epic; // Объектная модель
//	int epicId; // плоская модель

	public SubTask(String name, Status status, String description) {
		super(name, status, description);
	}

	@Override
	public Epic getEpic() {
		return epic;
	}

	public void setEpic(Epic epic) {
		this.epic = epic;
	}
}
