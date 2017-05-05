import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		SearchEngine se=new SearchEngine();
		se.initialization("files/"); //Input the path
		System.out.println("Search Engine has initialized successfully!");
		
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the word you want to search for. If the word is not included, a similar word will be chosen.");
		String input=scanner.nextLine();
		se.start(input);
	}
}
