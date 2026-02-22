package org.example;


public class ExpertState extends CharacterState {
    private static final int XP_TO_ADVANCE = 160;

    public ExpertState(GameCharacter character) {
        super(character);
        System.out.println("  ★ You are now an EXPERT. You can now fight enemies for bonus XP!");
    }

    @Override
    public String getLevelName() {
        return "Expert";
    }

    @Override
    public void action() {
        String[] options = {"Train (+15 XP)", "Meditate (+20 HP)", "Fight (-20 HP / +25 XP)"};
        switch (character.readUserChoice(options)) {
            case 1 -> train();
            case 2 -> meditate();
            case 3 -> fight();
        }
        // check advancement
        if (character.getExperiencePoints() >= XP_TO_ADVANCE) {
            System.out.println("\n  ⬆  You have enough XP! Advancing to MASTER level...");
            character.setState(new MasterState(character));
        } else {
            System.out.printf("  [XP needed to advance: %d / %d]%n",
                    character.getExperiencePoints(), XP_TO_ADVANCE);
        }
    }

    @Override
    public void train() {
        System.out.println("\n  You engage in expert-level training drills...");
        character.addExperience(15);
    }

    @Override
    public void meditate() {
        System.out.println("\n  You channel your energy through deep meditation...");
        character.addHealth(20);
    }

    @Override
    public void fight() {
        if (character.getHealthPoints() <= 20) {
            System.out.println("\n  [!] You are too weak to fight! Meditate first to restore HP.");
            return;
        }
        System.out.println("\n  ⚔  You charge into battle against a fierce enemy!");
        character.reduceHealth(20);
        character.addExperience(25);
        System.out.println("  You emerge victorious!");
    }
}
