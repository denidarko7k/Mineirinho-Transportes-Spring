package mineirinho.com.mtrans.repositorios;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import mineirinho.com.mtrans.dominio.pacote.Pacote;
import org.springframework.stereotype.Repository;

@Repository
public interface PacoteRep extends JpaRepository<Pacote, UUID> {
    Optional<Pacote> findByCodigoRastreio(String codigoRastreio);
}