package mineirinho.com.mtrans.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mineirinho.com.mtrans.dominio.pacote.Registro;
import mineirinho.com.mtrans.dominio.pacote.RegistroRequestDTO;
import mineirinho.com.mtrans.service.RegistroService;

@RestController
@RequestMapping("/api/registro")
public class RegistroController {
    @Autowired
    private RegistroService registroService;
    @PostMapping("/pacote/{pacoteId}")
    public ResponseEntity<Registro> create(@PathVariable UUID pacoteId, @RequestBody RegistroRequestDTO data){
        Registro registros = registroService.addRegpPacote(pacoteId, data);
        return ResponseEntity.ok(registros);
    }

}
