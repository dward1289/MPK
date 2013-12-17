package com.DevonaWard.mpk;

//SQLITE database items
public class thePokemon{

	//private variables
    int _id;
    String _name;
    String _type;
    String _date;
    String _image;
    String _level;
     
    // Empty constructor
    public thePokemon(){
         
    }
    // constructor
    public thePokemon(int id, String name, String type,String date, String level, String image){
        this._id = id;
        this._name = name;
        this._type = type;
        this._date = date;
        this._level = level;
        this._image = image;
    }
     
    // constructor
    public thePokemon(String name, String type,String date, String level, String image){
        this._name = name;
        this._type = type;
        this._date = date;
        this._level = level;
        this._image = image;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting name
    public String getName(){
        return this._name;
    }
     
    // setting name
    public void setName(String name){
        this._name = name;
    }
     
    // getting type
    public String getType(){
        return this._type;
    }
     
    // setting type
    public void setType(String type){
        this._type = type;
    }
    
    // getting date
    public String getDate(){
        return this._date;
    }
     
    // setting date
    public void setDate(String date){
        this._date = date;
    }
    
    // getting level
    public String getLevel(){
        return this._level;
    }
     
    // setting level
    public void setLevel(String level){
        this._level = level;
    }
    
    // getting image
    public String getImage(){
        return this._image;
    }
     
    // setting image
    public void setImage(String image){
        this._image = image;
    }
}
