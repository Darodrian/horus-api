package com.caschile.horus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caschile.horus.model.BlackList;
import com.caschile.horus.repository.BlackListRepository;

@Repository
public class BlackListService{

    @Autowired
    private BlackListRepository blackListRepo;

    public BlackList getToken(String token) {
        return blackListRepo.findByToken(token);
    }

    public BlackList guardarToken(BlackList token) {
        return blackListRepo.save(token);
    }

}
