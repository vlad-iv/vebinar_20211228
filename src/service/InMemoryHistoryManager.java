package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import model.Task;

public class InMemoryHistoryManager implements HistoryManager {
	LinkedHashMap map;
	LinkedList list;

	private static class Node {
		Task item;
		Node next;
		Node prev;

		Node(Node prev, Task element, Node next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}

	HashMap<Integer, Node> history = new HashMap<>();
	Node first;
	Node last;

	@Override
	public void add(Task task) {
		Node node = history.get(task.getId());
		removeNode(node);
		linkLast(task);
	}

	@Override
	public List<Task> getAll() {
		ArrayList<Task> list = new ArrayList<>();
		Node current = first;
		while (current != null) {
			// TODO
			current = current.next;
		}
		// 1:null:2, 2:1:null
		// current = 1:null:2
		// list = {1}, current = 2:1:null
		// list = {1, 2}, current = null

		// Обход по связанному списку
		return list;
	}


	@Override
	public void remove(int id) {
		Node node = history.get(id);
		removeNode(node);
	}

	void linkLast(Task task) {
		final Node l = last;
		final Node newNode = new Node(l, task, null);
		last = newNode;
		if (l == null) {
			first = newNode;
		} else {
			l.next = newNode;
		}
	}

	// Удаление из связанного списка
	private void removeNode(Node node) {
		// TODO
//		node.prev.next == node // так было
		node.prev.next = node.next; // так стало
		history.remove(node.item.getId());
	}
}
