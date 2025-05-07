package com.Chris.SongProject;

import java.util.Comparator;

public class AlphaComparator implements Comparator <Song> {

	@Override
	public int compare(Song s1, Song s2) {
        return s1.getTitle().compareToIgnoreCase(s2.getTitle());
    }

}
