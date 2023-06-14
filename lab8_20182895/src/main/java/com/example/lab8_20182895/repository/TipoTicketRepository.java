package com.example.lab8_20182895.repository;

import com.example.lab8_20182895.entity.Evento;
import com.example.lab8_20182895.entity.TipoTicketEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoTicketRepository extends JpaRepository<TipoTicketEvento, Integer> {
}
