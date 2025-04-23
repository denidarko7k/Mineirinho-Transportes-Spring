package mineirinho.com.mtrans.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mineirinho.com.mtrans.dominio.pacote.Pacote;
import mineirinho.com.mtrans.dominio.pacote.Registro;
import mineirinho.com.mtrans.dominio.pacote.RegistroRequestDTO;
import mineirinho.com.mtrans.repositorios.PacoteRep;
import mineirinho.com.mtrans.repositorios.RegistroRep;

@Service
public class RegistroService {
    @Autowired
    private RegistroRep repository;

    @Autowired 
    private PacoteRep pacoterep;

    public Registro addRegpPacote(UUID pacoteid, RegistroRequestDTO data){
        Pacote pacote = pacoterep.findById(pacoteid).orElseThrow(() -> new IllegalArgumentException("pacote not found"));
        Registro registro = new Registro();
        registro.setLocal(data.local());
        registro.setData(data.data());
        registro.setPacote(pacote);
        
        return repository.save(registro);
    }
    public List<Registro> consultRegistro(UUID pacoteId){
        return repository.findByPacoteId(pacoteId);
    }

}
