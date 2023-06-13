package com.caschile.horus.repository;

import com.caschile.horus.model.PermisoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisoRepository extends JpaRepository<PermisoModel, Integer> {

    List<PermisoModel> findTop10ByNumeroCaja(Short caja);
}
