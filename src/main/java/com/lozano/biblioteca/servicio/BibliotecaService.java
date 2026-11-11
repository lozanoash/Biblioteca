package com.lozano.biblioteca.servicio;

import com.lozano.biblioteca.modelo.Biblioteca;
import com.lozano.biblioteca.repository.BibliotecaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class BibliotecaService {
    private BibliotecaRepository bibliotecaRepository;

    public BibliotecaService(BibliotecaRepository bibliotecaRepository){
        this.bibliotecaRepository = bibliotecaRepository;

    }
    //TRAER TODOS
    public List<Biblioteca> traerTodos(){
        return bibliotecaRepository.findAll();
    }
    //TRAE UNO
    public Optional <Biblioteca> traerUno(int id){
        return bibliotecaRepository.findById(id);
    }
    //GUARDAR
    public Biblioteca  guardar (Biblioteca biblioteca){
        return bibliotecaRepository.save(biblioteca);
    }
    //EDITAR
    public Optional<Biblioteca> editar(int id, Biblioteca bibliotecaActualziado){
        return bibliotecaRepository.findById(id)
                .map( bibliotecaExiste ->{
                    bibliotecaExiste.setAnio(bibliotecaActualziado.getAnio());
                    bibliotecaExiste.setNombre(bibliotecaActualziado.getNombre());
                    bibliotecaExiste.setAutor(bibliotecaActualziado.getAutor());

                    return bibliotecaRepository.save( bibliotecaExiste);
                });
    }
    //ELIMINAR
    public boolean eliminar (int id){
        if ( bibliotecaRepository.existsById(id)){
            bibliotecaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    //Especial
    public List <Biblioteca> disponibles (){
        return bibliotecaRepository.findAll()
                .stream().filter(
                         biblioteca -> biblioteca.isDisponible()== true
                ).collect(Collectors.toList());
    }
}
