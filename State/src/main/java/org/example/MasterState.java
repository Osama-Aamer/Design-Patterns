package org.example;


//MASTER level — terminal state.
//The game ends as soon as this state's action() is called.
public class MasterState extends CharacterState {

    public MasterState(GameCharacter character) {
        super(character);
        System.out.println("  ★ You have reached the pinnacle — MASTER level!");
    }

    @Override
    public String getLevelName() {
        return "Master";
    }

    @Override
    public void action() {
        // Signal the game loop to stop
        character.setGameOver();
    }

    @Override
    public void train() {
        System.out.println("  A Master needs no further training.");
    }
}
