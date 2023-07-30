package com.dh.homework.bid.repository;

import com.dh.homework.bid.domain.CreditBid;
import com.dh.homework.bid.domain.User;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CreditBidRepositoryTest {

    @Autowired
    private CreditBidRepository creditBidRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        String externalId = UUID.randomUUID().toString();
        User user = userRepository.save(new User()
                .setExternalId(externalId)
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setEmail("test@mail.com")
                .setPhone("79026521137"));
        CreditBid creditBid = creditBidRepository.save(new CreditBid()
                .setUser(user)
                .setAmount(500_000)
                .setGoal(CreditBid.Goal.BUYING_NEW_CAR)
                .setReceivingCity("Moscow"));

        Assertions.assertNotNull(creditBid.getUser().getId());
        Assertions.assertEquals(creditBid.getUser().getExternalId(), externalId);
        Assertions.assertEquals(creditBid.getUser().getFirstName(), "Ivan");
        Assertions.assertEquals(creditBid.getUser().getLastName(), "Ivanov");
        Assertions.assertEquals(creditBid.getUser().getEmail(), "test@mail.com");
        Assertions.assertEquals(creditBid.getUser().getPhone(), "79026521137");
        Assertions.assertNull(creditBid.getUser().getMiddleName());

        Assertions.assertNotNull(creditBid.getId());
        Assertions.assertEquals(creditBid.getAmount(), 500_000);
        Assertions.assertEquals(creditBid.getGoal(), CreditBid.Goal.BUYING_NEW_CAR);
        Assertions.assertEquals(creditBid.getReceivingCity(), "Moscow");
    }
}
