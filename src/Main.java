import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Status;
import model.Task;
import service.FileBackedTaskManager;
import service.FileTaskService;
import service.Managers;
import service.TaskManager;

public class Main {
	public static void main(String[] args) {
		TaskManager taskManager = Managers.getDefaults();
		Task task = taskManager.create(new Task(0, "Новая задача", Status.NEW, "описание", Instant.now(), 100));
		System.out.println("Create task: " + task);

		Task taskFromManager = taskManager.get(task.getId());
		System.out.println("Get task: " + taskFromManager);

		taskFromManager.setName("New name");
		taskManager.update(taskFromManager);
		System.out.println("Update task: " + taskFromManager);

		taskManager.delete(taskFromManager.getId());
		System.out.println("Delete: " + task);

//		TaskManager taskManagerReload = FileBackedTaskManager.loadFromFile(Path.of("task.csv"));

		TaskManager taskManagerReload = FileBackedTaskManager.loadFromFile(new File("task.csv"));
		// TODO taskManager == taskManagerReload

		TaskManager taskServiceReload = FileTaskService.loadFromFile("task.csv");
		// TODO taskManager == taskServiceReload

		final List<Task> tasks = new ArrayList<>();
		Map<Integer, Task> taskMap = new HashMap<>();
		tasks.forEach(t -> {
			System.out.println(t);
			taskMap.put(t.getId(), t);
		});
	}
}
