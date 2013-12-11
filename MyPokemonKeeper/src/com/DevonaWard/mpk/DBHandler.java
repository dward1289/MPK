package com.DevonaWard.mpk;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "pokemonManager";
 
    // Contacts table name
    private static final String TABLE_POKEMON = "pokemon";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LEVEL = "level";
 
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POKEMON_TABLE = "CREATE TABLE " + TABLE_POKEMON + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_LEVEL + " TEXT" + ")";
        db.execSQL(CREATE_POKEMON_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POKEMON);
 
        // Create tables again
        onCreate(db);
    }
    
 // Adding new pokemon
    public void addPokemon(thePokemon pokemon) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, pokemon.getName()); // Pokemon Name
        values.put(KEY_LEVEL, pokemon.getLevel()); // Level Number
     
        // Inserting Row
        db.insert(TABLE_POKEMON, null, values);
        db.close(); // Closing database connection
    }
     
    // Getting single pokemon
    public thePokemon getPokemon(int id) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	 
        Cursor cursor = db.query(TABLE_POKEMON, new String[] { KEY_ID,
                KEY_NAME, KEY_LEVEL }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
     
        thePokemon pokemon = new thePokemon(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), id);
        // return pokemon
        return pokemon;
    }
     
    // Getting All Pokemon
    public List<thePokemon> getAllPokemon() {
    	  List<thePokemon> pokeList = new ArrayList<thePokemon>();
    	    // Select All Query
    	    String selectQuery = "SELECT  * FROM " + TABLE_POKEMON;
    	 
    	    SQLiteDatabase db = this.getWritableDatabase();
    	    Cursor cursor = db.rawQuery(selectQuery, null);
    	 
    	    // looping through all rows and adding to list
    	    if (cursor.moveToFirst()) {
    	        do {
    	            thePokemon poke = new thePokemon();
    	            poke.setID(Integer.parseInt(cursor.getString(0)));
    	            poke.setName(cursor.getString(1));
    	            poke.setType(cursor.getString(2));
    	            // Adding contact to list
    	            pokeList.add(poke);
    	        } while (cursor.moveToNext());
    	    }
    	 
    	    // return pokelist
    	    return pokeList;
    }
     
    // Getting contacts Count
    public int getPokemonCount() {
    	String countQuery = "SELECT  * FROM " + TABLE_POKEMON;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
    // Updating single contact
    public int updatePokemon(thePokemon pokemon) {
    	SQLiteDatabase db = this.getWritableDatabase();
 
    ContentValues values = new ContentValues();
    values.put(KEY_NAME, pokemon.getName());
    values.put(KEY_LEVEL, pokemon.getLevel());
 
    // updating row
    return db.update(TABLE_POKEMON, values, KEY_ID + " = ?",
            new String[] { String.valueOf(pokemon.getID()) });
}
     
    // Deleting single contact
    public void deleteContact(thePokemon pokemon) {
    	SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_POKEMON, KEY_ID + " = ?",
            new String[] { String.valueOf(pokemon.getID()) });
    db.close();}


}
