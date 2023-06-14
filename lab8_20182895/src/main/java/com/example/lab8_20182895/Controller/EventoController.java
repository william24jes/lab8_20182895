package com.example.lab8_20182895.Controller;

import com.example.lab8_20182895.entity.Evento;
import com.example.lab8_20182895.repository.EventoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/evento")
public class EventoController {

    private final EventoRepository eventoRepository;

    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @GetMapping("/listar")
    public List<Evento> listarEvento() {
        return eventoRepository.findAll();
    }

}
