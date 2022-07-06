package gui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class PuzzleCellRenderer extends JLabel implements TableCellRenderer {
    private static final Icon defaultIcon = new ImageIcon("./default.png");

    public PuzzleCellRenderer() {
        setBackground(null);
        setOpaque(false);
        setFont(new Font("Monospaced", Font.BOLD, 49));
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setIcon((Icon) value);
//        setIcon((Icon) defaultIcon);

        return this;
    }


}
