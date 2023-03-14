package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Epic;
import model.SubTask;
import model.Task;

public class InMemoryTaskManager implements TaskManager {
	final HashMap<Integer, Task> tasks;
	final HashMap<Integer, Epic> epics;
	final HashMap<Integer, SubTask> subTasks;

	private final HistoryManager historyManager;

	int seq = 0;

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
		tasks.put(task.getId(), task);
		return task;
	}


	@Override
	public Task get(int id) {
		Task task = tasks.get(id);
//		if (task == null) {
//			return null;
//		}
		historyManager.add(task);
		return task;
	}

	@Override
	public void update(Task task) {
		tasks.put(task.getId(), task);
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
		Epic epic = subTask.getEpic();
		Epic savedEpic = epics.get(epic.getId());
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
	}


	@Override
	public void deleteSubTask(int id) {
		SubTask removeSubTask = subTasks.remove(id);
		if (removeSubTask == null) {
			return;
		}

		Epic epic = removeSubTask.getEpic();
		Epic epicSaved = epics.get(epic.getId());

//		epicSaved.getSubTasks().remove(removeSubTask);
		epicSaved.removeTask(removeSubTask);
		calculateStatus(epicSaved);

	}

	@Override
	public void calculateStatus(Epic epicSaved) {

	}

	@Override
	public List<Task> getHistory() {
		return historyManager.getAll();
	}
}
