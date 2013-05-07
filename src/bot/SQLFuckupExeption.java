package bot;

public class SQLFuckupExeption extends Exception {
	private static final long serialVersionUID = 3214568341732809515L;

	public SQLFuckupExeption(String message) {
        super(message);
    }
}
