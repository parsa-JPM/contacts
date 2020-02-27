package ir.codefather.mongodemo.entities;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Contact {

    @Id
    public String id;
    public String name;
    public String number;
    public LocalDateTime createdDate;

    public Contact() {
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
        this.createdDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
