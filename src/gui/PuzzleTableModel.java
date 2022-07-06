package gui;

import boatpuzzle.BoatPuzzleEvent;
import boatpuzzle.BoatPuzzleListener;
import boatpuzzle.BoatPuzzleState;
import gamelogic.Individual;
import gamelogic.IndividualType;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class PuzzleTableModel extends AbstractTableModel implements BoatPuzzleListener {
    private static final Icon monsterIcon = new ImageIcon("./Monster.png");
    private static final Icon personIcon = new ImageIcon("./Person.png");
    private static final Icon waterIcon = new ImageIcon("./Water.png");

    private BoatPuzzleState puzzle;

    public PuzzleTableModel(BoatPuzzleState puzzle) {
        if(puzzle == null) throw new NullPointerException("Puzzle cannot be null");
        this.puzzle = puzzle;
        this.puzzle.AddListener(this);


    }

    @Override
    public int getRowCount() {
        return this.puzzle.GetTotalIndividuals();
    }

    @Override
    public int getColumnCount() { return 3; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 1) return waterIcon;
        Individual individual = this.puzzle.GetIdentityAt(rowIndex);
        int side = (individual.GetHasCrossed()) ? 2 : 0;

        if(side != columnIndex) return null;
        if(individual.GetType() == IndividualType.M) return monsterIcon;
        else if (individual.GetType() == IndividualType.P) return personIcon;

        return null;
    }

    @Override
    public void PuzzleChanged(BoatPuzzleEvent puzzleEvent) {
        fireTableDataChanged();
        try{
            Thread.sleep(1000);
        }catch(InterruptedException ignore){
        }
    }

    public void SetPuzzle(BoatPuzzleState puzzle) {
        if(puzzle == null) throw new NullPointerException("Puzzle cannot be null");

        this.puzzle.RemoveListener(this);
        this.puzzle = puzzle;

        puzzle.AddListener(this);
        fireTableDataChanged();
    }

}
