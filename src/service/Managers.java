package service;

import service.history.HistoryManager;
import service.history.InMemoryHistoryManager;

public class Managers {
	public static TaskManager getDefaults() {
//		return new InMemoryTaskManager(getDefaultHistory());
		return new InMemoryTaskManager(new InMemoryHistoryManager());
	}

	public static HistoryManager getDefaultHistory() {
		return new InMemoryHistoryManager();
	}
}
