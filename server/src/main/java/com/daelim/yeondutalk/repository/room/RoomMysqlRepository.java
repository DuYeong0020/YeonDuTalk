package com.daelim.yeondutalk.repository.room;

import com.daelim.yeondutalk.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class RoomMysqlRepository implements RoomRepository {
    private final EntityManager em;


    @Override
    public Room findById(Long id) {
        return em.find(Room.class, id);
    }
}
