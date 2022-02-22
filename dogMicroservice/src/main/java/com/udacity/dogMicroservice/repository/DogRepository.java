package com.udacity.dogMicroservice.repository;

import com.udacity.dogMicroservice.entity.Dog;
import org.springframework.data.repository.CrudRepository;
/**
 * This repository is for creating, reading, updating, and deleting Dog objects.
 * Note: This repository will not need to implement anything beyond an interface.
 *
 * Depending on your implementation, this can be re-used from either the REST API or GraphQL API.
 * In this case, we don't need to add anything within the interface here.
 */

@Repository
public interface DogRepository extends CrudRepository<Dog, Long> {
}