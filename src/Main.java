import static model.Статус.DONE;
import static model.Статус.NEW;

import model.Задача;

public class Main {
	public static void main(String[] args) {
		final Менеджер менеджер = new Менеджер();
		final Задача простаяЗадачаПараметр = new Задача("Простая задача", 1);
		менеджер.создатьЗадачу(простаяЗадачаПараметр);
		простаяЗадачаПараметр.setСтатуc(DONE);
		final Задача простаяЗадача = менеджер.найтиЗадачуПоИД(простаяЗадачаПараметр.getИд());
//        if (!простаяЗадача.equals(простаяЗадачаПараметр)) {
		if (!простаяЗадача.getСтатуc().equals(NEW)) {
			System.out.println("не работает метод создатьЗадачу");
		}
	}
}
