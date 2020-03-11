package ir.codefather.mongodemo.endpoints;


import ir.codefather.mongodemo.dto.ContactDTO;
import ir.codefather.mongodemo.entities.Contact;
import ir.codefather.mongodemo.repos.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactCrudAPI {

    private final ContactRepo contactRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    public ContactCrudAPI(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }


    @PostMapping("/api/add/contact")
    public Contact add(@RequestBody ContactDTO contactDTO) {
        Contact contact = contactRepo.save(new Contact(contactDTO.getName(), contactDTO.getNumber()));

        return contact;
    }

    @PostMapping("/api/update/contact/{id}")
    public Contact update(@RequestBody ContactDTO contactDTO, @PathVariable String id) {
        Contact contact = contactRepo.findById(id).orElseThrow();
        contact.name = contactDTO.getName();
        contact.number = contactDTO.getNumber();
        contactRepo.save(contact);

        return contact;
    }

    @PostMapping("/api/delete/contact/{id}")
    public void delete(@PathVariable String id) {
        contactRepo.deleteById(id);
    }

    /**
     * get list of contacts with paginate and order(with mongo template)
     *
     * @return List<Contact>
     */
    @GetMapping("/api/contacts")
    public List<Contact> getContacts() {
        Query query = new Query();
        //TODO make it paginate dynamic
        Pageable pageable = PageRequest.of(0, 100);
        query.with(Sort.by(Sort.Direction.ASC,"name"));
        query.collation(Collation.of("fa"));
        query.with(pageable);

        return mongoTemplate.find(query, Contact.class);
    }
}
