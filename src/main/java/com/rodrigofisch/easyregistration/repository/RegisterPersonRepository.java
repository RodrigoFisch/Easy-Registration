package com.rodrigofisch.easyregistration.repository;

import com.rodrigofisch.easyregistration.domain.RegisterPerson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterPersonRepository extends MongoRepository<RegisterPerson, String> {
    Optional<RegisterPerson> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
