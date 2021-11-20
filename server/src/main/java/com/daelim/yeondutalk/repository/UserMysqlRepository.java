package com.daelim.yeondutalk.repository;

import com.daelim.yeondutalk.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

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
    public User findByUserId(String userId) {

        List<User> userList = em.createQuery("select u from User u where u.userId = :userId", User.class)
                .setParameter("userId", userId).getResultList();
        if (userList.isEmpty()) {
            return null;
        }else{
            return userList.get(0);
        }
    }
}
