package vagrant.api;

public interface CommandIOListener {
    /**
     * Called when there's new input send to a Vagrant command.
     * {@code null} passed on end of stream.
     */
    void onInput(String input);

    /**
     * Called when there's new output printed by a Vagrant command.
     * stdout and stderr are combined.
     * {@code null} passed on end of stream.
     */
    void onOutput(String output);
}
