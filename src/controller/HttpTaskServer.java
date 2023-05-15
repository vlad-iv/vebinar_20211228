package controller;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.Instant;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import model.Epic;
import model.Status;
import model.Task;
import service.Managers;
import service.TaskManager;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class HttpTaskServer {
	TaskManager manager;
	HttpServer server;

	Gson gson = Managers.getGson();

	public HttpTaskServer() {
		this.manager = Managers.getDefaultTaskManager();
	}

	public HttpTaskServer(TaskManager manager) throws IOException {
		this.manager = manager;
		server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
		server.createContext("/tasks", this::task);

//		server.createContext("/tasks/tasks", this::task);
//		server.createContext("/tasks/epics", this::epics);



	}
	private void task(HttpExchange httpExchange) throws IOException {
		final String path = httpExchange.getRequestURI().getPath().substring(7);
		switch (path) {
			case "task": taskHandler(httpExchange);
		}
	}

	private void taskHandler(HttpExchange httpExchange) throws IOException {
		sendText(httpExchange, gson.toJson(manager.getAll()));
	}

	protected void sendText(HttpExchange h, String text) throws IOException {
		byte[] resp = text.getBytes(UTF_8);
		h.getResponseHeaders().add("Content-Type", "application/json");
		h.sendResponseHeaders(200, resp.length);
		h.getResponseBody().write(resp);
	}
	public static void main(String[] args) {
		final Gson gson = Managers.getGson();
		Task task = new Task(1, "Тест", Status.NEW, "Описание", Instant.now(), 15);
		final HashMap<Integer, Task> map = new HashMap<>();
		map.put(task.getId(), task);

		Epic epic = new Epic(2, "Эпик", "Описание эпик", Instant.now());
		epic.setId(2);
		epic.setStartTime(Instant.now());
		map.put(epic.getId(), epic);

		System.out.println(gson.toJson(task));
		final String json = gson.toJson(map);
		System.out.println(json);
		final HashMap<Integer, Task> mapRestored = gson.fromJson(json,
				new TypeToken<HashMap<Integer, Task>>() {
				}.getType());
		System.out.println("Restored:");
		System.out.println(mapRestored);
		System.out.println(mapRestored.get(1));

		System.out.println("Было:");
		System.out.println(map.get(2));
		System.out.println("Стало:");
		System.out.println(mapRestored.get(2));


		// KVServer (8078) <--> {[KVClient <-->  HttpTaskManager] <--> HttpTaskServer} (8080) <--> FRONTEND
		// HttpTaskServer получает id или json (body) -> Объект (Задача, Подзадача, Эпик)
		// HttpTaskManager
	}
}
