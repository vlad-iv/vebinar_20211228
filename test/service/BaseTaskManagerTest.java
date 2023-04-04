package service;

import java.util.Collections;
import java.util.List;

import model.Task;

public class BaseTaskManagerTest extends TaskManagerTest<InMemoryTaskManager> {
	@Override
	InMemoryTaskManager createManager() {
		EmptyHistoryManager historyManager = new EmptyHistoryManager();
		return new InMemoryTaskManager(historyManager);
	}

	private static class EmptyHistoryManager implements HistoryManager {
		@Override
		public void add(Task task) {
		}

		@Override
		public List<Task> getAll() {
			return Collections.emptyList();
		}

		@Override
		public void remove(int id) {
		}
	}
}
