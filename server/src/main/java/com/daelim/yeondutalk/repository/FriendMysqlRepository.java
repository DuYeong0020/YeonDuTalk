package com.daelim.yeondutalk.repository;

import com.daelim.yeondutalk.domain.Friend;
import com.daelim.yeondutalk.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional
public class FriendMysqlRepository implements FriendRepository{

    private final EntityManager em;

    @Override
    public Long save(User requestUser, User tagUser) {
        Friend buildFriend = Friend.builder()
                .requestUser(requestUser)
                .tagUser(tagUser).build();
        em.persist(buildFriend);

        return buildFriend.getId();
    }

    @Override
    public Long saveContrary(User requestUser, User tagUser) {
        Friend buildFriend = Friend.builder()
                .requestUser(tagUser)
                .tagUser(requestUser)
                .accepted("Y").build();

        em.persist(buildFriend);

        return buildFriend.getId();
    }



    @Override
    public Friend findById(Long id) {
        return em.find(Friend.class, id);
    }

    @Override
    public Friend findByRequestTagId(User requestUser, User tagUser) {
        return em.createQuery("select f from Friend f where f.requestUser = :requestUser and f.tagUser = :tagUser", Friend.class)
                .setParameter("requestUser", requestUser)
                .setParameter("tagUser", tagUser)
                .getResultStream().findFirst().orElse(null);
    }
}
