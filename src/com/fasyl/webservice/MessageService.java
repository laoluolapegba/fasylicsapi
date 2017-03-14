package com.fasyl.webservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasyl.entity.Message;
import com.fasyl.entity.MessageWrapper;
import com.fasyl.model.Resource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Path("/messageservice")
public class MessageService {
	@Inject
	private EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("status")
	public Response getStatus(){
		return Response.ok("{\"status\":\"The service is running...\"}").build();
		
	}
	/**
     * Accepts forward Messages from Infobip SMS gateway according to
     * specification 
     * 
     * @param messages
     * @return Response
     * @throws java.lang.Exception
     */
    @POST
    @Path("forwardmessage")
    @Produces(MediaType.APPLICATION_JSON)
   
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveRequests(String payload) throws Exception{        
    	String returnCode = "200";
    	//System.out.println("Price per message is :"+ payload.getMessageCount());
    	
    	//System.out.println("payload - " + payload);

		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		//List<Message> messages = new ArrayList<Message>();
		//messages = Arrays.asList(gson.fromJson(payload, Message[].class));
		
		MessageWrapper msgwapper = gson.fromJson(payload, MessageWrapper.class);
		List<Message> messages = msgwapper.getResults();
		//gsonBuilder.setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		
		//Instruct GSON to parse as a Post array (which we convert into a list)
		
		
		em = Resource.getEntityManager();
		try {
			em.getTransaction().begin();
			
			for(Message m:messages){
				em.persist(m);
				em.flush();
				em.refresh(m);
            } 
			
			em.getTransaction().commit();
			em.close();
			
			returnCode = "Accepted " + msgwapper.getMessageCount() +  " messages(s)" ;

		} catch (Exception err) {
			err.printStackTrace();
			returnCode = "{\"status\":\"500\","+
					"\"message\":\"Resource not created.\""+
					"\"developerMessage\":\""+err.getMessage()+"\""+
					"}";
			return  Response.status(500).entity(returnCode).build(); 

		}
		
		return  Response.status(201).entity(returnCode).build(); 
    	
               
    }
    @POST
    @Path("sendmessage")
    @Produces(MediaType.APPLICATION_JSON)   
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendSMS(String payload) throws Exception{        
    	String returnCode = "200";
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
		
		MessageWrapper msgwapper = gson.fromJson(payload, MessageWrapper.class);
		List<Message> messages = msgwapper.getResults();
		
		em = Resource.getEntityManager();
		try {
			em.getTransaction().begin();
			
			for(Message m:messages){
				em.persist(m);
				em.flush();
				em.refresh(m);
            } 
			
			em.getTransaction().commit();
			em.close();
			
			returnCode = "Accepted " + msgwapper.getMessageCount() +  " messages(s)" ;

		} catch (Exception err) {
			err.printStackTrace();
			returnCode = "{\"status\":\"500\","+
					"\"message\":\"Resource not created.\""+
					"\"developerMessage\":\""+err.getMessage()+"\""+
					"}";
			return  Response.status(500).entity(returnCode).build(); 

		}
		
		return  Response.status(201).entity(returnCode).build(); 
    	
               
    }
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("message/{id}")
	public Response getMessage(@PathParam("code") String entrycode){
		String response = null;
		try {
			em = Resource.getEntityManager();
			Message fmessage = em.find(Message.class, entrycode);
			if(null == fmessage){
				response = "{\"status\":\":401\"," 
						+ "\"message\":\"No content found \""
						+ "\"developerMessage\":\"Book - " + entrycode + " Not Found";
				return Response.status(401).entity(response).build();
			}
			em.close();
			response = toJSONString(fmessage);
			
		}
		catch(Exception ex)
		{
			response = "{\"status\":\"error...\"}";
			return Response.status(401).entity(response).build();
		}
		
		return Response.ok("{\"status\":\"The service is running...\"}").build();
		
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("messages")
	public Response getMessages(){
		String response = null;
		try {
			em = Resource.getEntityManager();
			Query query = em.createQuery("FROM com.fasyl.entity.Message");
			List<Message> messages = query.getResultList();
			em.close();
			response = toJSONString(messages);
			
		}
		catch(Exception err)
		{
			response = "{\"status\":\"401\","
					+ "\"message\":\"No content found \""
					+ "\"developerMessage\":\"" + err.getMessage() + "\"" + "}";
			return Response.status(401).entity(response).build();
		}
		return Response.ok(response).build();
		
	}
	public String toJSONString(Object object){
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'"); //ISO 8601 UTC
		
		Gson gson = gsonBuilder.create();
		return gson.toJson(object);
	}
}
