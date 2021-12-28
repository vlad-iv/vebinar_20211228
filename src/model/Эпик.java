package model;

import java.util.ArrayList;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class Эпик extends Задача {
	ArrayList<Задача> подзадачи = new ArrayList<>();

//	@Override
//	public String getСтатуc() {
//		return getСтатуc();
//	}

	@Override
	public void setСтатуc(String статуc) {
		super.setСтатуc(вычислитьСтатус());
	}

	private String вычислитьСтатус() {
		return "Вычесленный статус";
	}

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
