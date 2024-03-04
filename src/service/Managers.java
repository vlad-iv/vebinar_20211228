package service;

public class Managers {
	public static TaskManager getDefaults() {
		return new FileBackedTaskManager(getDefaultHistory());
//		return new FileBackedTaskManager(new InMemoryHistoryManager());
	}

	public static HistoryManager getDefaultHistory() {
		return new InMemoryHistoryManager();
	}
}
