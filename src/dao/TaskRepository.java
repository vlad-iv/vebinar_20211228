package dao;

import model.TaskData;

public interface TaskRepository {
	TaskData load();

	void save(TaskData taskData);
}
