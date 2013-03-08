package com.example.myfirstapp;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

   // All Static variables
   // Database Version
   private static final int DATABASE_VERSION = 1;

   // Database Name
   private static final String DATABASE_NAME = "exercises.db";

   // Contacts table name
   private static final String TABLE_EXERCISE = "exercises";

   // Contacts Table Columns names
   private static final String KEY_ID = "id";
   private static final String NUM_IN_PACKET = "num_in_packet";
   private static final String PACKET = "packet";
   private static final String TYPE = "type";
   private static final String NUM_ATTEMPTS = "num_attempts";
   private static final String NUM_CORRECT = "num_correct";
   private static final String FIRST_ATTEMPT = "first_attempt";
   private static final String MOST_RECENT_ATTEMPT = "most_recent_attempt";
   

   public DatabaseHandler(Context context) {
       super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }

   // Creating Tables
   @Override
   public void onCreate(SQLiteDatabase db) {
       String CREATE_EXERCISE_TABLE = "CREATE TABLE " + TABLE_EXERCISE + "("
               + KEY_ID + " INTEGER PRIMARY KEY," + NUM_IN_PACKET + " INTEGER,"
               + PACKET + " TEXT," + TYPE + " TEXT," + NUM_CORRECT + " INTEGER," + NUM_ATTEMPTS + " INTEGER," + 
               FIRST_ATTEMPT + " TEXT," + MOST_RECENT_ATTEMPT + " TEXT" + ")";
       db.execSQL(CREATE_EXERCISE_TABLE);
   }

   // Upgrading database
   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // Drop older table if existed
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);

       // Create tables again
       onCreate(db);
   }

   /**
    * All CRUD(Create, Read, Update, Delete) Operations
    */

   // Adding new contact
   void addExercise(Exercise ex) {
       SQLiteDatabase db = this.getWritableDatabase();

       ContentValues values = new ContentValues();
       values.put(PACKET, ex.packet); 
       values.put(NUM_IN_PACKET, ex.numInPacket);
       values.put(TYPE, ex.type);
       values.put(NUM_CORRECT, ex.numCorrect);
       values.put(NUM_ATTEMPTS, ex.numAttempts);
       values.put(FIRST_ATTEMPT, ex.firstAttempt);
       values.put(MOST_RECENT_ATTEMPT, ex.firstAttempt);
       

       // Inserting Row
       db.insert(TABLE_EXERCISE, null, values);
       db.close(); // Closing database connection
   }
  
   // Getting single contact
   Exercise getExercise(String packet, int numInPacket) {
       SQLiteDatabase db = this.getReadableDatabase();

       Cursor cursor = db.query(TABLE_EXERCISE, new String[] { KEY_ID,
               NUM_IN_PACKET, PACKET, TYPE, NUM_ATTEMPTS, NUM_CORRECT, FIRST_ATTEMPT, MOST_RECENT_ATTEMPT}, NUM_IN_PACKET + "=? AND " + PACKET + "=?",
               new String [] { String.valueOf(numInPacket), packet }, null, null, null, null);
       if (cursor != null)
           cursor.moveToFirst();

       Exercise ex = new Exercise(cursor.getString(2),
               cursor.getInt(1), cursor.getString(3),cursor.getInt(4),cursor.getInt(5),cursor.getString(6),cursor.getString(7));
       // return contact
       return ex;
   }
/*
   // Getting All Contacts
   public List<Contact> getAllContacts() {
       List<Contact> contactList = new ArrayList<Contact>();
       // Select All Query
       String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

       SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = db.rawQuery(selectQuery, null);

       // looping through all rows and adding to list
       if (cursor.moveToFirst()) {
           do {
               Contact contact = new Contact();
               contact.setID(Integer.parseInt(cursor.getString(0)));
               contact.setName(cursor.getString(1));
               contact.setPhoneNumber(cursor.getString(2));
               // Adding contact to list
               contactList.add(contact);
           } while (cursor.moveToNext());
       }

       // return contact list
       return contactList;
   }
*/
   // Updating single contact
   void updateExercise(String packet, int numInPacket, boolean correct) {
       SQLiteDatabase db = this.getWritableDatabase();
       Exercise grab = new Exercise();
       grab = this.getExercise(packet, numInPacket);
       int numAttempts = grab.numAttempts;
       int numCorrect = grab.numCorrect;
       String mostRecent = grab.mostRecentAttempt;
       String firstAttempt = grab.firstAttempt;
       
       Date today = new Date();
       Timestamp currentTime = new Timestamp(today.getTime());
      
       mostRecent = currentTime.toString();
       numAttempts++;
       if(correct){
    	   numCorrect++;
       }
       
       if(firstAttempt.equals("null")){
    	   firstAttempt = mostRecent;
       }
       
       ContentValues values = new ContentValues();
       values.put(NUM_ATTEMPTS, numAttempts);
       values.put(NUM_CORRECT, numCorrect);
       values.put(MOST_RECENT_ATTEMPT, mostRecent);
       values.put(FIRST_ATTEMPT, firstAttempt);

       // updating row
       db.update(TABLE_EXERCISE, values, PACKET + " = ? AND " + NUM_IN_PACKET + " = ?",
               new String[] { packet, String.valueOf(numInPacket) });
   }
   
/*
   // Deleting single contact
   public void deleteContact(Contact contact) {
       SQLiteDatabase db = this.getWritableDatabase();
       db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
               new String[] { String.valueOf(contact.getID()) });
       db.close();
   }

   // Getting contacts Count
   public int getContactsCount() {
       String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cursor = db.rawQuery(countQuery, null);
       cursor.close();

       // return count
       return cursor.getCount();
   }*/

}