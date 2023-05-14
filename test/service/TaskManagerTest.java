package service;

import static model.Status.NEW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Task;

abstract class TaskManagerTest<T extends TaskManager> {

	T taskManager;


	abstract T createManager();

	@BeforeEach
	void beforeEach() {
		taskManager = createManager();
	}

	@Test
	void create() {
		Task task = new Task(0, "test", NEW, "testing", Instant.now(), 123);

		Task result = taskManager.create(task);
		Task returnTask = taskManager.get(task.getId());

		assertNotNull(result);
		assertTrue(result.getId() > 0, "Task id");
		assertEquals(task.getName(), result.getName(), "Task name");
	}

	@Test
	void get() {
		Task task = new Task(0, "test", NEW, "testing", Instant.now(), 123);
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