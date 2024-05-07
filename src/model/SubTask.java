package model;

public class SubTask extends Task {
	private Epic epic;

	public SubTask(String name, Status status, String description, Epic epic) {
		super(name, status, description);
		this.epic = epic;
	}

	@Override
	public Epic getEpic() {
		return epic;
	}

	public void setEpic(Epic epic) {
		this.epic = epic;
	}
}
