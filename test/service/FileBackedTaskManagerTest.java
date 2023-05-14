package service;

class FileBackedTaskManagerTest extends TaskManagerTest<FileBackedTaskManager> {

	@Override
	void init() {
		taskManager = new FileBackedTaskManager();
	}
}