package com.vedoque.notas.servicios;

import com.vedoque.notas.modelo.Entrada;
import com.vedoque.notas.repositorios.RepositorioEntradas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioEntradas {
    @Autowired
    RepositorioEntradas repositorio;

    public Entrada findById(long id){
        return repositorio.findById(id);
    }

    public List<Entrada> findAll(){
        return repositorio.findAll();
    }

    public void save(Entrada entrada){
        repositorio.save(entrada);
    }

    public void delete(Entrada entrada){
        repositorio.delete(entrada);
    }
    public List<Entrada> findAllOrderByFechaAsc(){
        return repositorio.findAllByOrderByFechaAsc();
    }
    public List<Entrada> findAllOrderByFechaDesc(){
        return repositorio.findAllByOrderByFechaDesc();
    }
}
