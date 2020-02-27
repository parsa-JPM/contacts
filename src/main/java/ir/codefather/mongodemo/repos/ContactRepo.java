package ir.codefather.mongodemo.repos;

import ir.codefather.mongodemo.entities.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends MongoRepository<Contact, String> {
     Contact findByName(String name);
}
