package com.DevonaWard.mpk;

//SQLITE database items
public class thePokemon{

//private variables
String _name;
String _type;
String _date;
int _level;
int _id;
 
// Empty constructor
public thePokemon(){
     
}
// constructor
public thePokemon(int level, String name, String type, String date, int id){
    this._level = level;
    this._name = name;
    this._type = type;
    this._date = date;
    this._id = id;
}
 
// constructor
public thePokemon(String name, String type){
    this._name = name;
    this._type = type;
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

//getting date
public String getDate(){
 return this._date;
}

//setting date
public void setDate(String date){
 this._date = date;
}

//getting level
public int getLevel(){
return this._level;
}

//setting date
public void setLevel(int level){
this._level = level;
}
}
