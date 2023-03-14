package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Status;
import model.Task;

abstract class TaskManagerTest<T extends TaskManager> {
	T manager;
	private Task task;

	abstract T createManager();

	@BeforeEach
	void init() {
		manager = createManager();

		task = manager.create(new Task("Новая задача", Status.NEW, "описание"));
	}

	@Test
	void shouldGetTasks() {
		// подготовка данных

		// тестируем
		List<Task> all = manager.getAll();

		// проверяем
		assertEquals(all.size(), 1);
		assertEquals(all.get(0), task);
	}


	@Test
	void taskManagers() {
		EmptyHistoryManager historyManager = new EmptyHistoryManager();
		InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager(historyManager);

//		assertEquals(inMemoryTaskManager, memoryTaskManager, "Менеджеры должны совпадать");
		assertEqualsTaskManager(inMemoryTaskManager, manager, "Менеджеры должны совпадать");
		assertEqualsInMemoryTaskManager(inMemoryTaskManager, (InMemoryTaskManager) manager, "Менеджеры должны совпадать");
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