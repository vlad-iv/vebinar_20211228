package service;

import java.util.List;

import dao.CsvTaskRepository;
import dao.TaskRepository;
import model.Epic;
import model.SubTask;
import model.Task;

/**
 * FileTaskManager by decorator.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class FileTaskService implements TaskManager {

	private final InMemoryTaskManager taskManager;
	private final TaskRepository taskRepository;

	public FileTaskService(InMemoryTaskManager taskManager, TaskRepository taskRepository) {
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

	@Override
	public List<Task> getHistory() {
		return taskManager.getHistory();
	}


	public static FileTaskService loadFromFile(String fileName) {
		final FileTaskService manager = new FileTaskService(
				new InMemoryTaskManager(new InMemoryHistoryManager()),
				new CsvTaskRepository(() -> fileName)
		);
		manager.load();
		return manager;
	}

}
