package org.signal.bot.repository;

import org.bson.types.ObjectId;
import org.signal.bot.model.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends MongoRepository<Price, ObjectId> {

    Price findFirstByOrderByIdDesc();

}
