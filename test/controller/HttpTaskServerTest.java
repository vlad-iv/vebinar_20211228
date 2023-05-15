package controller;

import static model.Status.NEW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Task;
import service.InMemoryTaskManager;
import service.Managers;
import service.TaskManager;

class HttpTaskServerTest {
	Gson gson = Managers.getGson();
	TaskManager taskManager;
	HttpTaskServer taskServer;
	private Task task;

	@BeforeEach
	void init() throws IOException {
		taskManager = new InMemoryTaskManager();
		taskServer = new HttpTaskServer(taskManager);
		task = new Task(1, "Test Task", NEW, "Test Task description", Instant.now(), 15);
	}

	@AfterEach
	void stop() {
		// TODO
//		taskServer.stop();
	}

	@DisplayName("....")
	@Test
	void shouldReturnTaskList() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		URI url = URI.create("http://localhost:8080/tasks/task");
		HttpRequest request = HttpRequest.newBuilder().uri(url).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		assertEquals(200, response.statusCode());

		final List<Task> tasks = gson.fromJson(response.body(), new TypeToken<ArrayList<Task>>() {
		}.getType());

		assertNotNull(tasks);
		assertEquals(1, tasks.size(), "Task size");
		Task actual = tasks.get(0);
//		assertEquals(task, actual, "task");
		assertTask(actual);

	}

	private void assertTask(Task actual) {
		assertEquals(task.getId(), actual.getId(), "task id");
		assertEquals(task.getName(), actual.getName(), "task name");
		//...
	}
}