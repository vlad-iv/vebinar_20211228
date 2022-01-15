package controller;

import static model.Статус.NEW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.Задача;
import model.ПодЗадача;
import model.Эпик;

/**
 * Менеджер задач.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class ВПамятиМенеджер implements Менеджер {
//	МенеджерЗадач менеджерЗадач = new МенеджерЗадач();

	HashMap<Integer, ПодЗадача> подЗадачи = new HashMap<>();
	int генераторИДПодЗадач = 0;

	HashMap<Integer, Эпик> эпики = new HashMap<>();
	int генераторИДЭпики = 0;

	//	Получение списка всех эпиков.
	@Override
	public List<Эпик> получитьВсеЭпики() {
		return new ArrayList<>(эпики.values());
	}

	//	Получение списка всех подзадач определённого эпика.
	@Override
	public List<ПодЗадача> получитьВсеПодзадачиПоЭпику(Эпик эпик) {
		return эпики.get(эпик.getИд()).getПодзадачи();
	}

	@Override
	public List<Задача> получитьВсеПодзадачиПоЭпику(Integer эпикИД) {
		return new ArrayList<>();
	}

	@Override
	public ПодЗадача найтиПодЗадачуПоИД(Integer ид) {
		final ПодЗадача подЗадача = подЗадачи.get(ид);
		if (история.size() == 10) {
			история.remove(0);
		}
		история.add(подЗадача);
		return подЗадача;
	}

	@Override
	public Эпик найтиЭпикПоИД(Integer ид) {
		final Эпик эпик = эпики.get(ид);
		if (эпик == null) {
			return null;
		}
		if (история.size() == 10) {
			история.remove(0); // Удалить в начале
		}
		история.add(эпик); // Добавить в конец
		return эпик;
	}

	@Override
	public ПодЗадача создатьПодЗадачу(ПодЗадача задача) {
		if (!эпики.containsKey(задача.getЭпикИд())) {
			System.out.println("Не найден эпик ид=" + задача.getЭпикИд());
			return null;
		}
		int ид = ++генераторИДПодЗадач;
		final ПодЗадача подЗадачаНовая = new ПодЗадача(задача.getНазвание(), задача.getОписание(), ид, NEW, задача.getЭпикИд());
		подЗадачи.put(задача.getИд(), подЗадачаНовая);
		final Эпик эпик = эпики.get(задача.getЭпикИд());
		эпик.добавитьПозадачу(задача);
		return подЗадачаНовая;
	}

	@Override
	public Эпик создатьЭпик(Эпик эпик) {
		int ид = ++генераторИДЭпики;
		Эпик эпикНовый = new Эпик(эпик.getНазвание(), эпик.getОписание(), ид, NEW);
		эпики.put(ид, эпикНовый);
		return эпикНовый;
	}

	@Override
	public void обновитьПодЗадачу(ПодЗадача подЗадачаИзмененная) {
		final ПодЗадача подЗадачаСохраненная = подЗадачи.get(подЗадачаИзмененная.getИд());
		if (подЗадачаСохраненная == null) {
			return;
		}
		подЗадачи.put(подЗадачаИзмененная.getИд(), подЗадачаИзмененная);
//			рассчитатьИСохранитьСтатусЭпика(подЗадачаИзмененная.getЭпик()); // TODO
	}

	@Override
	public void обновитьЭпик(Эпик эпик) {
		final Эпик эпикСохраненный = эпики.get(эпик.getИд());
		if (эпикСохраненный == null) {
			return;
		}
		эпикСохраненный.setНазвание(эпик.getНазвание());
		эпикСохраненный.setОписание(эпик.getОписание());
	}

	@Override
	public void удалитьПодЗадачуПоИД(Integer ид) {
		final ПодЗадача подЗадача = подЗадачи.remove(ид);
		if (подЗадача == null) {
			return;
		}
		final Эпик эпик = эпики.get(подЗадача.getЭпикИд());
		эпик.getПодзадачи().remove(подЗадача);
	}

	@Override
	public void удалитьЭпикПоИД(Integer ид) {
		final Эпик эпик = эпики.remove(ид);
		if (эпик == null) {
			return;
		}
		for (ПодЗадача подЗадача : эпик.getПодзадачи()) {
			подЗадачи.remove(подЗадача.getИд());
		}
	}

	//	List<Задача> история = new ArrayList<>(10);
	List<Задача> история = new LinkedList<>();

	@Override
	public List<Задача> история() {
		return история;
	}
}
