package model;

import static model.Статус.NEW;

/**
 * Задача.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class Задача {
	private String название; //	Название, кратко описывающее суть задачи (например, «Переезд»).
	private String описание; //	Описание, в котором раскрываются детали.
	//	String ид; //	Уникальный идентификационный номер задачи, по которому её можно будет найти.
	protected Integer ид; //	Уникальный идентификационный номер задачи, по которому её можно будет найти.
	//	Long ид; //	Уникальный идентификационный номер задачи, по которому её можно будет найти.
//Status статуc; // enum			Статус,
	private String статуc;

	public Задача() {
		this("Тест", "", -1, NEW);
	}

	public Задача(String название, Integer ид) {
		this(название, "", ид, NEW);
	}

	public Задача(String название, String описание, Integer ид) {
		this.название = название;
		this.описание = описание;
		this.ид = ид;
		this.статуc = NEW;
	}

	public Задача(String название, String описание, Integer ид, String статуc) {
		this.название = название;
		this.описание = описание;
		this.ид = ид;
		this.статуc = статуc;
	}

	public Задача(Задача задача) {
		this.название = задача.название;
		this.описание = задача.описание;
		this.ид = задача.ид;
		this.статуc = задача.статуc;
	}

	public String getНазвание() {
		return название;
	}

	public String getОписание() {
		return описание;
	}

	public Integer getИд() {
		return ид;
	}

	public String getСтатуc() {
		return статуc;
	}

	public void setСтатуc(String статуc) {
		this.статуc = статуc;
	}

	public void setНазвание(String название) {
		this.название = название;
	}

	public void setОписание(String описание) {
		this.описание = описание;
	}

}
