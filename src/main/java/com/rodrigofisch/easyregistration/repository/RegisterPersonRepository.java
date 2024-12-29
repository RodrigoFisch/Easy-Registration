package com.rodrigofisch.easyregistration.repository;

import com.rodrigofisch.easyregistration.domain.RegisterPerson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterPersonRepository extends MongoRepository<RegisterPerson, String> {
}
