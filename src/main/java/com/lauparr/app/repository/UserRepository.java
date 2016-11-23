package com.lauparr.app.repository;

import com.lauparr.app.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by lauparr on 14/11/2016.
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    User findByIdAndEmail(String id, String email);
}
