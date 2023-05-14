package dao;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import model.TaskData;
import model.TaskType;

public class CsvTaskRepository implements TaskRepository {
	private final FileNameProvider provider;

	public CsvTaskRepository(FileNameProvider provider) {
		this.provider = provider;
	}

	@Override
	public TaskData load() {
//		List<String> lines;
//		try {
//			lines = Files.readAllLines(Path.of(provider.getFileName()), StandardCharsets.UTF_8);
//		} catch (IOException e) {
//			throw new RuntimeException(e); // TODO ManagerException
//		}
		List<String> lines;
		ClassLoader classLoader = getClass().getClassLoader();
		try (InputStream inputStream = Objects.requireNonNull(classLoader.getResourceAsStream(provider.getFileName()))) {
			String allLines = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
			lines = Arrays.stream(allLines.split(System.lineSeparator())).toList();
		} catch (IOException e) {
			throw new RuntimeException(e); // TODO ManagerException
		}

		List<Task> tasks = new ArrayList<>();
		for (String line : lines) {
			final Task task = parseTask(line);
			tasks.add(task);
			if (line.isEmpty()) {
				break;
			}
		}

		List<Integer> history = parseHistory(lines.getLast());
		return new TaskData(tasks, history);
	}

	@Override
	public void save(TaskData taskData) {
		String fileName = provider.getFileName();
		try (final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, StandardCharsets.UTF_8))) {
			// TODO  Заголовок id,type,name,status,description,epic
			for (Task task : taskData.getTasks()) {
				writer.append(taskToLine(task));
				writer.newLine();
			}
			writer.newLine();
			writer.write(historyToLine(taskData.getHistory()));
			writer.newLine();
		} catch (IOException e) {
			throw new RuntimeException("Error in " + fileName, e); // TODO ManagerSaveException
		}
	}

	private List<Integer> parseHistory(String line) {
		return null;
	}

	private String historyToLine(List<Integer> history) {
		return "";
	}

	private Task parseTask(String line) {
		String[] columns = line.split(",");
		// TODO
		int id = 0;
		String name = "";
		String description = "";
		Status status = null;
		TaskType type = TaskType.valueOf(columns[1]);
		Task task = null;
		switch (type) {
			case TASK:
				task = new Task(id, name, status, description, Instant.now(), 0);
				break;

			case SUBTASK:
				task = new SubTask(id, name, status, description, Instant.now(), 0, 0);
				break;

			case EPIC:
				task = new Epic(id, name, status, description, Instant.now(), 0);
				break;
		}
		return task;
	}


	private String taskToLine(Task task) {
		return "null";
	}
}
