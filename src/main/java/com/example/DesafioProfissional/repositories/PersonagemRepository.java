package com.example.DesafioProfissional.repositories;
import com.example.DesafioProfissional.domains.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}
