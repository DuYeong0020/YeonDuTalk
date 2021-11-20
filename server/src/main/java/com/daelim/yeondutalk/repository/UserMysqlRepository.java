package com.daelim.yeondutalk.repository;

import com.daelim.yeondutalk.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserMysqlRepository implements UserRepository{

    private final EntityManager em;

    @Override
    public Long save(User user) {
        em.persist(user);
        return user.getId();
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByUserId(Long userId) {

        return em.createQuery("select u from User u where u.userId = :userId", User.class)
                .setParameter("userId", userId).getResultList().get(0);
    }
}
