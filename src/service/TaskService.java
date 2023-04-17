package service;

import java.util.List;

import dao.CSVTaskRepository;
import dao.TaskRepository;
import model.Epic;
import model.SubTask;
import model.Task;

/**
 * File TaskManager.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class TaskService implements TaskManager {

	private final InMemoryTaskManager taskManager;
	private final TaskRepository taskRepository;

	public TaskService(InMemoryTaskManager taskManager, TaskRepository taskRepository) {
		this.taskManager = taskManager;
		this.taskRepository = taskRepository;
	}

	private void save() {
		taskRepository.save(taskManager.load());
	}

	private void load() {
		taskManager.save(taskRepository.load());
	}

	@Override
	public Task create(Task task) {
		Task newTask = taskManager.create(task);
		save();
		return newTask;
	}

	@Override
	public Task get(int id) {
		Task task = taskManager.get(id);
		save();
		return task;
	}

	@Override
	public void update(Task task) {
		taskManager.update(task);
		save();
	}

	@Override
	public void updateEpic(Epic epic) {
		taskManager.updateEpic(epic);
		save();
	}

	@Override
	public void updateSubTask(SubTask subTask) {
		taskManager.updateSubTask(subTask);
		save();
	}

	@Override
	public List<Task> getAll() {
		return taskManager.getAll();
	}

	@Override
	public void delete(int id) {
		taskManager.delete(id);
		save();
	}

	@Override
	public void deleteSubTask(int id) {
		taskManager.deleteSubTask(id);
		save();
	}


	public static TaskService loadFromFile(String fileName) {
		final TaskService manager = new TaskService(new InMemoryTaskManager(new InMemoryHistoryManager()), new CSVTaskRepository(fileName));
		manager.load();
		return manager;
	}

}
