package model;

import java.util.ArrayList;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class Эпик extends Задача {
	ArrayList<Задача> подзадачи = new ArrayList<>();


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Задача задача = (Задача) o;
		return ид == задача.ид.intValue();
	}

	@Override
	public int hashCode() {
		return ид;
	}

	public void добавитьПозадачу(ПодЗадача задача) {
		подзадачи.add(задача);
	}
}
