package twentquestionsgame;

/**
 * Interface that manages UI operations
 */
public interface UserInterface {
    /**
     * Waits for a yes or no input from user.
     * @return answer Boolean
     */
    boolean nextBoolean();

    /**
     * Get users next line and saves to a string.
     * @return answer String
     */
    String nextLine();
    
    /**
     * Displays the given output message to the user.
     * @param message String
     */
    void print(String message);
    
    /**
     * Displays the given output message to the user.
     * @param message String.
     */
    void println(String message);

    // Messages that will be output by the user interface
    final String BANNER_MESSAGE = "Think of an item, and I will try to guess it.";
    final String PLAY_AGAIN_MESSAGE = "Play me again?";
    final String SAVE_MESSAGE = "Shall I save these games?";
    final String LOAD_MESSAGE = "Shall I load our previous games?";
    final String SAVE_FILENAME_MESSAGE = "What is the file name?";
    final String STATUS_MESSAGE = "Games played: %d\nI won: %d";
}
