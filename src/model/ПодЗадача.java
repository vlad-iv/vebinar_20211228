package model;

/**
 * Под задача.
 *
 * @author Vladimir Ivanov (ivanov.vladimir.l@gmail.com)
 */
public class ПодЗадача extends Задача {
	Integer эпикИд;

	public ПодЗадача(Integer эпикИд) {
		this.эпикИд = эпикИд;
	}

	public ПодЗадача(String название, String описание, Integer ид, String статуc, Integer эпикИд) {
		super(название, описание, ид, статуc);
		this.эпикИд = эпикИд;
	}

	public Integer getЭпикИд() {
		return эпикИд;
	}
}
