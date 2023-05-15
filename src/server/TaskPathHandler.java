package server;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import service.TaskManager;

public class TaskPathHandler implements PathHandler {
	TaskManager manager;
	public TaskPathHandler(TaskManager manager) {
		manager = manager;
	}

	@Override
	public String getPah() {
		return "task";
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		manager.getAll();

	}
}
