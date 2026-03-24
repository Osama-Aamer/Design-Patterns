package memento.guistate.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Memento implements IMemento {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    private final int[] options;
    private final boolean isSelected;
    private final LocalDateTime timestamp;

    public Memento(int[] options, boolean isSelected) {
        this.options = options.clone();
        this.isSelected = isSelected;
        this.timestamp = LocalDateTime.now();
        System.out.println("Memento created at " + getTimestamp());
    }

    public int[] getOptions() {
        return options;
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public String getTimestamp() {
        return timestamp.format(FORMATTER);
    }

    @Override
    public String getDescription() {
        String[] colorNames = {"Red", "Blue", "Yellow"};
        String c1 = colorNames[options[0]];
        String c2 = colorNames[options[1]];
        String c3 = colorNames[options[2]];
        String cb = isSelected ? "checked" : "unchecked";
        return String.format("[%s]  Box1=%s  Box2=%s  Box3=%s  CB=%s",
                getTimestamp(), c1, c2, c3, cb);
    }
}
