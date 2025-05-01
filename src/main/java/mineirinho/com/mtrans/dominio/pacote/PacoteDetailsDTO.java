package mineirinho.com.mtrans.dominio.pacote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PacoteDetailsDTO(
        UUID id,
        String idRastreio,
        String remetente,
        String destinatario,
        String status,
        List<RegistroRequestDTO> registros
) {
    public PacoteDetailsDTO(Pacote pacote, List<Registro> registros) {
        this(
                pacote.getId(),
                pacote.getIdrastreio(),
                pacote.getRemetente(),
                pacote.getDestinatario(),
                pacote.getCpf(),
                registros.stream()
                        .map(r -> new RegistroRequestDTO(r.getLocal(), r.getData()))
                        .toList()
        );
    }
}

