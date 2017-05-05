import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
	Map<Character,Trie> kid;
	int words;   //Store the occurrence times of the keyword for all files
	Map<File,Integer> occurrenceList; //A hshmap stores the occurrence times of the keyword for each file
	Trie(){
		kid=new HashMap<>();
		words=0;    
		occurrenceList=null; 
	}
}
