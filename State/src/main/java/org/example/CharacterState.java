package org.example;

public abstract class CharacterState {
    protected GameCharacter character;

    public CharacterState(GameCharacter character) {
        this.character = character;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    /**1111 Display the current level name. */
    public abstract String getLevelName();

    /** Display available actions and handle one turn. */
    public abstract void action();

    /** Train: always available — increases XP. */
    public abstract void train();

    /** Meditate: available from Intermediate onwards — increases HP. */
    public void meditate() {
        System.out.println("  [!] You cannot meditate at the " + getLevelName() + " level.");
    }

    /** Fight: available from Expert onwards — costs HP, gains XP. */
    public void fight() {
        System.out.println("  [!] You cannot fight at the " + getLevelName() + " level.");
    }
}
