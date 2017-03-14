package com.fasyl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name="ics_in_messages")
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="messageid")
    //@JsonProperty
    private String messageId;
    //@JsonProperty
    @Column(name="msisdn")
    private String from;
    //@JsonProperty
    @Column(name="recipient")
    private String to;
    //@JsonProperty
    @Column(name="text")
    private String text;
    //@JsonProperty
    @Column(name="cleantext")
    private String cleanText;
    //@JsonProperty
    @Column(name="keyword")
    private String keyword;
    //@JsonProperty
    @Column(name="receivedat")
    private Date receivedAt;
    //@JsonProperty
    @Transient
    private int smsCount;
    //@JsonProperty
    @Column(name="callbackdata")
    private String callbackData;
    //@JsonProperty
    @Transient
    private Price price;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getMessageId() {
        return messageId;
    }   

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
    
        
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCleanText() {
        return cleanText;
    }

    public void setCleanText(String cleanText) {
        this.cleanText = cleanText;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Date receivedAt) {
        this.receivedAt = receivedAt;
    }

    public int getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(int smsCount) {
        this.smsCount = smsCount;
    }


    public String getCallbackData() {
        return callbackData;
    }

    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }
    
    public Message(){
    	
    }
}