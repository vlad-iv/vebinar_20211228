package exception;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import service.Managers;

public class ErrorHandler {
	Gson gson = Managers.getGson();
	public void handle(HttpExchange h, ManagerSaveException e) throws IOException {
		e.printStackTrace();
		sendText(h, 400, gson.toJson(e));
	}

	public void handle(HttpExchange h, Exception e) throws IOException {
		e.printStackTrace();
		sendText(h, 500, gson.toJson(e));
	}

	protected void sendText(HttpExchange h, int code, String text) throws IOException {
		byte[] resp = text.getBytes(UTF_8);
		h.getResponseHeaders().add("Content-Type", "application/json");
		h.sendResponseHeaders(code, resp.length);
		h.getResponseBody().write(resp);
	}

}
