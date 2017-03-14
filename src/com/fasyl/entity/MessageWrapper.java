package com.fasyl.entity;

import java.util.List;

/**
*
* @author Laolu
*/
public class MessageWrapper {
   private List<Message> results;
   private int messageCount;
   private int pendingMessageCount;

   public List<Message> getResults() {
       return results;
   }

   public void setResults(List<Message> results) {
       this.results = results;
   }

   public int getMessageCount() {
       return messageCount;
   }

   public void setMessageCount(int messageCount) {
       this.messageCount = messageCount;
   }

   public int getPendingMessageCount() {
       return pendingMessageCount;
   }

   public void setPendingMessageCount(int pendingMessageCount) {
       this.pendingMessageCount = pendingMessageCount;
   }
}