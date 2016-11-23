package com.lauparr.app.repository;

import com.lauparr.app.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by lauparr on 14/11/2016.
 */
@RepositoryRestResource(collectionResourceRel = "projects", path = "projects")
public interface ProjectRepository extends MongoRepository<Project, String> {

}
