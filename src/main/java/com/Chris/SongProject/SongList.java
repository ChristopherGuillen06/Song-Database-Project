package com.Chris.SongProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SongList {
    private ArrayList<Song> list = new ArrayList<>();
    private final String DB_URL = "jdbc:sqlite:src/main/resources/songs.db";

    public ArrayList<Song> getList() {
        return list;
    }

    public void add(Song s) {
        list.add(s);
        addToDatabase(s);
    }

    public boolean remove(String title, String artist) {
        Iterator<Song> iterator = list.iterator();
        while (iterator.hasNext()) {
            Song s = iterator.next();
            if (s.getTitle().equalsIgnoreCase(title) && 
                s.getArtistName().equalsIgnoreCase(artist)) {
                iterator.remove();
                removeFromDatabase(title, artist);
                return true;
            }
        }
        return false;
    }

    public void saveToDatabase(ArrayList<Song> songs) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DELETE FROM song");
            }
            
            
            String sql = "INSERT INTO song(title, artistName, album, dateReleased) VALUES(?,?,?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (Song s : songs) {
                    pstmt.setString(1, s.getTitle());
                    pstmt.setString(2, s.getArtistName());
                    pstmt.setString(3, s.getAlbum());
                    pstmt.setString(4, s.getDateReleased());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving to database: " + e.getMessage());
        }
    }

    public void loadFromDatabase() {
        list.clear();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM song")) {
            
            while (rs.next()) {
                Song song = new Song(
                    rs.getString("title"),
                    rs.getString("artistName"),
                    rs.getString("album"),
                    rs.getString("dateReleased")
                );
                list.add(song);
            }
        } catch (SQLException e) {
            System.err.println("Error loading database: " + e.getMessage());
            
        }
    }

   

    private void addToDatabase(Song s) {
        String sql = "INSERT INTO song(title, artistName, album, dateReleased) VALUES(?,?,?,?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, s.getTitle());
            pstmt.setString(2, s.getArtistName());
            pstmt.setString(3, s.getAlbum());
            pstmt.setString(4, s.getDateReleased());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding to database: " + e.getMessage());
        }
    }

    private void removeFromDatabase(String title, String artist) {
        String sql = "DELETE FROM song WHERE title = ? AND artistName = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, artist);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error removing from database: " + e.getMessage());
        }
    }
}