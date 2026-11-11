package com.lozano.biblioteca.repository;

import com.lozano.biblioteca.modelo.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository<Biblioteca,Integer> {
}
