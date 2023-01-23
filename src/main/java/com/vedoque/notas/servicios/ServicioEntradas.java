package com.vedoque.notas.servicios;

import com.vedoque.notas.modelo.Entrada;
import com.vedoque.notas.repositorios.RepositorioEntradas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public Page<Entrada> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Entrada> lista=findAll();

        if (lista.size() < startItem) {
            lista = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, lista.size());
            lista = lista.subList(startItem, toIndex);
        }

        Page<Entrada> entradasPagina = new PageImpl<Entrada>(lista, PageRequest.of(currentPage, pageSize), findAll().size());

        return entradasPagina;
    }
}
