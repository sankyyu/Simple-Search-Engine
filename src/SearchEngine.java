import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * It can store all words in a trie
 * And get the frequency of certain word¡¡for each file
 *
 */
public class SearchEngine {   
	private Trie root;
	class Result{   // This class is going to store the final result and convenient to print.
		String target;
		Map<File,Integer> list;
		Result(String target,Map<File,Integer> list){
			this.target=target;
			this.list=list;
		}
	}
	
	SearchEngine(){  //Class Constructor
		root=new Trie();
		
	}
	
	public void initialization(String path){
		File folder=new File(path);
		File[] files=folder.listFiles();// get into the direction 
		String encoding="UTF-8";   // set the encoding type
		System.out.println("-----------------Here are documents--------------------");
		for(File f: files){
			System.out.println(f.getName());
			try{
				InputStreamReader read=new InputStreamReader(
					new FileInputStream(f),encoding);
				BufferedReader bufferedreader=new BufferedReader(read);
				String line =null;
				while((line=bufferedreader.readLine())!=null){
					addWord(line,f,root);  //put the content into the trie line by line
				}
			}catch(Exception e){
				System.out.println("Unable to read the file. "+e);
			}
		}
		System.out.println("--------------------------------------------------------");
		
		
	}
	/**
	 * This is the critical method of this search engine.
	 * @param word: the string we haven't put into trie
	 * @param trie
	 */
	public void addWord(String word,File f,Trie trie){
		if(word.length()==0){
			trie.words++;
			addtoList(trie,f);  // if we reached the last char, we finish the process and add the file to the occurrence list
		}else{
			char c=word.charAt(0); //get the first char 
			c=Character.toLowerCase(c);// make the char lowercase
			if(c>='a'&&c<='z'){     //if we meet a symbol or space, we skip it and go to next word.
				if(!trie.kid.containsKey(c))   //put the char to the trie
					trie.kid.put(c, new Trie());
				addWord(word.substring(1),f,trie.kid.get(c));
			}else if(trie!=root){    //if we meet a symbol and we are at the root of the trie, it won't be counted as a word.
				trie.words++;
				addtoList(trie,f);
				addWord(word.substring(1),f,root);
			}else{
				addWord(word.substring(1),f,root);  
			}
		}
	}
	
	public void addtoList(Trie trie,File f){   //this method is used to add the file into the occurrence list.
		if(trie.occurrenceList==null){
			trie.occurrenceList=new HashMap();
			trie.occurrenceList.put(f, 1);
		}else if(trie.occurrenceList.containsKey(f)){
			trie.occurrenceList.put(f,trie.occurrenceList.get(f)+1);
		}else{
			trie.occurrenceList.put(f, 1);
		}
	}
	/**
	 *Because we want to get the max match word when we cannot find the key word.
	 *So we have variable temp to store the word we have ever met.
	 * @param word
	 * @return
	 */
	public Result getMaxMatchWord(String word){
		String tar="";
		String temptar="";  //Store the result about temp.
		//Map<File,Integer> list=null;
		Map<File,Integer> templist=null;
		
		
		char[] wa=word.toCharArray();
		Trie trie=root;
		
		for(int i=0;i<wa.length;i++){
			char c=wa[i];
			c=Character.toLowerCase(c);
			if(!trie.kid.containsKey(c)){
				if(trie.words!=0) // If the words is equal to 0, it is not a leaf. In other words, it is not a word.
					return new Result(tar,trie.occurrenceList);
				else
					return null;	
			}else{
				if(trie.words!=0){
					temptar=tar;
					templist=trie.occurrenceList;
				}
				tar+=c; 
				trie=trie.kid.get(c);
			}
		}
		if(trie.words==0)
			return new Result(temptar,templist);
		return new Result(tar,trie.occurrenceList);
		
	}
	
	public void printout(String word,Map<File,Integer> list){ // This method is to print the result out.
		
		System.out.println("The word \""+word+"\" is founded!");
		System.out.println("------------------------Here are results------------------------");
		List<Map.Entry<File,Integer>> res=new ArrayList<>();
		for(Map.Entry<File,Integer> entry:list.entrySet()){
			res.add(entry);
		}
		res.sort(new Comparator<Map.Entry<File, Integer>>(){//rank the result in descending order by frequency.
			public int compare(Map.Entry<File, Integer> o1, Map.Entry<File, Integer> o2) {
				return o2.getValue()-o1.getValue();} 
			});
		for(Map.Entry<File,Integer> entry: res){
			System.out.println("The file: "+entry.getKey().getName()+". Keyword appears "+entry.getValue()+" time(s).");
				
		}
	}
	
	
	public void start(String word){
		Result res=getMaxMatchWord(word);
		//System.out.println(res);
		if(res==null){
			System.out.println("Unable to find the keyword: "+word);
		}else{
			printout(res.target,res.list);
		}
	}
	
}
