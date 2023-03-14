package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Эпик")
class EpicTest {

	@Test
	@DisplayName("должен совпадать со своей копией")
	void shouldEqualsWithCopy() {
		Epic epic = new Epic("name", Status.NEW, "desc");
		Epic epicExpected = new Epic("name1", Status.NEW, "desc");
		assertEquals(epicExpected, epic, "Эпики должны совпадать");
		assertEqualsTask(epicExpected, epic, "Эпики должны совпадать");
	}

	private static void assertEqualsTask(Task expected, Task actual, String message) {
		assertEquals(expected.getId(), actual.getId(), message + ", id");
		assertEquals(expected.getName(), actual.getName(), message + ", name");

	}

}