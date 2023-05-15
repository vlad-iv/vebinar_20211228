package server;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import exception.ManagerSaveException;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class KVClient {
	private final String url;
	private String authToken;

	public KVClient(int port) {
		url = "http://localhost:" + port + "/";
	}

	public void register() {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url + "register"))
					.GET()
					.build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() != 200) {
				throw new ManagerSaveException("Can't do save request, status code: " + response.statusCode());
			}
			authToken = response.body();
		} catch (IOException | InterruptedException e) {
			throw new ManagerSaveException("Can't do save request", e);
		}
	}

	public String load(String key) { // tasks, epics, subtasks , history
		return null;
	}

	public void save(String key, String value) {

	}
}
