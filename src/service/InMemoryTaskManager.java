package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

public class InMemoryTaskManager implements TaskManager {
	HashMap<Integer, Task> tasks;
	HashMap<Integer, Epic> epics;
	HashMap<Integer, SubTask> subTasks;
	private final HistoryManager historyManager;

	protected int seq = 0;


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

	private int generateId() {
		return ++seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	@Override
	public Task get(int id) {
		return tasks.get(id);
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
		if (saved == null) {
			return;
		}

		saved.setName(epic.getName());
		saved.setDescription(epic.getDescription());
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
		if (savedEpic == null) {
			return;
		}
		// TODO
//		calculateStatus(savedEpic);
		savedEpic.updateStatus();
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

		int epicId = removeSubTask.getEpicId();
		Epic epicSaved = epics.get(epicId);

		epicSaved.getSubTasks().remove(removeSubTask);
		calculateStatus(epicSaved);

	}

	private void calculateStatus(Epic epic) {
		Status status = Status.NEW; // TODO Calculate status
		epic.setStatus(status);
	}

	@Override
	public Epic createEpic(Epic epic) {
		epic.updateStatus();
		return null;
	}

	@Override
	public SubTask createSubTask(SubTask subTask) {
		Epic epic = epics.get(subTask.getEpicId());
		epic.addTask(subTask);
		epic.updateStatus();
		return subTask;
	}

	@Override
	public List<Task> getHistory() {
		return historyManager.getAll();
	}

}
