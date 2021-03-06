package com.daelim.yeondutalk.repository.user;

import com.daelim.yeondutalk.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserMysqlRepository implements UserRepository {

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
    public User findByUserId(String userId) {
        return em.createQuery("select u from User u where u.userId = :userId", User.class)
                .setParameter("userId", userId).getResultStream().findFirst().orElse(null);

    }

    @Override
    public User findByUserIdPassword(User user) {
        return em.createQuery("select u from User u where u.userId = :userId and u.password = :password", User.class)
                .setParameter("userId", user.getUserId())
                .setParameter("password", user.getPassword())
                .getResultStream().findFirst().orElse(null);
    }
}
