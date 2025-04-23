package mineirinho.com.mtrans.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import mineirinho.com.mtrans.dominio.pacote.Pacote;

public interface PacoteRep extends JpaRepository<Pacote, UUID> {

}
