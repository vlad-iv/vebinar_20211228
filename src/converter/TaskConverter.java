package converter;

import model.Task;
import model.TaskType;

public class TaskConverter {
	public TaskType getType() {
		return TaskType.TASK;
	}

	public String toString(Task task) {
		return task.getId() + "," + task.getName() + "," + task.getDescription();
	}
}
