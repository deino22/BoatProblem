package gui;

import agent.Solution;
import boatpuzzle.BoatPuzzleAgent;
import boatpuzzle.BoatPuzzleProblem;
import boatpuzzle.BoatPuzzleState;
import gamelogic.Individual;
import gamelogic.IndividualType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private static Individual[] initialIdentities = new Individual[] {
        new Individual(0, IndividualType.P),
        new Individual(1, IndividualType.P),
        new Individual(2, IndividualType.P),
        new Individual(3, IndividualType.M),
        new Individual(4, IndividualType.M),
        new Individual(4, IndividualType.M)
    };
    // Agent
    private BoatPuzzleAgent agent = new BoatPuzzleAgent(new BoatPuzzleState(initialIdentities));
    // Table
    private PuzzleTableModel puzzleTableModel;
    private JTable puzzleTable = new JTable();
    // Buttons
    private JButton btnSolve = new JButton("Solve");
    private JButton btnStop = new JButton("Stop");

    public MainFrame() {
        try {
            this.init();
        }catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void init() throws Exception {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Boat Puzzle");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel contentPanel = (JPanel) this.getContentPane();
        JPanel actionsPanel = new JPanel(new FlowLayout());

        actionsPanel.add(this.btnSolve);
        actionsPanel.add(this.btnStop);

        JPanel puzzlePanel = new JPanel();

        puzzlePanel.add(this.puzzleTable);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(puzzlePanel, BorderLayout.NORTH);
        mainPanel.add(actionsPanel, BorderLayout.SOUTH);

        contentPanel.add(mainPanel);

        // Button Actions
        btnStop.setEnabled(false);
        btnStop.addActionListener(new ButtonStop_ActionAdapter(this));

        btnSolve.setEnabled(true);
        btnSolve.addActionListener(new ButtonSolve_ActionAdapter(this));

        // Fill Table
        configureTable(puzzleTable);

        this.setSize(800, 600);
        this.setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);
        this.setVisible(true);

        pack();
    }

    private void configureTable(JTable table) {
        this.puzzleTableModel = new PuzzleTableModel(this.agent.GetEnvironment());
        puzzleTable.setModel(puzzleTableModel);

        table.setDefaultRenderer(Object.class, new PuzzleCellRenderer());

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(Properties.CELL_WIDTH);
        }
        table.setRowHeight(Properties.CELL_HEIGHT);
//        table.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    private void ShowSolution() {
        SwingWorker worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                agent.ExecuteSolution();
                return null;
            }
        };

        worker.execute();
    }

    public void SolveActionPerformed(ActionEvent e) {
        SwingWorker worker = new SwingWorker<Solution, Void>() {
            @Override
            protected Solution doInBackground() throws Exception {
                btnSolve.setEnabled(false);
                btnStop.setEnabled(true);
                // Update UI
                BoatPuzzleProblem problem = new BoatPuzzleProblem((BoatPuzzleState) agent.GetEnvironment().clone());

                agent.SolveProblem(problem);

                return null;
            }

            @Override
            public void done() {
                if(!agent.HasBeenStopped()) {
                    System.out.println("Finished Run");

                    if(agent.HasSolution()) {
                        ShowSolution();
                    }
                }
                btnSolve.setEnabled(true);
                btnStop.setEnabled(false);
            }

        };

        worker.execute();
    }

    public void StopActionPerformed(ActionEvent e) {
        this.agent.Stop();

        btnStop.setEnabled(false);
        btnSolve.setEnabled(true);
    }
}

class ButtonSolve_ActionAdapter implements ActionListener {
    private final MainFrame frame;

    ButtonSolve_ActionAdapter(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.SolveActionPerformed(e);
    }
}

class ButtonStop_ActionAdapter implements ActionListener {
    private final MainFrame frame;

    ButtonStop_ActionAdapter(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.StopActionPerformed(e);
    }
}



