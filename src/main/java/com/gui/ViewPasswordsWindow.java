package main.java.com.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import main.java.com.app.Password;
import main.java.com.app.PasswordDAO;

public class ViewPasswordsWindow extends JFrame {
    private PasswordDAO passwordDAO;
    private String username;
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchBar;

    public ViewPasswordsWindow(PasswordDAO passwordDAO, String username) {
        this.passwordDAO = passwordDAO;
        this.username = username;
        initComponents();
        try {
            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Website URL", "Website Name", "Website Username", "Website Password", "Edit", "Delete"}, 0);
        table = new JTable(model);

        searchBar = new JTextField();
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            UserOptionsWindow userOptionsWindow = new UserOptionsWindow(passwordDAO, username);
            userOptionsWindow.setVisible(true);
            dispose();
        });

        JButton sortPasswordsButton = new JButton("Sort Passwords");
        sortPasswordsButton.addActionListener(e -> sortPasswords());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(new JLabel(), BorderLayout.NORTH);
        bottomPanel.add(new JLabel(), BorderLayout.EAST);
        bottomPanel.add(sortPasswordsButton, BorderLayout.CENTER);
        bottomPanel.add(backButton, BorderLayout.SOUTH);

        panel.add(searchBar, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
        setTitle("View Passwords");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    private void search() {
        String searchText = searchBar.getText();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
    
        if (searchText.length() > 0) {
            sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
                @Override
                public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                    String websiteName = entry.getStringValue(1); // Get the value from the "Website Name" column
                    return websiteName.toLowerCase().startsWith(searchText.toLowerCase());
                }
            });
        } else {
            sorter.setRowFilter(null);
        }
    }
    

    private void sortPasswords() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }

    private void populateTable() throws SQLException {
        List<Password> passwords = passwordDAO.getUserPasswords(username);
        for (Password password : passwords) {

            JButton editButton = new JButton("Edit");
            editButton.addActionListener(e -> {
                EditPasswordWindow editPasswordWindow = new EditPasswordWindow(passwordDAO, password, username);
                editPasswordWindow.setVisible(true);
                dispose();
            });

            JButton deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {
                int row = table.getSelectedRow();
                int modelRowIndex = table.convertRowIndexToModel(row);
                Password selectedPassword = passwords.get(modelRowIndex);
                try {
                    passwordDAO.deleteUserPassword(selectedPassword.getId());
                    model.removeRow(modelRowIndex);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            Object[] row = new Object[]{password.getWebsiteUrl(), password.getWebsiteName(),
                password.getWebsiteUsername(), password.getWebsitePassword(), editButton, deleteButton};

        model.addRow(row);
    }

    TableColumn editColumn = table.getColumnModel().getColumn(4);
    editColumn.setCellRenderer(new JButtonRenderer());
    editColumn.setCellEditor(new JButtonEditor(new JCheckBox()));

    TableColumn deleteColumn = table.getColumnModel().getColumn(5);
    deleteColumn.setCellRenderer(new JButtonRenderer());
    deleteColumn.setCellEditor(new JButtonEditor(new JCheckBox()));
}

class JButtonRenderer implements TableCellRenderer {
    private JButton button;

    public JButtonRenderer() {
        button = new JButton();
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof JButton) {
            button = (JButton) value;
        }
        return button;
    }
}

class JButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;

    public JButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        if (value instanceof JButton) {
            button = (JButton) value;
            label = button.getText();
        } else {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
        }
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            // Perform action when button is pushed
        }
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
}
