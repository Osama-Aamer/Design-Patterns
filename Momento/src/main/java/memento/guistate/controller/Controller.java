package memento.guistate.controller;

import memento.guistate.model.IMemento;
import memento.guistate.model.Model;
import memento.guistate.view.Gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {

    private final Model model;
    private final Gui gui;

    private final List<IMemento> undoList;

    private final List<IMemento> redoList;

    public Controller(Gui gui) {
        this.model = new Model();
        this.gui = gui;
        this.undoList = new ArrayList<>();
        this.redoList = new ArrayList<>();
    }

    // --- State accessors ---

    public void setOption(int optionNumber, int choice) {
        saveToHistory();
        model.setOption(optionNumber, choice);
    }

    public int getOption(int optionNumber) {
        return model.getOption(optionNumber);
    }

    public void setIsSelected(boolean isSelected) {
        saveToHistory();
        model.setIsSelected(isSelected);
    }

    public boolean getIsSelected() {
        return model.getIsSelected();
    }

    public void undo() {
        if (!undoList.isEmpty()) {
            System.out.println("Undo triggered");
            redoList.add(model.createMemento());
            IMemento previousState = undoList.remove(undoList.size() - 1);
            model.restoreState(previousState);
            gui.updateGui();
        } else {
            System.out.println("Nothing to undo");
        }
    }

    public void redo() {
        if (!redoList.isEmpty()) {
            System.out.println("Redo triggered");
            undoList.add(model.createMemento());
            IMemento redoState = redoList.remove(redoList.size() - 1);
            model.restoreState(redoState);
            gui.updateGui();
        } else {
            System.out.println("Nothing to redo");
        }
    }

    public void restoreFromHistory(int index) {
        if (index >= 0 && index < undoList.size()) {
            model.restoreState(undoList.get(index));
            gui.updateGui();
        }
    }

    public List<IMemento> getHistory() {
        return Collections.unmodifiableList(undoList);
    }


   private void saveToHistory() {
        undoList.add(model.createMemento());
        redoList.clear();
        System.out.println("State saved. Undo size=" + undoList.size() + "  Redo cleared.");
    }
}
