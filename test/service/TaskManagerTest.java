package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Task;

abstract class TaskManagerTest<T extends TaskManager> {

	T taskManager;


	abstract void init();
	@Test
	void create() {
		Task task = new Task();

		Task result = taskManager.create(task);
		Task returnTask = taskManager.get(task.getId());

		assertNotNull(result);
		assertTrue(result.getId() > 0, "Task id");
		assertEquals(task.getName(), result.getName(), "Task name");
	}

	@Test
	void get() {
		Task task = new Task();
		Task result = taskManager.create(task);

		Task returnTask = taskManager.get(task.getId());

	}

	@Test
	void update() {
	}

	@Test
	void updateEpic() {
	}

	@Test
	void updateSubTask() {
	}

	@Test
	void getAll() {
	}

	@Test
	void delete() {
	}

	@Test
	void deleteSubTask() {
	}
}