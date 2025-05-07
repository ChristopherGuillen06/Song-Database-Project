package com.Chris.SongProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	


	public static void main(String[] args) {

		MyList<Song> ml = new MyList<>();

		Song one = new Song("IFHY", "Tyler the Creator (Feat. Pharrell Williams)", "Wolf", "05-01-2020");
		Song two = new Song("Ghost Town", "Kanye West", "ye", "01-01-2020");
		Song three = new Song("Balloon", "Tyler the Creator (Feat. Doechii)", "Chromakopia", "12-31-2020");
		ml.add(one);
		ml.add(two);
		ml.add(three);

		System.out.println("Original List of Songs");
		ml.printList();
		System.out.println();

		System.out.println("Songs Sorted Alphabetically by Title");
		ml.sort(new AlphaComparator());
		ml.printList();
		System.out.println();

		System.out.println("Songs Sorted Chronologically by Release Date");
		ml.sort(new ChronoComparator());
		ml.printList();
		System.out.println();

		//loadFromDatabase(ml);

	}
}