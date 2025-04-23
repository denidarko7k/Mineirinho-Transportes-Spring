package mineirinho.com.mtrans.dominio.pacote;

import java.util.UUID;

public record PacoteResponseDTO(UUID id, String cpf, String remetente, String destinatario, int idrastreio) {
}
