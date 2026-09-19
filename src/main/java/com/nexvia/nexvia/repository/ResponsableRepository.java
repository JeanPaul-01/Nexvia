package com.nexvia.nexvia.repository;

import com.nexvia.nexvia.entity.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponsableRepository extends JpaRepository<Responsable, Long> {
    List<Responsable> findByDisponibleTrue();
    List<Responsable> findByEspecialidadIgnoreCaseAndDisponibleTrue(String especialidad);
}
