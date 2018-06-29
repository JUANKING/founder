package com.foundernest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.foundernest.domain.Startup;

@Repository
public interface StartupRepository extends MongoRepository<Startup, String> {
	Page <Startup> findAllByFounderId(String founderId, Pageable pageable);
}
