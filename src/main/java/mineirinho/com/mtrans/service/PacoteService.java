package mineirinho.com.mtrans.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import mineirinho.com.mtrans.dominio.pacote.Pacote;
import mineirinho.com.mtrans.dominio.pacote.PacoteRequestDTO;
import mineirinho.com.mtrans.dominio.pacote.PacoteResponseDTO;
import mineirinho.com.mtrans.dominio.pacote.Registro;
import mineirinho.com.mtrans.repositorios.PacoteRep;

@Service
public class PacoteService {

    @Autowired
    private PacoteRep repository;
    public Pacote createPacote(PacoteRequestDTO data){
        Pacote pacote = new Pacote();
        pacote.setCpf(data.cpf());
        pacote.setDestinatario(data.destinatario());
        pacote.setIdrastreio(data.idrastreio());
        pacote.setRemetente(data.remetente());  
        repository.save(pacote);
        return pacote;

    }
    @GetMapping
    public List<PacoteResponseDTO> getPacotes(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Pacote> pacotePage = this.repository.findAll(pageable);
        return pacotePage.map(pacote -> new PacoteResponseDTO(pacote.getId(), pacote.getCpf(),pacote.getDestinatario(),pacote.getRemetente(),pacote.getIdrastreio())).stream().toList();
    }
    public void addRegPacote(UUID pacoteId, String local){
        Pacote pacote = repository.findById(pacoteId)
                .orElseThrow(() -> new RuntimeException("Pacote não encontrado"));

        Registro novoRegistro = new Registro();
        novoRegistro.setLocal(local);
        novoRegistro.setData(LocalDateTime.now());

        pacote.addReg(novoRegistro);

        repository.save(pacote);
    }
}