import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TodoApp extends JFrame {
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton;
    private JButton deleteButton;

    public TodoApp() {
        setTitle("To-Do List");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center window

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(taskList);

        taskField = new JTextField();
        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Task");

        // Panel for input field and add button
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(taskField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Main layout
        setLayout(new BorderLayout(10, 10));
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(deleteButton, BorderLayout.SOUTH);

        // Add task logic
        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                listModel.addElement(task);
                taskField.setText("");
            }
        });

        // Delete task logic
        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                listModel.remove(selectedIndex);
            }
        });

        // Optional: Press Enter to add
        taskField.addActionListener(e -> addButton.doClick());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TodoApp().setVisible(true);
        });
    }
}
