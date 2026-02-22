package org.example;


//INTERMEDIATE level

public class IntermediateState extends CharacterState {
    private static final int XP_TO_ADVANCE = 80;

    public IntermediateState(GameCharacter character) {
        super(character);
        System.out.println("  ★ You are now INTERMEDIATE. You can now meditate to restore HP!");
    }

    @Override
    public String getLevelName() {
        return "Intermediate";
    }

    @Override
    public void action() {
        String[] options = {"Train (+10 XP)", "Meditate (+15 HP)"};
        switch (character.readUserChoice(options)) {
            case 1 -> train();
            case 2 -> meditate();
        }
        // check advancement
        if (character.getExperiencePoints() >= XP_TO_ADVANCE) {
            System.out.println("\n  ⬆  You have enough XP! Advancing to EXPERT level...");
            character.setState(new ExpertState(character));
        } else {
            System.out.printf("  [XP needed to advance: %d / %d]%n",
                    character.getExperiencePoints(), XP_TO_ADVANCE);
        }
    }

    @Override
    public void train() {
        System.out.println("\n  You push through an intense training session...");
        character.addExperience(12);
    }

    @Override
    public void meditate() {
        System.out.println("\n  You sit in quiet meditation and feel refreshed...");
        character.addHealth(15);
    }
}
