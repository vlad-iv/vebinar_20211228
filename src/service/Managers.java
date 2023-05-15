package service;

import java.time.Instant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.InstantAdapter;

public class Managers {
	public static TaskManager getDefaultTaskManager() {
		return new HttpTaskManager(8078);
	}

	public static HistoryManager getDefaultHistory() {
		return new InMemoryHistoryManager();
	}

	public static Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Instant.class, new InstantAdapter());
		return gsonBuilder.create();
	}
}
