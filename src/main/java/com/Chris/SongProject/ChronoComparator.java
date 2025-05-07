package com.Chris.SongProject;

import java.util.Comparator;

public class ChronoComparator implements Comparator<Song> {
    @Override
    public int compare(Song a, Song b) {
        try {
        	
            int year1 = Integer.parseInt(a.getDateReleased());
            int year2 = Integer.parseInt(b.getDateReleased());
            return Integer.compare(year1, year2);
            
        } catch (NumberFormatException e) {
            return a.getDateReleased().compareTo(b.getDateReleased());
        }
    }
}