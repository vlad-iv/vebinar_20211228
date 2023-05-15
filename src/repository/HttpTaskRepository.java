package repository;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Task;
import model.TaskData;
import server.KVClient;
import service.Managers;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class HttpTaskRepository implements TaskRepository {
	private KVClient client;
	private Gson gson = Managers.getGson();

	public HttpTaskRepository(int port) {
		this.client = new KVClient(port); // PORT KVServer
	}

	public HttpTaskRepository(KVClient client) {
		this.client = client;
	}

	@Override
	public TaskData load() {
		String jsonTask = client.load("tasks");
		ArrayList<Task> tasks = gson.fromJson(jsonTask, new TypeToken<ArrayList<Task>>() {
		}.getType());

		String jsonHistory = client.load("history");
		List<Integer> history = gson.fromJson(jsonHistory, new TypeToken<Integer>() {
		}.getType());
		return new TaskData(tasks, history);
	}

	@Override
	public void save(TaskData taskData) {
		String jsonTask = gson.toJson(taskData.getTasks());
		client.save("tasks", jsonTask);
		String jsonHistory = gson.toJson(taskData.getHistory());
		client.save("history", jsonHistory);
	}
}
