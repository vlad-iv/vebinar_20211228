package exception;

public class ManagerSaveException extends RuntimeException {
	public ManagerSaveException(String s) {
		super(s);
	}

	public ManagerSaveException(String s, Exception e) {
		super(s, e);
	}
}
