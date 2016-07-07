package com.example.set_a_p.gtochron;

import io.realm.RealmObject;

public class TimeBase extends RealmObject {

    private String          name;
    private String          family;
    private Boolean         gender;
    private String            Time;


    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }
    public String getFamily() { return family; }
    public void   setFamily(String family) { this.family = family; }
    public Boolean getGender() { return gender; }
    public void   setGender(Boolean gender) { this.gender = gender; }
    public String   getTime() { return Time; }
    public void   setTime(String Time) { this.Time = Time; }
}
