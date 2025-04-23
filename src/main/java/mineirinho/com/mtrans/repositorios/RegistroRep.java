package mineirinho.com.mtrans.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import mineirinho.com.mtrans.dominio.pacote.Registro;

public interface RegistroRep extends JpaRepository<Registro, UUID> {
    List<Registro> findByPacoteId(UUID pacoteId);
}
