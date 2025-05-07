package com.Chris.SongProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		ArrayList<Song> songs = new ArrayList<>();
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the following for any of your favorite songs");
		System.out.println("Enter the title of the song");
		System.out.println("Enter the name of the Artist(s)");
		System.out.println("Enter the name of the Album");
		System.out.println("Enter the date the song was released (YYYY-MM-DD)");
		
		Song s1 = new Song(scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine());
		
		System.out.println();
        
        Song s2 = new Song(scan.nextLine(), scan.nextLine(), scan.nextLine(), scan.nextLine());
		
		songs.add(s1);
		songs.add(s2);
		
		System.out.println("Your Songs:");
        for (Song song : songs) {
            System.out.println(song);
        }
		
        Collections.sort(songs, new ChronoComparator());
        
        System.out.println("SONGS IN CHRONOLOGICAL ORDER");
        for (Song song : songs) {
            System.out.println(song);
        }
        
		
        scan.close();
    }
}
