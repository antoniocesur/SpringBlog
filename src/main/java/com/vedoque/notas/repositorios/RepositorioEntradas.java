package com.vedoque.notas.repositorios;

import com.vedoque.notas.modelo.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioEntradas extends JpaRepository<Entrada, Long> {
    public Entrada findById(long id);
    public List<Entrada> findAll();
    public List<Entrada> findAllByOrderByFechaAsc();
    public List<Entrada> findAllByOrderByFechaDesc();
}
