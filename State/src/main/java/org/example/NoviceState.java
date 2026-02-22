package org.example;

/**
 * NOVICE level
 * Available action : Train
 * XP to advance    : 30
 */
public class NoviceState extends CharacterState {
    private static final int XP_TO_ADVANCE = 30;

    public NoviceState(GameCharacter character) {
        super(character);
        System.out.println("\n  ★ You are now a NOVICE. Train hard to advance!");
    }

    @Override
    public String getLevelName() {
        return "Novice";
    }

    @Override
    public void action() {
        String[] options = {"Train"};
        switch (character.readUserChoice(options)) {
            case 1 -> train();
        }
        // check advancement
        if (character.getExperiencePoints() >= XP_TO_ADVANCE) {
            System.out.println("\n  ⬆  You have enough XP! Advancing to INTERMEDIATE level...");
            character.setState(new IntermediateState(character));
        } else {
            System.out.printf("  [XP needed to advance: %d / %d]%n",
                    character.getExperiencePoints(), XP_TO_ADVANCE);
        }
    }

    @Override
    public void train() {
        System.out.println("\n  You train diligently...");
        character.addExperience(10);
    }
}
