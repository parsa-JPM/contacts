package ir.codefather.mongodemo.entities;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Contact {

    @Id
    public String id;
    public String name;
    public String number;
    public String profile;
    public LocalDateTime createdDate;

    public Contact() {
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
        this.createdDate = LocalDateTime.now();
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", profile='" + profile + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
