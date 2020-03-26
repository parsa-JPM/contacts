package ir.codefather.mongodemo.repos;

import ir.codefather.mongodemo.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends MongoRepository<Contact, String> {
    Contact findByName(String name);

    @Query(value = "{}", collation = "fa")
    Page<Contact> findAllPaginate(Pageable pageable);

    @Query(value = "{$or:[{name:{$regex: ?0 }},{number   :?0}]}", collation = "fa")
    Page<Contact> search(String nameOrMobile, Pageable pageable);

}
