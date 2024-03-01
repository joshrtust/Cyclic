package com.example.deliverable1test.admin;

public class Event {
    String id, eventname, age, pace, level;

    public Event() { //DO NOT REMOVE THIS EMPTY CONSTRUCTOR, Firebase requires it to function
    }

    public Event(String id, String eventname, String age, String pace, String level) {
        this.id = id;
        this.eventname = eventname;
        this.age = age;
        this.pace = pace;
        this.level = level;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPace() {
        return pace;
    }

    public void setPace(String pace) {
        this.pace = pace;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
