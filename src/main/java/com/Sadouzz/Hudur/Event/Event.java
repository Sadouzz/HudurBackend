package com.Sadouzz.Hudur.Event;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.Sadouzz.Hudur.Presence.Presence;
import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;
    private Date startDate;
    private Date endDate;
    private String photoUrl;

    private LocalTime startRegistration;
    private LocalTime endRegistration;

    @OneToMany(mappedBy = "event")
    private List<Presence> presences;

    /*@Lob
    private byte[] imageData;*/

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }
    public void setEndDate(Date date) {
        this.endDate = date;
    }

    public LocalTime getStartRegistration() {
        return startRegistration;
    }
    public void setStartRegistration(LocalTime startRegistration) {
        this.startRegistration = startRegistration;
    }

    public LocalTime getEndRegistration() {
        return endRegistration;
    }
    public void setEndRegistration(LocalTime endRegistration) {
        this.endRegistration = endRegistration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /*public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }*/
}
