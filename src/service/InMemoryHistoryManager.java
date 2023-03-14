package service;

import java.util.LinkedList;
import java.util.List;

import model.Task;

public class InMemoryHistoryManager implements HistoryManager {
	LinkedList<Task> history = new LinkedList<>();
	@Override
	public void add(Task task) {
		if (history.size() > 9) {
			history.removeFirst();
		}
		history.add(task);
	}

	@Override
	public List<Task> getAll() {
		return history;
	}
}
