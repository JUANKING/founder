package com.foundernest.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.foundernest.domain.Investor;

@Repository
public interface InvestorRepository extends MongoRepository<Investor, String> {

}
