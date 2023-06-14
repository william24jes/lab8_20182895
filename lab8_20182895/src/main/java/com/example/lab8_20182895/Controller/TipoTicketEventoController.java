package com.example.lab8_20182895.Controller;

import com.example.lab8_20182895.entity.TipoTicketEvento;
import com.example.lab8_20182895.repository.TipoTicketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/eventoConTipoDeTicket")
public class TipoTicketEventoController {
    private final TipoTicketRepository tipoTicketRepository;

    public TipoTicketEventoController(TipoTicketRepository tipoTicketRepository) {
        this.tipoTicketRepository = tipoTicketRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HashMap<String,Object>> obtenerProductoPorId(
            @PathVariable("id") String idStr) {


        HashMap<String,Object> responseJson = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);
            Optional<TipoTicketEvento> optProduct = tipoTicketRepository.findById(id);
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
}
