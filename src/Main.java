import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import service.Managers;
import service.TaskManager;

public class Main {

	public static void main(String[] args) {

		TaskManager taskManager = Managers.getDefaults();
		Task task = taskManager.create(new Task("Новая задача", Status.NEW, "описание"));
		System.out.println("Create task: " + task);

		Task taskFromManager = taskManager.get(task.getId());
		System.out.println("Get task: " + taskFromManager);

		Task taskUpdated = new Task(taskFromManager.getId(), "Новая задача Обновление", Status.DONE, taskFromManager.getDescription());
		taskManager.update(taskUpdated);
		System.out.println("Update task: " + taskUpdated);

		taskManager.delete(taskFromManager.getId());
		System.out.println("Delete: " + task);


		Epic epic = taskManager.createEpic(new Epic("Новый эпик", "описание"));
		System.out.println("Create epic: " + epic);

		SubTask subTask = taskManager.createSubTask(new SubTask("Новая задача", Status.IN_PROGRESS, "описание подзадачи", epic));
		System.out.println("Create subtask: " + subTask);

	}

	private static void printEpic(Epic epic) {
		System.out.println("Epic: id=" + epic.getId() + ", name:" + epic.getName());
		System.out.println("Subtask:");
		for (SubTask subTask : epic.getSubTasks()) {
			printSubTask(subTask);
		}
	}

	private static void printSubTask(SubTask subTask) {

	}
}
