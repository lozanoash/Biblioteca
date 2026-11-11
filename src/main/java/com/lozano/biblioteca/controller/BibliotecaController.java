package com.lozano.biblioteca.controller;

import com.lozano.biblioteca.modelo.Biblioteca;
import com.lozano.biblioteca.servicio.BibliotecaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biblioteca")
public class BibliotecaController {

    private BibliotecaService bibliotecaService;

    public BibliotecaController(BibliotecaService bibliotecaService){
        this.bibliotecaService = bibliotecaService;
    }

    @GetMapping
    public List<Biblioteca> verTodos(){
        return bibliotecaService.traerTodos();
    }
    //GUARDAR
    @PostMapping
    public ResponseEntity<Biblioteca> guardar(@RequestBody Biblioteca biblioteca){
        Biblioteca nuevaBiblioteca = bibliotecaService.guardar(biblioteca);
        return new ResponseEntity<>(nuevaBiblioteca, HttpStatus.CREATED);
    }
    //EDITAR
    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> editar(@PathVariable int id, @RequestBody Biblioteca biblioteca){
        return bibliotecaService.editar(id, biblioteca)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //TRAER UNO
    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> traerUno(@PathVariable int id){
        return bibliotecaService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Biblioteca> eliminar (@PathVariable int id){
        if (bibliotecaService.eliminar(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }


    }
    @GetMapping("/disponibles")
    public List <Biblioteca> disponibles(){
        return bibliotecaService.disponibles();
    }
}

