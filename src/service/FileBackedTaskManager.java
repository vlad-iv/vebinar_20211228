package service;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import converter.TaskConverter;
import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import model.TaskType;

/**
 * File backed TaskManager.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class FileBackedTaskManager extends InMemoryTaskManager {
	HashMap<TaskType, TaskConverter> converters;
	private final File file;

	public FileBackedTaskManager() {
		this(Managers.getDefaultHistory());
	}

	public FileBackedTaskManager(HistoryManager historyManager) {
		this(historyManager, new File(TASK_CSV));
	}

	public FileBackedTaskManager(File file) {
		this(Managers.getDefaultHistory(), file);
	}


	// ПЛОХОЕ
//	public FileBackedTaskManager(File file, boolean load) {
//		super(new InMemoryHistoryManager());
//		this.file = file;
//		if (load) {
//			loadFromFile();
//		}
//	}
//	public static FileBackedTaskManager loadFromFile(File file) {
//		final FileBackedTaskManager manager = new FileBackedTaskManager(file, true);
//		return manager;
//	}

	// ХОРОШЕЕ
	public FileBackedTaskManager(HistoryManager historyManager, File file) {
		super(historyManager);
		this.file = file;
	}
//	public FileBackedTaskManager(String fileName) {
//		super(new InMemoryHistoryManager());
//		this.file = file;
//	}
//	public FileBackedTaskManager(Path path) {
//		super(new InMemoryHistoryManager());
//		this.file = file;
//	}

	public void init() {
		loadFromFile();
	}

	public static FileBackedTaskManager loadFromFile(File file) {
		FileBackedTaskManager manager = new FileBackedTaskManager(file);
		manager.init();
		return manager;
	}


	@Override
	public List<Task> getAll() {
		return super.getAll();
	}

	@Override
	public Task get(int id) {
		return super.get(id);
	}

	@Override
	public Task create(Task task) {
		Task newTask = super.create(task);
		save();
		return newTask;
	}

	@Override
	public void update(Task task) {
		super.update(task);
	}

	@Override
	public void updateEpic(Epic epic) {
		super.updateEpic(epic);
	}

	@Override
	public void updateSubTask(SubTask subTask) {
		super.updateSubTask(subTask);
	}

	@Override
	public void delete(int id) {
		super.delete(id);
	}

	@Override
	public void deleteSubTask(int id) {
		super.deleteSubTask(id);
	}

	// Todo
	private String toString(Task task) {
//		return converters.get(task.getType()).toString(task);
		return task.getId() + "," + task.getName() + "," + task.getDescription() + "," + task.getEpicId();
	}

	// Todo
	private Task fromString(String value) {
		final String[] columns = value.split(",");
		// TODO
		String name = "";
		String description = "";
		Status status = null;

		TaskType type = TaskType.valueOf(columns[1]);
		Task task = null;
		switch (type) {
			case TASK:
				task = new Task(name, status, description);
				break;

			case SUBTASK:
				task = new SubTask(name, status, description);
				break;

//			case EPIC:
//				task = new Epic();
//				break;
		}
		return task;
	}

	static String toString(HistoryManager manager) {
		String sb = "";
		for (Task task : manager.getAll()) {
			// TODO
		}
		return sb;
	}

	static List<Integer> historyFromString(String value) {
		final String[] ids = value.split(",");
		List<Integer> history = new ArrayList<>();
		for (String id : ids) {
//			история.add(Integer.parseInt(v));
			history.add(Integer.valueOf(id));
		}
		return history;
	}

	// Сохранение в файл
	private void save() {
		try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			// TODO  Заголовок id,type,name,status,description,epic
			for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
				writer.append(toString(entry.getValue()));
				writer.newLine();
			}
			// TODO подзадачи , эпики, история
		} catch (IOException e) {
			throw new RuntimeException("Ошибка в файле: " + file.getAbsolutePath(), e); // TODO ManagerSaveException
		}

	}

	// Восстановление из в файла
	private void loadFromFile() {
//		try {
//			final String s = Files.readString(file.toPath());
//			s.split("\n");
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}

		int maxId = 0;
		try (final BufferedReader reader = new BufferedReader(new FileReader(file, UTF_8))) {
			reader.readLine(); // Пропускаем заголовок
			while (true) {
				String line = reader.readLine();
				// Задачи
				final Task task = fromString(line);
				// TODO добавить задачу в менеджер
				final int id = task.getId();
				if (task.getType() == TaskType.TASK) {
					tasks.put(id, task);
//				} else if () { // TODO

				}

				if (maxId < id) {
					maxId = id;
				}
				if (line.isEmpty()) {
					break;
				}
			}

			String line = reader.readLine();
			// История

		} catch (IOException e) {
			throw new RuntimeException(e); // TODO ManagerSaveException
		}
		// генератор
		seq = maxId;

	}

	public static final String TASK_CSV = "task.csv";
}
