package com.example.myfirstapp;

import java.sql.Timestamp;

public class Exercise {
	 
    //private variables
    public int id;
    public String type;
    public String packet;
    public int numInPacket;
    public int numAttempts;
    public int numCorrect;
    public String firstAttempt;
    public String mostRecentAttempt;
    
    
 
    // Empty constructor
    public Exercise(){
 
    }

 
    // constructor
    public Exercise(String packet, int numInPacket, String type){
        this.packet = packet;
        this.type = type;
        this.numInPacket = numInPacket;
        this.numAttempts = 0;
        this.numCorrect = 0;
        this.firstAttempt = "null";
        this.mostRecentAttempt = "null";
        
    }
    
    //constructor
    
    public Exercise(String packet, int numInPacket, String type, int numAttempts, int numCorrect, String firstAttempt, String mostRecentAttempt){
        this.packet = packet;
        this.type = type;
        this.numInPacket = numInPacket;
        this.numAttempts = numAttempts;
        this.numCorrect = numCorrect;
        this.firstAttempt = firstAttempt;
        this.mostRecentAttempt = mostRecentAttempt;
    }

 

}