package com.caschile.horus.security.repository;


import com.caschile.horus.security.entity.Correlativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CorrelativoRepository extends JpaRepository<Correlativo, Integer> {


}
