package com.app.justificativoservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.justificativoservice.entity.Inasistencia;
import com.app.justificativoservice.repository.InasistenciaRepository;

@Service
public class InasistenciaService {
    @Autowired
    InasistenciaRepository inasistenciaRepository;

    public List<Inasistencia> obtenerInasistencias() {
        return inasistenciaRepository.findAll();
    }

    public Inasistencia obtenerPorId(long id) {
        return inasistenciaRepository.findById(id).orElse(null);
    }

    public Inasistencia guardarInasistencia(Inasistencia inasistencia) {
        return inasistenciaRepository.save(inasistencia);
    }
    
    public Inasistencia obtenerInasistenciaPorEmpleadoYFecha(int mes, int anio, String rut){
        return inasistenciaRepository.findInasistenciaByRutFecha(rut, mes, anio);
    }
}