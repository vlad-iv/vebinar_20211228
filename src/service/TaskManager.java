package service;

import java.util.List;

import model.Epic;
import model.SubTask;
import model.Task;

public interface TaskManager {
	Task create(Task task);

	Task get(int id);

	void update(Task task);

	void updateEpic(Epic epic);

	void updateSubTask(SubTask subTask);

	List<Task> getAll();

	void delete(int id);

	void deleteSubTask(int id);

	Epic createEpic(Epic epic);

	SubTask createSubTask(SubTask subTask);

	List<Task> getHistory();
}
