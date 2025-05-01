package mineirinho.com.mtrans.controller;

import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.ipc.http.HttpSender.Response;
import mineirinho.com.mtrans.dominio.pacote.Pacote;
import mineirinho.com.mtrans.dominio.pacote.PacoteDetailsDTO;
import mineirinho.com.mtrans.dominio.pacote.PacoteRequestDTO;
import mineirinho.com.mtrans.dominio.pacote.PacoteResponseDTO;
import mineirinho.com.mtrans.dominio.pacote.Registro;
import mineirinho.com.mtrans.dominio.pacote.RegistroRequestDTO;
import mineirinho.com.mtrans.repositorios.PacoteRep;
import mineirinho.com.mtrans.service.PacoteService;

import java.util.List;
import java.util.Optional;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/pacote")
public class PacoteController {
    @Autowired
    private PacoteRep pacoteRep;
    @Autowired
    private PacoteService pacoteservice;
    @PostMapping
    public ResponseEntity<Pacote> create(@RequestParam("cpf") String cpf, @RequestParam("destinatario")String destinatario, @RequestParam("remetente") String remetente, @RequestParam("idrastreio")int idrastreio){
        PacoteRequestDTO pacoteRequestDTO = new PacoteRequestDTO(cpf,remetente,destinatario,idrastreio);
        Pacote pacote = this.pacoteservice.createPacote(pacoteRequestDTO);
        return ResponseEntity.ok(pacote);
    }

    public ResponseEntity<List<PacoteResponseDTO>> getPacotes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        List<PacoteResponseDTO> allpacotes = pacoteservice.getPacotes(page,size);
        return ResponseEntity.ok(allpacotes);
    }
    @GetMapping("/rastrear/{codigoRastreio}")
    public ResponseEntity<?> rastrearPacote(@PathVariable String codigoRastreio) {
        Optional<Pacote> pacoteOptional = pacoteRep.findByCodigoRastreio(codigoRastreio);

        if (pacoteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pacote não encontrado.");
        }

        Pacote pacote = pacoteOptional.get();
        List<Registro> registros = pacote.getLocais();

        List<RegistroRequestDTO> registroDTOs = registros.stream()
        .map(reg -> new RegistroRequestDTO(reg.getLocal(), reg.getData()))
        .toList();

    PacoteDetailsDTO dto = new PacoteDetailsDTO(
        pacote.getId(),
            pacote.getIdrastreio(),
        pacote.getRemetente(),
            pacote.getDestinatario(),
        pacote.getCpf(),


        registroDTOs
        );

    return ResponseEntity.ok(dto);
}
}

