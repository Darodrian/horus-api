package com.caschile.horus.repository;



import com.caschile.horus.model.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, Integer> {

    BlackList findByToken(String token);
}
