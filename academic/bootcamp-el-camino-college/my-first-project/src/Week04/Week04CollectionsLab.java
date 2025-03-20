package Week04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Week04CollectionsLab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Did something!");
		System.out.println("---------------------------------");
		
		String firstName = "Sally";
		String lastName = "Smith";
		
		String tripleHi = multiplyString("Hi", 3);
		System.out.println(tripleHi);
		
		StringBuilder sBuilder = new StringBuilder("Rocket"); 
		sBuilder.append(" Raccoon");
		System.out.println(sBuilder);
		
		System.out.println(sBuilder.charAt(2));
		//System.out.println(sBuilder.deleteCharAt(2));
		//System.out.println(sBuilder.delete(1,3));
		System.out.println(sBuilder.indexOf("Ra"));
		System.out.println(sBuilder.replace(4, 9, "Guardian"));
		System.out.println(sBuilder.reverse());
		
		//-------------------------------------------------------------------------------------------
		//-------------------------------------------------------------------------------------------
		System.out.println("---------------------------------");
		// A LIST is an interface
		// Signature = List<E> = E is the generic of the type you need
		List<String> sports = new ArrayList<String>(); 
		sports.add("Soccer");
		sports.add("Tennis");
		sports.add("Basketball");
		sports.add(3, "Racing");
		sports.add(3, "Baseball");
		
		for(int i = 0; i < sports.size(); i++) {
			System.out.println(sports.get(i));
		}		
		System.out.println(" ");
		
		for (String sport : sports) {
			System.out.println(sport);
		}		
		System.out.println(sports.isEmpty());
		
		//-------------------------------------------------------------------------------------------
		//-------------------------------------------------------------------------------------------
		System.out.println("---------------------------------");
		
		/*
		 * LIST
		 * 
		 * Allows duplicates
		 * Keeps elements ordered by index
		 * Allows null values
		 * Common implementations: ArrayList, LinkedList, Vector
		 */
		
		List<String> students = new ArrayList<String>();
		students.add("John");
		students.add("Jane");
		students.add("John");
		students.add(null);
		for(String student : students) 
			System.out.println(student);
		
		System.out.println(students.get(1));
	
		/*
		 * SET 
		 * 
		 * No duplicates
		 * Can not guarantee order - Unordered
		 * Allows null only once
		 * Common implementations: HashSet, LinkedHashSet, TreeSet
		 */
		
		Set<String> states = new HashSet<String>();
		states.add("Alabama");
		states.add("Alabama");
		states.add("Alaska");
		states.add("California");
		for(String state : states) {
			System.out.println(state);
		}
		
		System.out.println(states.size());
		System.out.println(states.contains("Delware"));
		
		/*
		 * MAP
		 * 
		 * Map<K, V> K is key, V is value
		 * 
		 * Key value pairs - ie Dictionary
		 * Values can be duplicate, but not keys
		 * Common implementations: HashMap, LinkedHashMap, TreeMap, Hashtable 
		 */
		
		Map<Integer, String> racerPlacements = new HashMap<Integer, String>();
		racerPlacements.put(1, "Rockt Raccoon");
		racerPlacements.put(2, "Drax");
		racerPlacements.put(3, "Gamora");
		
		System.out.println(racerPlacements.get(1));
		System.out.println(racerPlacements.isEmpty());
		
		Set<Integer> racerKeys = racerPlacements.keySet(); // returns all racer keys
		for (Integer key : racerKeys) {
			System.out.println(key + " : " + racerPlacements.get(key));
		}
		
		Collection<String> racerNames = racerPlacements.values();
		for(String racer : racerNames) {
			System.out.println(racer);
		}
				
		Map<String, String> dictionary = new HashMap<String, String>();
		dictionary.put("augment", "make something greater by adding to it, increase.");
		dictionary.put("diminish", "Make of become less.");
		dictionary.put("ostentatious", "Characterized by vulgar or pretentious display.");		
	}
	
	public static String multiplyString(String str, int num) {
		StringBuilder sBuilder = new StringBuilder();
		for(int i = 0; i < num; i++) {
			sBuilder.append(str);
		}
		
		return sBuilder.toString();
		
	}

}
