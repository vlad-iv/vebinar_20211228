package controller;

/**
 * // TODO .
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class Менеджеры {
	public static Менеджер поУмолчанию() {
		return new ВПамятиМенеджер();
	}
}
