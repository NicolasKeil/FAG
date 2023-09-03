package fotoverwaltung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FileAttributeGUI extends JFrame implements ActionListener {
    private JTextField folderPathField;
    private JRadioButton readOnlyRadioButton;
    private JRadioButton readWriteRadioButton;
    private JButton applyButton;

    public FileAttributeGUI() {
        setTitle("File Attribute Setter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        JLabel folderPathLabel = new JLabel("Enter Folder Path:");
        folderPathField = new JTextField();
        readOnlyRadioButton = new JRadioButton("Set to Read-Only");
        readWriteRadioButton = new JRadioButton("Set to Read-Write");
        applyButton = new JButton("Apply");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(readOnlyRadioButton);
        buttonGroup.add(readWriteRadioButton);

        mainPanel.add(folderPathLabel);
        mainPanel.add(folderPathField);
        mainPanel.add(readOnlyRadioButton);
        mainPanel.add(readWriteRadioButton);
        mainPanel.add(applyButton);

        applyButton.addActionListener(this);

        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String folderPath = folderPathField.getText();
        boolean setToReadOnly = readOnlyRadioButton.isSelected();

        try {
            if (setToReadOnly) {
                Main.setImagesInFolderToReadOnly(folderPath);
                JOptionPane.showMessageDialog(this, "Files set to read-only.");
            } else {
                Main.setImagesInFolderToWritable(folderPath);
                JOptionPane.showMessageDialog(this, "Files set to read-write.");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error changing file attributes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileAttributeGUI());
    }
}
