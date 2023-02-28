package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Epic;
import model.SubTask;
import model.Task;

public class TaskManager {
	private HashMap<Integer, Task> tasks;
	HashMap<Integer, Epic> epics;
	HashMap<Integer, SubTask> subTasks;
	int seq = 0;

	private int generateId() {
		return ++seq;
	}

	public TaskManager() {
		this.tasks = new HashMap<>();
	}

	public Task create(Task task) {
		task.setId(generateId());
		tasks.put(task.getId(), task);
		return task;
	}


	public Task get(int id) {
		return tasks.get(id);
	}

	public void update(Task task) {
		tasks.put(task.getId(), task);
//		Task saved = tasks.get(task.getId());
//		saved.setName(task.getName());
//		saved.setStatus(task.getStatus());
		// ....

	}

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
		// ....

	}

	public void updateSubTask(SubTask subTask) {
//		Epic saved = epics.get(epic.getId());
//		epic.setStatus(saved.getStatus());
//		epic.setSubTasks(saved.getSubTasks());
//		epics.put(epic.getId(), epic); // Не подходит
		Epic epic = subTask.getEpic();
		Epic savedEpic = epics.get(epic.getId());
		if (savedEpic == null) {
			return;
		}
		// TODO
//		calculateStatus(savedEpic);
		savedEpic.calculateEpicStatus();
		// ....

	}

	public List<Task> getAll() {
		return new ArrayList<>(tasks.values());
	}

	public void delete(int id) {
		tasks.remove(id);
	}

	public void deleteSubTask(int id) {
		SubTask removeSubTask = subTasks.remove(id);

		Epic epic = removeSubTask.getEpic();
		Epic epicSaved = epics.get(epic.getId());

		epicSaved.getSubTasks().remove(removeSubTask);
		calculateStatus(epicSaved);

	}

	private void calculateStatus(Epic epic) {
		// TODO
	}
}
