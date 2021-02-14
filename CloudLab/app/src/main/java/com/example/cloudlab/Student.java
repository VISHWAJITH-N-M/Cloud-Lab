package com.example.cloudlab;

public class Student {

    private String names;
    private String Email;
    private String Feedback;

    public Student() {
        // This is default constructor.
    }

    public String getName() {

        return names;
    }
    public String getEmail() {

        return Email;
    }
    public String getfeedback() {

        return Feedback;
    }
    public void setName(String name) {

        this.names = name;
    }

    public void setEmail(String email) {

        this.Email = email;
    }
    public void setfeedback(String feedback) {

        this.Feedback = feedback;
    }


}
