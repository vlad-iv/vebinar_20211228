package service;

public class Managers {
	public static TaskManager getDefaults() {
		return new InMemoryTaskManager(getDefaultHistory());
//		return new InMemoryTaskManager(new InMemoryHistoryManager());
	}

	public static HistoryManager getDefaultHistory() {
		return new InMemoryHistoryManager();
	}
}
