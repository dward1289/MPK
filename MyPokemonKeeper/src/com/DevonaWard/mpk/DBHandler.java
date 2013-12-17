package com.DevonaWard.mpk;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "pokemonManager";
 
    // Contacts table name
    private static final String TABLE_POKEMON = "pokemon";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TYPE = "type";
    private static final String KEY_DATE = "date";
    private static final String KEY_IMG = "image";
    private static final String KEY_LEVEL = "level";
 
    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POKEMON_TABLE = "CREATE TABLE " + TABLE_POKEMON + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_TYPE + " TEXT," + KEY_DATE + " TEXT," + KEY_LEVEL + " TEXT," + KEY_IMG + " TEXT" +")";
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
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new Pokemon
    void addPokemon(thePokemon thePokemon) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, thePokemon.getName()); 
        values.put(KEY_TYPE, thePokemon.getType());
        values.put(KEY_DATE, thePokemon.getDate());
        values.put(KEY_LEVEL, thePokemon.getLevel());
        values.put(KEY_IMG, thePokemon.getImage());
 
        // Inserting Row
        db.insert(TABLE_POKEMON, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single Pokemon
    thePokemon getPokemon(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_POKEMON, new String[] { KEY_ID,
                KEY_NAME, KEY_TYPE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        thePokemon pokemon = new thePokemon(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        // return contact
        return pokemon;
    }
     
    // Getting All Pokemon
    public List<thePokemon> getAllPokemon() {
        List<thePokemon> pokemonList = new ArrayList<thePokemon>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_POKEMON;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                thePokemon pokemon = new thePokemon();
                pokemon.setID(Integer.parseInt(cursor.getString(0)));
                pokemon.setName(cursor.getString(1));
                pokemon.setType(cursor.getString(2));
                pokemon.setDate(cursor.getString(3));
                pokemon.setLevel(cursor.getString(4));
                pokemon.setImage(cursor.getString(5));
                // Adding contact to list
                pokemonList.add(pokemon);
            } while (cursor.moveToNext());
        }
 
        // return Pokemon list
        return pokemonList;
    }
 
    // Updating single Pokemon
    public int updatePokemon(thePokemon pokemon) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, pokemon.getName());
        values.put(KEY_TYPE, pokemon.getType());
        values.put(KEY_DATE, pokemon.getDate());
        values.put(KEY_LEVEL, pokemon.getLevel());
        values.put(KEY_IMG, pokemon.getImage());
 
        // updating row
        return db.update(TABLE_POKEMON, values, KEY_ID + " = ?",
                new String[] { String.valueOf(pokemon.getID()) });
    }
 
    // Deleting single Pokemon
    public void deletePokemon(int po) {
    	SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_POKEMON, KEY_NAME + "=" + po, null);
        int rows = db.delete(TABLE_POKEMON, KEY_NAME + "=" + po, null);
        Log.i("ROWS", rows+"");
        db.close();
    }
 
 
    // Getting Pokemon Count
    public int getPokemonCount() {
        String countQuery = "SELECT  * FROM " + TABLE_POKEMON;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
}
