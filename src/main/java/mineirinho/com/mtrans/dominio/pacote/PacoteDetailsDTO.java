package mineirinho.com.mtrans.dominio.pacote;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PacoteDetailsDTO(
    UUID id,
    String remetente,
    String cpf,
    String destinatario,
    String idRastreio,
    List<RegistroDTO> locais) {
        public record RegistroDTO(
            String local,
            LocalDateTime data ){

        }
    }


