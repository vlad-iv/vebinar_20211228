package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Status;
import model.Task;

class InMemoryHistoryManagerTest {

	@Test
	void testRemoveFirst() {
		// Prepare
		InMemoryHistoryManager manager = new InMemoryHistoryManager();
		Task task1 = new Task("task1", Status.NEW, "task1 desc");
		manager.add(task1);
		Task task2 = new Task("task2", Status.NEW, "task1 desc");
		manager.add(task2);
		Task task3 = new Task("task3", Status.NEW, "task1 desc");
		manager.add(task3);

		manager.remove(task1.getId());

		assertEquals(manager.getAll(), List.of(task2, task3));
	}

}