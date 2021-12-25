import static model.Статус.NEW;

import java.util.ArrayList;
import java.util.HashMap;

import model.Задача;
import model.ПодЗадача;
import model.Эпик;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class Менеджер {
	HashMap<Integer, Задача> задачи = new HashMap<>();
	int генераторИДЗадач = 0;
	HashMap<Integer, Задача> подЗадачи = new HashMap<>();
	int генераторИДПодЗадач = 0;

	HashMap<Integer, Эпик> эпики = new HashMap<>();
	int генераторИДЭпики = 0;

	HashMap<Integer, ArrayList<ПодЗадача>> эпикиПодзадачи = new HashMap<>();
//	HashMap<Эпик, ArrayList<ПодЗадача>> эпикиПодзадачи = new HashMap<>();
//	эпикиПодзадачи.get(new Эпик(ид));
	//	Получение списка всех задач.
	public ArrayList<Задача> получитьВсеЗадачи() {
//		return new ArrayList<>(задачи.values());
		final ArrayList<Задача> задачиВсе = new ArrayList<>(задачи.values());
		задачиВсе.addAll(подЗадачи.values());
		return задачиВсе;
	}

	//	Получение списка всех эпиков.
	public ArrayList<Задача> получитьВсеЭпики() {
		return new ArrayList<>(эпики.values());
	}

	//	Получение списка всех подзадач определённого эпика.
	public ArrayList<Задача> получитьВсеПодзадачиПоЭпику(Задача эпик) {
		return получитьВсеПодзадачиПоЭпику(эпик.getИд());
	}

	public ArrayList<Задача> получитьВсеПодзадачиПоЭпику(Integer эпикИД) {
		return new ArrayList<>();
	}

	//	Получение задачи любого типа по идентификатору.
	public Задача найтиЗадачуПоИД(Integer ид) {
		return задачи.get(ид);
	}

	public ПодЗадача найтиПодЗадачуПоИД(Integer ид) {
		return new ПодЗадача();
	}

	public Эпик найтиЭпикПоИД(Integer ид) {
		return new Эпик();
	}

	//	Добавление новой задачи, эпика и подзадачи. Сам объект должен передаваться в качестве параметра.
	// Автогенерация ид в менджере
	public Задача создатьЗадачу(Задача задача) {
		int ид = ++генераторИДЗадач;
		final Задача value = new Задача(задача.getНазвание(), задача.getОписание(), ид , NEW);
		if (задачи.containsKey(задача.getИд())) {
			System.out.println("Такая задача существует ид=" + задача.getИд());
			return null;
		}
		задачи.put(задача.getИд(), value);
		return value;
	}
	// ид приходит снаружи
//	public void создатьЗадачу(Задача задача) {
//		return new Задача();
//	}

	public ПодЗадача создатьПодЗадачу(ПодЗадача задача) {
		int ид = ++генераторИДЗадач;
		final ПодЗадача value = new ПодЗадача(задача.getНазвание(), задача.getОписание(), ид , NEW);
		if (задачи.containsKey(задача.getИд())) {
			System.out.println("Такая задача существует ид=" + задача.getИд());
			return null;
		}
		if (!эпики.containsKey(задача.getЭпик().getИд())) {
			System.out.println("Не найден эпик ид=" + задача.getЭпик().getИд());
			return null;
		}
		подЗадачи.put(задача.getИд(), value);
		final Эпик эпик = эпики.get(задача.getЭпик().getИд());
		эпик.добавитьПозадачу(задача);
		return value;
	}

	public Эпик создатьЭпик(Эпик задача) {
		return new Эпик();
	}

	//	Обновление задачи любого типа по идентификатору. Новая версия объекта передаётся в виде параметра.
	public Задача обновитьЗадачу(Задача задача) {
		return new Задача();
	}
	// ид приходит снаружи
//	public void создатьЗадачу(Задача задача) {
//		return new Задача();
//	}

	public ПодЗадача обновитьПодЗадачу(ПодЗадача задача) {
		return new ПодЗадача();
	}

	public Эпик обновитьЭпик(Эпик задача) {
		return new Эпик();
	}

	//	Удаление ранее добавленных задач — всех и по идентификатору.
	public Задача удалитьЗадачуПоИД(Integer ид) {
		return new Задача();
	}

	public ПодЗадача удалитьПодЗадачуПоИД(Integer ид) {
		return new ПодЗадача();
	}

	public Эпик удалитьЭпикПоИД(Integer ид) {
		return new Эпик();
	}
}
