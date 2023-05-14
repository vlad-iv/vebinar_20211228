package service;

class FileBackedTaskManagerTest extends TaskManagerTest<FileBackedTaskManager> {

	@Override
	FileBackedTaskManager createManager() {
		return new FileBackedTaskManager();
	}
}