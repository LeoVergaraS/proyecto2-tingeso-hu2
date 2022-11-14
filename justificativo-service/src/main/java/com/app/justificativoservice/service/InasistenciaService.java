package com.app.justificativoservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.justificativoservice.entity.Inasistencia;
import com.app.justificativoservice.models.IngresoSalida;
import com.app.justificativoservice.repository.InasistenciaRepository;

@Service
public class InasistenciaService {

    @Autowired
    InasistenciaRepository inasistenciaRepository;

    RestTemplate restTemplate = new RestTemplate();

    public List<Inasistencia> obtenerInasistencias() {
        return inasistenciaRepository.findAll();
    }

    public Inasistencia obtenerPorId(long id) {
        return inasistenciaRepository.findById(id).orElse(null);
    }

    public Inasistencia guardarInasistencia(Inasistencia inasistencia) {
        Inasistencia nuevaInasistencia = inasistenciaRepository.save(inasistencia);
        return nuevaInasistencia;
    }

    public boolean guardarDelArchivo() {
        IngresoSalida[] inasistencias = restTemplate.getForObject("http://localhost:8001/ingresosSalidas/inasistencias", IngresoSalida[].class);
        if(inasistencias.length != 0){
            String[] fechaSeparada = inasistencias[0].getFecha().toString().split("-");
            int anio = Integer.valueOf(fechaSeparada[0]);
            int mes = Integer.valueOf(fechaSeparada[1]);
            for(IngresoSalida i:inasistencias){
                Inasistencia inasistencia = inasistenciaRepository.findInasistenciaByRutFecha(i.getRutEmpleado(), mes, anio);
                if(inasistencia == null){
                    inasistenciaRepository.save(new Inasistencia(null, mes, anio, 1, 0, i.getRutEmpleado()));
                }else{
                    inasistencia.setCantidadDias(inasistencia.getCantidadDias()+1);
                    inasistenciaRepository.save(inasistencia);
                }
            }
            return true;
        }
        return false;
    }
    
    public Inasistencia obtenerInasistenciaPorEmpleadoYFecha(int mes, int anio, String rut){
        return inasistenciaRepository.findInasistenciaByRutFecha(rut, mes, anio);
    }
}