package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Task;
import model.TaskData;
import server.KVClient;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class HttpTaskManager extends FileBackedTaskManager {
	private KVClient client;
	private Gson gson = Managers.getGson();

	public HttpTaskManager(int port) {
		this.client = new KVClient(port);
		client.register();
//		this.client = new KVClientBuilder()
//				.port(port)
//				.create();
	}

	@Override
	public TaskData load() {
		String jsonTasks = client.load("tasks");
		tasks = gson.fromJson(jsonTasks,
				new TypeToken<HashMap<Integer, Task>>() {
				}.getType());
		String jsonEpic = client.load("epic");
		epics = gson.fromJson(jsonEpic,
				new TypeToken<HashMap<Integer, Task>>() {
				}.getType());
		String jsonHistory = client.load("history");
		ArrayList<Integer> history = gson.fromJson(jsonHistory,
				new TypeToken<ArrayList<Integer>>() {
				}.getType());
		// todo add history

		return null;
	}

	@Override
	public void save(TaskData taskData) {
		client.save("tasks", gson.toJson(tasks));
		client.save("epic", gson.toJson(epics));
		client.save("history", gson.toJson(historyStorage.getAll().stream()
				.map(Task::getId)
				.collect(Collectors.toList())));
		// ...
	}
}
