package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Status;
import model.Task;
import service.history.HistoryManager;

class InMemoryTaskManagerTest {
	InMemoryTaskManager memoryTaskManager;
	private Task task;


	@BeforeEach
	void init() {
		EmptyHistoryManager historyManager = new EmptyHistoryManager();
		memoryTaskManager = new InMemoryTaskManager(historyManager);

		task = memoryTaskManager.create(new Task("Новая задача", Status.NEW, "описание"));
	}

	@Test
	void shouldGetTasks() {
		// подготовка данных

		// тестируем
		List<Task> all = memoryTaskManager.getAll();

		// проверяем
		assertEquals(all.size(), 1);
		assertEquals(all.get(0), task);
	}

	@Test
	void shouldCreateGetAndPutToMap() {
		// подготовка данных
		Task task1 = new Task("Новая задача", Status.NEW, "описание");

		// тестируем
		Task taskResult = memoryTaskManager.create(task1);


		// проверяем
		assertEquals(memoryTaskManager.get(taskResult.getId()), task1);
//		assertEquals(memoryTaskManager.tasks.get(taskResult.getId()), task1);
	}


	@Test
	void taskManagers() {
		EmptyHistoryManager historyManager = new EmptyHistoryManager();
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

		@Override
		public void remove(int id) {
		}
	}
}