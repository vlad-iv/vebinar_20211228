package service;

import java.io.IOException;

import server.KVServer;

class HttpTaskManagerTest extends TaskManagerTest<FileBackedTaskManager> {
	KVServer kvServer;

	{
		try {
			kvServer = new KVServer();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	void init() {
		taskManager = new HttpTaskManager(8080);
	}
}