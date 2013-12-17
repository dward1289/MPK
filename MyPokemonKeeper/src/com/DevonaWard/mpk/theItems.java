package com.DevonaWard.mpk;


public class theItems {
	
	private String Name = "";
    private String Type = "";
    private String Date = "";
    private String Level = "";
    private String image;

    public void setName(String name) {
     this.Name = name;
    }

    public String getName() {
     return Name;
    }

    public void setType(String type) {
     this.Type = type;
    }

    public String getType() {
     return Type;
    }
    public void setDate(String date) {
     this.Date = date;
    }

    public String getDate() {
     return Date;
    }
    
    public void setLevel(String level) {
        this.Level = level;
       }

       public String getLevel() {
        return Level;
       }
       
    public void setImage(String image) {
           this.image = image;
          }

    public String getImage() {
           return image;
          }

}
