package com.example.chatapp101;

public class User {

    private String username;
    private String Email;
    private String profilePicture;

    public User(){

    }
    public User(String username, String email, String profilePicture) {
        this.username = username;
        this.Email = email;
        this.profilePicture = profilePicture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}

