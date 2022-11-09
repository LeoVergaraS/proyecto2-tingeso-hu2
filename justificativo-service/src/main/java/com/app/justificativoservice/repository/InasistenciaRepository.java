package com.app.justificativoservice.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.justificativoservice.entity.Inasistencia;

@Repository
public interface InasistenciaRepository extends JpaRepository<Inasistencia, Long> {
        @Transactional
        @Modifying
        @Query(value = "UPDATE inasistencias i SET i.dias_justificado = :d WHERE i.id = :id",
        nativeQuery = true)
        void updateDiasJustificados(@Param("d") int d, @Param("id") long id);

        @Query(value = "select * from inasistencias i where i.rut_empleado = :rut and i.mes = :mes and i.anio = :anio",
        nativeQuery = true)
        Inasistencia findInasistenciaByRutFecha(@Param("rut") String rut, @Param("mes") int mes, @Param("anio") int anio);
}
