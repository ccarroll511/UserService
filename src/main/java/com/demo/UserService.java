package com.demo;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path; 
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/UserService") 
public class UserService {  
	UserDao userDao = new UserDao();  
	
	@GET 
	@Path("/users") 
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<User> getUsers(){ 
		return userDao.getAllUsers();
	}  
	
	@GET 
	@Path("/healthcheck") 
	@Produces(MediaType.TEXT_PLAIN) 
	public String healthCheck(){ 
		return "Service is running!";
	}  
	
	@POST
	@Path("/users")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String addUser(User newUser) {
		userDao.addUser(newUser);
		return "User: " + newUser.getName() + " was added!";
	}
	
	@PUT
	@Path("/users")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(@QueryParam("id") int id, @QueryParam("profession") String newProfession) {
		int status = userDao.updateProfession(id, newProfession);
		if (status == 0) {
			return "ID not found";
		}
		
		return "User has been updated!";
	}
	
	@DELETE
	@Path("/users")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(@QueryParam("id") int id) {
		User user = userDao.deleteUser(id);
		
		if (user != null) {
			return "User: " + user.getName() + " has been removed";
		}
		
		return "ID not found";
	}
	
}