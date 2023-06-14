package com.example.lab8_20182895.repository;

import com.example.lab8_20182895.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
