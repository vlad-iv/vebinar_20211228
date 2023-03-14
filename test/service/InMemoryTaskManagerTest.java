package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Task;

class InMemoryTaskManagerTest {
	EmptyHistoryManager historyManager = new EmptyHistoryManager();
	InMemoryTaskManager memoryTaskManager = new InMemoryTaskManager(historyManager);

	@Test
	void taskManagers() {
		InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager(historyManager);

//		assertEquals(inMemoryTaskManager, memoryTaskManager, "Менеджеры должны совпадать");
		assertEqualsTaskManager(inMemoryTaskManager, memoryTaskManager, "Менеджеры должны совпадать");
		assertEqualsInMemoryTaskManager(inMemoryTaskManager, memoryTaskManager, "Менеджеры должны совпадать");
	}


	private static void assertEqualsInMemoryTaskManager(InMemoryTaskManager expected, InMemoryTaskManager actual, String message) {

		assertEquals(expected.tasks, actual.tasks, message + ", tasks");
	}

	private static void assertEqualsTaskManager(TaskManager expected, TaskManager actual, String message) {
		assertEquals(expected.getAll(), actual.getAll(), message + ", tasks");
	}

	private static class EmptyHistoryManager implements HistoryManager {
		@Override
		public void add(Task task) {
		}

		@Override
		public List<Task> getAll() {
			return Collections.emptyList();
		}
	}
}