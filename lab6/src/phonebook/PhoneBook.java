package phonebook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

public class PhoneBook implements Serializable{
	private Map<String,LinkedList<String>> phoneBook;				
	private int size;
	
	public PhoneBook() {
	
		phoneBook = new HashMap<String,LinkedList<String>>();
		size = 0;
	}
	
	/** 
	 * Associates the specified number with the specified 
	 * name in this phone book. 
	 * post: If the specified name is not present in this phone book,
	 *        the specified name is added and associated  with
	 *  	  the specified number. Otherwise the specified 
	 *  	  number is added to the set of number associated with name.
	 * @param name The name for which a phone number is to be added
	 * @param number The number associated with the specified name
	 * @return true if the specified name and number was inserted
	 */
	public boolean put(String name, String number) {
		if(phoneBook.containsKey(name)){							
			LinkedList <String> list = new LinkedList <String>();	
			list = phoneBook.get(name);								
			
			if(list.contains(number)){								
				return false;										
			} else{	
				list.add(number);									
			}
			
		} else {													
			LinkedList <String> list = new LinkedList <String>();	
			list.add(number);											
			phoneBook.put(name, list);								
			size++;													
		}
		return true;	
	}
	
	
	/**
	 * Removes the the specified name from this phone book.
	 * post: If the specified name is present in this phone book,
	 * 		 it is removed. Otherwise this phone book is
	 * 		 unchanged.
	 * @param name The name to be removed
	 * @return true if the specified name was present
	 */
	public boolean remove(String name) {
		LinkedList<String> list = phoneBook.get(name);				
		
		if(phoneBook.containsKey(name)){							
			list.clear();											
			phoneBook.remove(name);									
			size--;
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the specified number from name in this phone book.
	 * post: If the specified name and number is present in this phone book,
	 * 		 the number is removed. Otherwise this phone book is
	 * 		 unchanged.
	 * @param number The number to be removed
	 * @param name The name from which to remove the number
	 * @return true if the specified name and number was present
	 */
	public boolean removeNumber(String name, String number) {
		LinkedList<String> list = phoneBook.get(name);				
		if(phoneBook.containsKey(name)){							
			if(list.contains(number)){								
				list.remove(number);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Retrieves a set of phone numbers for the specified name. If the 
	 * specified name is not present in this phone book an empty set is 
	 * returned.
	 * @param name The name whose associated phone numbers are to be returned  
	 * @return The phone numbers associated with the specified name
	 */
	public LinkedList<String> findNumber(String name) {
	
		if(phoneBook.containsKey(name)){							
			return phoneBook.get(name);								
		}
		return null;
	}
	
	/**
	 * Retrieves a set of names associated with the specified phone number. 
	 * If the specified number is not present in this phone book an empty 
	 * set is returned.
	 * @param number The number for which the set of associated
	 * names is to be returned.
	 * @return The names associated with the specified number
	 */
	public LinkedList<String> findNames(String number) {
		
		LinkedList<String> list = new LinkedList<String>();			 
		Set<String> names = phoneBook.keySet();						
		Iterator<String> itr = names.iterator();					
		while (itr.hasNext()) {							
			String Name = itr.next();								
			List<String> numbers = findNumber(Name);				
			
			if (numbers != null && numbers.contains(number)) {		
				list.add(Name);										
			}
		}
		return list;												
	}
		
	
	
	/**
	 * Retrieves the set of all names present in this phone book.
	 * The set's iterator will return the names in ascending order
	 * @return The set of all names present in this phone book
	 */
	public Set<String> names() {
		return phoneBook.keySet();									
	}
	
	/**
	 * Returns true if this phone book is empty.
	 * @return true if this phone book is empty
	 */	
	public boolean isEmpty() {
		if(size == 0){
			return true;
		}
		return false ;
	}
	
	/**
	 * Returns the number of names in this phone book.
	 * @return The number of names in this phone book
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns a string containing all names and numbers (one entry per row) in the the phonebook.
	 * @return A string containing all names and numbers (one entry per row) in the the phonebook
	 */
	@Override
	public
	String toString(){
		
		StringBuilder sb = new StringBuilder();						
		Set<String> names = phoneBook.keySet();						
		Iterator<String> itr = names.iterator();					
		while (itr.hasNext()){
			String name = itr.next();
			LinkedList<String> list = phoneBook.get(name);			
			sb.append(name + "\t" );								
			
			Iterator<String> itr2 = list.iterator();				
			while(itr2.hasNext()){
				String number = itr2.next();						
				if(itr2.hasNext() == false){						
					sb.append(number);
				}else{
					sb.append(number + "\t");						
				}
		
			} 	
			sb.append("\n");										
		}
		return sb.toString();										
	
	}
	
	public void save(File fileName){
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(phoneBook);
		} 
		catch (Exception e) {
	
		
		}
	}
	
	public void open(File file){
		try {
			ObjectInputStream in =new ObjectInputStream(new FileInputStream(file));
		    phoneBook = (Map<String,LinkedList<String>>) in.readObject();

		} catch (Exception e) {
		
		}
		
	}
	}


