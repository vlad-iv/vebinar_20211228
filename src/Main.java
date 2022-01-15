import static model.Статус.IN_PROGRESS;
import static model.Статус.NEW;

import controller.Менеджер;
import controller.Менеджеры;
import model.Эпик;

public class Main {
	public static void main(String[] args) {
		final Менеджер менеджер = Менеджеры.поУмолчанию();
		Эпик эпик1 = new Эпик("Эпик 1", "Эпик описание 1");

		final Эпик эпик1Новый = менеджер.создатьЭпик(эпик1);
		final Integer эпик1Ид = эпик1Новый.getИд();
		эпик1 = new Эпик("Новое название эпик 1", эпик1Новый.getОписание(), эпик1Ид, IN_PROGRESS);
		менеджер.обновитьЭпик(эпик1);
		final Эпик эпик1Сохраненный = менеджер.найтиЭпикПоИД(эпик1Ид);
		if (менеджер.история().isEmpty()) {
			System.out.println("не работает метод история");
		}

		if (!эпик1Сохраненный.getСтатуc().equals(NEW)) {
			System.out.println("не работает метод обновить эпик");
		}

		менеджер.удалитьЭпикПоИД(эпик1Ид);

		if (!менеджер.получитьВсеЭпики().isEmpty()) {
			System.out.println("не работает метод удалить эпик");
		}
	}
}
