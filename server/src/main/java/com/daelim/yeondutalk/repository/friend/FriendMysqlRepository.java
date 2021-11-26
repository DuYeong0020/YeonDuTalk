package com.daelim.yeondutalk.repository.friend;

import com.daelim.yeondutalk.domain.Friend;
import com.daelim.yeondutalk.domain.User;
import com.daelim.yeondutalk.dto.friend.FriendListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class FriendMysqlRepository implements FriendRepository {
    

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
        return em.createQuery("select f from Friend f where f.requestUser = :requestUser and f.tagUser = :tagUser ", Friend.class)
                .setParameter("requestUser", requestUser)
                .setParameter("tagUser", tagUser)
                .getResultStream().findFirst().orElse(null);
    }


    @Override
    public List<FriendListDTO> findFriendListByRequestUser(User requestUser) {
        return em.createQuery("select new com.daelim.yeondutalk.dto.friend.FriendListDTO(u.id, u.userName) " +
                        "from Friend f inner join f.tagUser u where f.requestUser = :requestUser and f.accepted = 'Y' and f.deleted = 'N'", FriendListDTO.class)
                .setParameter("requestUser", requestUser)
                .getResultList();
    }

    @Override
    public void deleteFriend(User requestUser, User tagUser) {
        em.createQuery("delete from Friend f where (f.requestUser =: requestUser and f.tagUser = :tagUser and f.accepted = 'Y') " +
                "or (f.requestUser =: tagUser and f.tagUser =: requestUser and f.accepted = 'Y')")
                .setParameter("requestUser", requestUser)
                .setParameter("tagUser", tagUser).executeUpdate();
    }

    @Override
    public void deleteFriendRequest(User requestUser, User tagUser) {
        em.createQuery("delete from Friend f where f.requestUser =: requestUser and f.tagUser =: tagUser and f.accepted = 'N'")
                .setParameter("requestUser", requestUser)
                .setParameter("tagUser", tagUser).executeUpdate();
    }
}
