package com.Chris.SongProject;

import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import javax.swing.*;

public class GUI extends JFrame {

    private JLabel instructionsL, titleLabel, artistLabel, albumLabel, drLabel;
    private JButton addButton, removeButton, sort1Button, sort2Button, saveButton, searchArtistButton, searchTitleButton; 
    private Container myCP; 
    private JTextField titleField, artistField, albumField, dateField;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private SongList sl = new SongList();

    public GUI() {
        super("Song Database");
        
        sl.loadFromDatabase();
        
        setSize(600, 700);
        setLocation(100, 100);
        myCP = getContentPane();
        myCP.setLayout(new FlowLayout());

        
        instructionsL = new JLabel("Welcome to Your Favorite Songs");
        instructionsL.setFont(new Font("Times", Font.PLAIN, 20));
        myCP.add(instructionsL);

        
        titleLabel = new JLabel("Title: ");
        artistLabel = new JLabel("Artist: ");
        albumLabel = new JLabel("Album: ");
        drLabel = new JLabel("Release Date (XX-XX-XXX): ");

        titleField = createTextField();
        artistField = createTextField();
        albumField = createTextField();
        dateField = createTextField();

        myCP.add(titleLabel);
        myCP.add(titleField);
        myCP.add(artistLabel);
        myCP.add(artistField);
        myCP.add(albumLabel);
        myCP.add(albumField);
        myCP.add(drLabel);
        myCP.add(dateField);

        
        addButton = new JButton("Add Song");
        createButton(addButton);
        addButton.addActionListener(new AddHandler());
        myCP.add(addButton);
        
        removeButton = new JButton("Remove Song");
        createButton(removeButton);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter song title to remove:");
                if (title == null || title.trim().isEmpty()) return;
                
                String artist = JOptionPane.showInputDialog("Enter artist name:");
                if (artist == null || artist.trim().isEmpty()) return;

                boolean removed = sl.remove(title.trim(), artist.trim());
                
                if (removed) {
                    JOptionPane.showMessageDialog(GUI.this, "Song removed successfully!");
                    refreshSongListDisplay();
                } else {
                    JOptionPane.showMessageDialog(GUI.this, "No matching song found.");
                }
            }
        });
        myCP.add(removeButton);

        sort1Button = new JButton("Sort by Title");
        createButton(sort1Button);
        sort1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sl.getList().sort(new Comparator<Song>() {
                    public int compare(Song a, Song b) {
                        return a.getTitle().compareToIgnoreCase(b.getTitle());
                    }
                });
                refreshSongListDisplay();
            }
        });
        myCP.add(sort1Button);
        
        sort2Button = new JButton("Sort by Date");
        createButton(sort2Button);
        sort2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sl.getList().sort(new Comparator<Song>() {
                    public int compare(Song a, Song b) {
                        return a.getDateReleased().compareTo(b.getDateReleased());
                    }
                });
                refreshSongListDisplay();
            }
        });
        myCP.add(sort2Button);
        
        saveButton = new JButton("Save to Database");
        createButton(saveButton);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sl.saveToDatabase(sl.getList());
                JOptionPane.showMessageDialog(GUI.this, "Songs saved to database.");
            }
        });
        myCP.add(saveButton);

        searchTitleButton = new JButton("Search by Title");
        createButton(searchTitleButton);
        searchTitleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTitle = JOptionPane.showInputDialog("Enter title to search:");
                if (searchTitle == null || searchTitle.trim().isEmpty()) return;
                
                StringBuilder result = new StringBuilder("Search Results:\n");
                for (Song s : sl.getList()) {
                    if (s.getTitle().toLowerCase().contains(searchTitle.toLowerCase())) {
                        result.append(s.toString());
                    }
                }
                textArea.setText(result.toString());
            }
        });
        myCP.add(searchTitleButton);

        searchArtistButton = new JButton("Search by Artist");
        createButton(searchArtistButton);
        searchArtistButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchArtist = JOptionPane.showInputDialog("Enter artist to search:");
                if (searchArtist == null || searchArtist.trim().isEmpty()) return;
                
                StringBuilder result = new StringBuilder("Search Results:\n");
                for (Song s : sl.getList()) {
                    if (s.getArtistName().toLowerCase().contains(searchArtist.toLowerCase())) {
                        result.append(s.toString());
                    }
                }
                textArea.setText(result.toString());
            }
        });
        myCP.add(searchArtistButton);

        
        textArea = new JTextArea(20, 40);
        scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        myCP.add(scrollPane);
        
        refreshSongListDisplay();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(20);
        textField.setPreferredSize(new Dimension(150, 30));
        return textField;
    }

    private void createButton(JButton button) {
        button.setPreferredSize(new Dimension(200, 40));
        button.setFont(new Font("Times", Font.PLAIN, 16));
    }

    private class AddHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText().trim();
            String artist = artistField.getText().trim();
            String album = albumField.getText().trim();
            String date = dateField.getText().trim();

            if (title.isEmpty() || artist.isEmpty() || album.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(GUI.this, "All fields must be filled");
                return;
            }

            Song newSong = new Song(title, artist, album, date);
            sl.add(newSong);
            refreshSongListDisplay();
            clearFields();
        }
    }

    private void refreshSongListDisplay() {
        StringBuilder sb = new StringBuilder();
        for (Song s : sl.getList()) {
            sb.append(s.toString());
        }
        textArea.setText(sb.toString());
    }

    private void clearFields() {
        titleField.setText("");
        artistField.setText("");
        albumField.setText("");
        dateField.setText("");
    }

    public static void main(String[] args) {
        new GUI();
    }
}