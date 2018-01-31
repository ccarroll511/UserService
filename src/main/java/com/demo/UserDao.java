package com.demo;

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException;  
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.ObjectInputStream; 
import java.io.ObjectOutputStream; 
import java.util.ArrayList; 
import java.util.List;  

public class UserDao { 
	
	public static int nextId = 1;
	
	public List<User> getAllUsers(){ 

		List<User> userList = null; 
		try { 
			File file = new File("Users.dat"); 
			if (!file.exists()) { 
				User user = new User(nextId++, "John", "Teacher"); 
				userList = new ArrayList<User>(); 
				userList.add(user); 
				saveUserList(userList);
			} 
			else{ 
				FileInputStream fis = new FileInputStream(file); 
				ObjectInputStream ois = new ObjectInputStream(fis); 
				userList = (List<User>) ois.readObject();
				ois.close(); 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}  
		return userList; 
	} 
	
	public void addUser(User newUser) {
		List<User> userList = getAllUsers();
		newUser.setId(nextId++);
		userList.add(newUser);
		saveUserList(userList);
	}
	
	public int updateProfession(int id, String newProfession) {
		List<User> userList = getAllUsers();
		int userIndex = -1;
		
		int i = 0;
		for (i = 0; i < userList.size(); i++) {
			if (userList.get(i).getId() == id) {
				userIndex = i;
			}
		}
		
		if (userIndex == -1) {
			return 0;
		}
		
		User user = userList.get(userIndex);
		user.setProfession(newProfession);
		userList.set(userIndex, user);
		
		saveUserList(userList);
		
		return 1;
	}
	
	public User deleteUser(int id) {
		User deletedUser = null;
		List<User> userList = getAllUsers();
		
		int i = 0;
		while (i < userList.size()) {
			if (userList.get(i).getId() == id) {
				deletedUser = userList.remove(i);
				saveUserList(userList);
				return deletedUser;
			}
			
			i++;
		}
		
		return deletedUser;
	}
	
	private void saveUserList(List<User> userList){ 
		try { 
			File file = new File("Users.dat"); 
			FileOutputStream fos;  
			fos = new FileOutputStream(file); 
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			oos.writeObject(userList); 
			oos.close(); 
		} catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}    
}