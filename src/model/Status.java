package model;

public enum Status {
	NEW("Новый", 3),
	IN_PROGRESS("В процессе", 6),
	DONE("Завершен", 9);

	String nameStatus;
	int value;

	Status(String nameStatus, int value) {
		this.nameStatus = nameStatus;
		this.value = value;
	}

	String getName() {
		return nameStatus;
	}
}
