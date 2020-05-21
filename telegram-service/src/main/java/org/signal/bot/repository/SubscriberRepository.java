package org.signal.bot.repository;

import org.bson.types.ObjectId;
import org.signal.bot.domain.Subscriber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends MongoRepository<Subscriber, ObjectId> {

    Subscriber findByChatId(Long chatId);

    void deleteByChatId(Long chatId);

}
