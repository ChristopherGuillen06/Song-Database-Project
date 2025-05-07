package com.Chris.SongProject;

public class Song implements Comparable<Song> {
    private String title;
    private String artistName;
    private String album;
    private String dateReleased;

    private static int numSongs = 1;
    private int songID;

    public Song(String title, String artistName, String album, String dateReleased) {
        this.title = title;
        this.artistName = artistName;
        this.album = album;
        this.dateReleased = dateReleased;
        this.songID = numSongs++;
    }

    public String getTitle() {
        return title;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbum() {
        return album;
    }

    public String getDateReleased() {
        return dateReleased;
    }

    public int getSongID() {
        return songID;
    }

    @Override
    public String toString() {
        return "Song #: " + songID + " | Title: " + title + " | Artist: " + artistName + 
               " | Album: " + album + " | Released: " + dateReleased + "\n";
    }

    @Override
    public int compareTo(Song other) {
        return this.title.compareToIgnoreCase(other.title);
    }
}