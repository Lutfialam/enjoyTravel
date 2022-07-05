package model;

import java.io.Serializable;

import com.google.firebase.database.DataSnapshot;

public class UserModel implements Serializable {
    String id;
    String name;
    String email;
    String password;
    String level;

    public UserModel setUser(DataSnapshot snapshot) {
        UserModel data = new UserModel();
        data.setId(snapshot.getKey());
        data.setName(snapshot.child("name").getValue(String.class));
        data.setEmail(snapshot.child("email").getValue(String.class));
        data.setLevel(snapshot.child("level").getValue(String.class));
        data.setPassword(snapshot.child("password").getValue(String.class));
        return data;
    }

    public UserModel() {
    }

    public UserModel(String id, String name, String email, String password, String level) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.level = level;
    }

    public UserModel(String name, String email, String password, String level) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.level = level;
    }

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
