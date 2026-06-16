package entities;

import java.util.Formatter;

public class User {
    private String id;
    private String name;
    private String emailId;

    public User(String id,String name,String emailId){
        this.id = id;
        this.name = name;
        this.emailId = emailId;
    }

    public String getName(){
        return name;
    };
    public String getId(){
        return id;
    };

}
