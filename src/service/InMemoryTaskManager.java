package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import dao.TaskRepository;
import exception.NotFoundException;
import exception.ValidationException;
import model.Epic;
import model.SubTask;
import model.Task;
import model.TaskData;

public class InMemoryTaskManager implements TaskManager, TaskRepository {
	final HashMap<Integer, Task> tasks;
	final HashMap<Integer, Epic> epics;
	final HashMap<Integer, SubTask> subTasks;

	TreeSet<Task> prioritizedTasks = new TreeSet<>(Comparator.comparing(Task::getStartTime));
//	TreeMap<Instant, Task> prioritizedTasks = new TreeMap<>();

	private final HistoryManager historyManager;
	protected int seq = 0;

	private int generateId() {
		return ++seq;
	}

	public InMemoryTaskManager(HistoryManager historyManager) {
//		this.historyManager = new InMemoryHistoryManager(); // 1
//		this.historyManager = Managers.getDefaultHistory(); // 2
		this.historyManager = historyManager; // 3
		this.tasks = new HashMap<>();
		this.epics = new HashMap<>();
		this.subTasks = new HashMap<>();
	}

	@Override
	public Task create(Task task) {
		task.setId(generateId());
		addPrioritized(task);
		tasks.put(task.getId(), task);
		return task;
	}

	private void addPrioritized(Task task) {
		for (Task t : prioritizedTasks) {

			// Проверка на пересечение	task1 c task
			// выходим если пересеклись.
			// выбросить исключение
			throw new ValidationException("Пересечение с задачей ....");
		}
		prioritizedTasks.add(task);
	}

	@Override
	public Task get(int id) {
		Task task = tasks.get(id);
		if (task == null) {
			throw new NotFoundException("Задача с ид=" + id);
		}
//		Optional.of(task)
//				.orElseThrow(() -> new RuntimeException("Задача с ид=" + id));
		return task;
	}

	@Override
	public void update(Task task) {
		Task original = tasks.get(task.getId()); // 1
		if (original == null) {
			throw new NotFoundException("Task id=" + task.getId());
		}
		addPrioritized(task);
		prioritizedTasks.remove(original); // O(logN)
		prioritizedTasks.add(task); //  O(logN)
//		prioritizedTasks.remove(original.getStartTime()); // O(logN)
//		prioritizedTasks.put(task.getStartTime(), task); // O(logN)

		tasks.put(task.getId(), task); // 1
//		Task saved = tasks.get(task.getId());
//		saved.setName(task.getName());
//		saved.setStatus(task.getStatus());
		// ....

	}

	@Override
	public void updateEpic(Epic epic) {
//		Epic saved = epics.get(epic.getId());
//		epic.setStatus(saved.getStatus());
//		epic.setSubTasks(saved.getSubTasks());
//		epics.put(epic.getId(), epic); // Не подходит
		Epic saved = epics.get(epic.getId());
		saved.setName(epic.getName());
		// ....

	}

	@Override
	public void updateSubTask(SubTask subTask) {
//		Epic saved = epics.get(epic.getId());
//		epic.setStatus(saved.getStatus());
//		epic.setSubTasks(saved.getSubTasks());
//		epics.put(epic.getId(), epic); // Не подходит
		Integer epicId = subTask.getEpicId();
		Epic savedEpic = epics.get(epicId);
//		calculateEpicStatus(savedEpic);
//		savedEpic.calculateEpicStatus();
		// ....

	}

	@Override
	public List<Task> getAll() {
		return new ArrayList<>(tasks.values());
	}

	@Override
	public void delete(int id) {
		tasks.remove(id);
		historyManager.remove(id);
	}


	@Override
	public void deleteSubTask(int id) {
		SubTask removeSubTask = subTasks.remove(id);
		if (removeSubTask == null) {
			return;
		}

		int epicId = removeSubTask.getEpicId();
		Epic epicSaved = epics.get(epicId);

//		epicSaved.getSubTasks().remove(removeSubTask);
		epicSaved.removeTask(removeSubTask);
		calculateStatus(epicSaved);

	}

	private void calculateStatus(Epic epicSaved) {

	}

	@Override
	public List<Task> getHistory() {
		return historyManager.getAll();
	}

	@Override
	public TaskData load() {
		List<Task> tasks = new ArrayList<>(this.tasks.values());
		tasks.addAll(this.subTasks.values());
		tasks.addAll(this.epics.values());
		List<Integer> history = new ArrayList<>();
		for (Task task : historyManager.getAll()) {
			history.add(task.getId());
		}

		TaskData taskData = new TaskData(tasks, history);
		return taskData;
	}

	@Override
	public void save(TaskData taskData) {
		int maxId = 0;
		HashMap<Integer, Task> taskById = new HashMap<>();
		for (Task task : taskData.getTasks()) {
			int id = task.getId();
			taskById.put(id, task);
			switch (task.getType()) {
				case TASK:
					tasks.put(id, task);
					break;
				case SUBTASK:
					subTasks.put(id, (SubTask) task);
					break;
				case EPIC:
					epics.put(id, (Epic) task);
					break;
			}
			if (maxId < id) {
				maxId = id;
			}
		}

		// TODO Заполнить подзадачи у эпика.

		seq = maxId;
		for (Integer id : taskData.getHistory()) {
			historyManager.add(taskById.get(id));
		}
	}


}
