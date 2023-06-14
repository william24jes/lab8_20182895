package com.example.lab8_20182895.Controller;

import com.example.lab8_20182895.entity.Evento;
import com.example.lab8_20182895.repository.EventoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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



    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String,Object>> obtenerProductoPorId(
            @PathVariable("id") String idStr) {


        HashMap<String,Object> responseJson = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);
            Optional<Evento> optProduct = eventoRepository.findById(id);
            if (optProduct.isPresent()) {
                responseJson.put("result","success");
                responseJson.put("evento",optProduct.get());
                return ResponseEntity.ok(responseJson);
            } else {
                responseJson.put("msg","Evento no encontrado");
            }
        } catch (NumberFormatException e) {
            responseJson.put("msg","el ID debe ser un número entero positivo");
        }
        responseJson.put("result","failure");
        return ResponseEntity.badRequest().body(responseJson);
    }
    @PostMapping(value = "")
    public ResponseEntity<HashMap<String,Object>> crearEvento(
            @RequestBody Evento evento,
            @RequestParam (value = "fetchId",required = false) boolean fetchId){
        HashMap<String,Object> responseMap = new HashMap<>();
        eventoRepository.save(evento);
        if (fetchId==true ){
            responseMap.put("estado", "creado");
            responseMap.put("id", evento.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
        }else if (fetchId==false){
            responseMap.put("estado", "creado");
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
        }else{
            responseMap.put("msg", "Debe enviar un evento");
            responseMap.put("estado", "error");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String,String>> gestionExcepcion(HttpServletRequest request) {

        HashMap<String, String> responseMap = new HashMap<>();

        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "“error");
            responseMap.put("msg", "Debe enviar un evento");

        }
        return ResponseEntity.badRequest().body(responseMap);
    }


}
