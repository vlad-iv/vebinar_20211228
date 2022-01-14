package controller;

import static model.Статус.NEW;

import java.util.ArrayList;
import java.util.HashMap;

import model.Задача;

/**
 * Менеджер задач.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class МенеджерЗадач {
	HashMap<Integer, Задача> задачи = new HashMap<>();
	int генераторИДЗадач = 0;

	public ArrayList<Задача> получитьВсе() {
//		return new ArrayList<>(задачи.values());
		final ArrayList<Задача> задачиВсе = new ArrayList<>(задачи.values());
		return задачиВсе;
	}

	public Задача найтиПоИД(Integer ид) {
		return задачи.get(ид);
	}

	public Задача создать(Задача задача) {
		int ид = ++генераторИДЗадач;
		final Задача value = new Задача(задача.getНазвание(), задача.getОписание(), ид, NEW);
		if (задачи.containsKey(задача.getИд())) {
			System.out.println("Такая задача существует ид=" + задача.getИд());
			return null;
		}
		задачи.put(задача.getИд(), value);
		return value;
	}

	public Задача обновить(Задача задачаИзмененная) {
		//		if (задачи.containsKey(задачаИзмененная.getИд())) {
//			задачи.put(задачаИзмененная.getИд(), задачаИзмененная);
//		} else {
//			//Ошибка нет такой задачи.
//		}


		final Задача задачаСохраненная = задачи.get(задачаИзмененная.getИд());
		if (задачаСохраненная == null) {
			// Ошибка
			return null;
		}
		задачаСохраненная.setОписание(задачаИзмененная.getОписание());
		задачаСохраненная.setНазвание(задачаИзмененная.getНазвание());
		задачаСохраненная.setСтатуc(задачаИзмененная.getСтатуc());
		return задачаСохраненная;
	}

	public Задача удалить(Integer ид) {
		return задачи.remove(ид);
	}

	public void удалитьВсе() {
		задачи.clear();
	}
}
