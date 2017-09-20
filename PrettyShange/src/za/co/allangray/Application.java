package za.co.allangray;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Application {

	static Map<String, User> sortedUsers = new TreeMap<String, User>();

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("C:\\Java\\tweet.txt");
		File names = new File("C:\\Java\\user.txt");

		processNames(names);

		processFileLines(file, new LineProcessor() {
			@Override
			public void process(String lineContents) {

				String username = lineContents.split(">")[0];
				String msg = lineContents.split(">")[1];

				for (String user : sortedUsers.keySet()) {
					if (user.equals(username) || sortedUsers.get(user).getFollows().contains(sortedUsers.get(username))) {
						sortedUsers.get(user).getMessages().add("	@"+username+": "+msg);
					}
				}
			}

		});

		formatTweets();

	}

	static void processFileLines(File file, LineProcessor lineProcessor) throws FileNotFoundException {
		try (Scanner s = new Scanner(file).useDelimiter("\r\n")) {

			while (s.hasNext()) {
				lineProcessor.process(s.next());

			}

		}
	}

	static interface LineProcessor {

		void process(String lineContents);

	}

	public static void processNames(File file) throws FileNotFoundException {

		Scanner s = new Scanner(file).useDelimiter("\r\n");
		User user = null;
		User fol = null;
		Set<User> follows = new TreeSet<User>();
		while (s.hasNext()) {
			String[] tokens = s.next().split("follows");

			user = new User();
			user.setUser_name(tokens[0].trim());
			for (String followee : tokens[1].split(",")) {
				fol = new User();
				fol.setUser_name(followee.trim());
				follows.add(fol);
				sortedUsers.put(fol.getUser_name(), fol);
			}
			user.setFollows(follows);
			sortedUsers.put(user.getUser_name(), user);
		}

	}


	public static void formatTweets() {


		for (String user : sortedUsers.keySet()) {
			System.out.println(user);
			for (String message : sortedUsers.get(user).getMessages()) {
				System.out.println( message);
			}

		}

	}

}
