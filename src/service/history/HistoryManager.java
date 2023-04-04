package service.history;

import java.util.List;

import model.Task;

public interface HistoryManager {
	void add(Task task);

	List<Task> getAll();

	void remove(int id);
}
