package com.app.justificativoservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.justificativoservice.entity.Inasistencia;
import com.app.justificativoservice.service.InasistenciaService;

@RestController
@RequestMapping("/inasistencia")
public class InasistenciaController {

    @Autowired
    InasistenciaService inasistenciaService;

    @GetMapping
    public ResponseEntity<List<Inasistencia>> getAll(){
        List<Inasistencia> inasistencias = inasistenciaService.obtenerInasistencias();
        if(inasistencias.isEmpty()){return ResponseEntity.noContent().build();}
        return ResponseEntity.ok(inasistenciaService.obtenerInasistencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inasistencia> getById(@PathVariable("id") long id){
        Inasistencia inasistencia = inasistenciaService.obtenerPorId(id);
        if(inasistencia == null){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(inasistencia);
    }

    @PostMapping()
    public ResponseEntity<Inasistencia> create(@RequestBody Inasistencia inasistencia){
        Inasistencia nuevInasistencia = inasistenciaService.guardarInasistencia(inasistencia);
        return ResponseEntity.ok(nuevInasistencia);
    }

    @PostMapping("/file")
    public ResponseEntity<String> createFromFile(){
        boolean creado = inasistenciaService.guardarDelArchivo();
        if(creado){
            return ResponseEntity.ok("Inasistencias creadas");
        }
        return ResponseEntity.badRequest().body("No se pudieron crear las inasistencias");
    }

    @GetMapping("/empleado/{rut}/mes/{mes}/anio/{anio}")
    public ResponseEntity<Inasistencia> getByEmpleadoFecha(@PathVariable("rut") String rut, @PathVariable("mes") int mes, @PathVariable("anio") int anio){
        Inasistencia inasistencia = inasistenciaService.obtenerInasistenciaPorEmpleadoYFecha(mes, anio, rut);
        if(inasistencia == null){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(inasistencia);
    }

}
