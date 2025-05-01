package mineirinho.com.mtrans.dominio.pacote;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Table(name = "pacote")
@Entity
@Setter
@Getter
@AllArgsConstructor
public class Pacote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String cpf;
    private String remetente;
    private String destinatario;
    private String idrastreio;
    @OneToMany(mappedBy = "pacote", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Registro> locais = new ArrayList<>();
    @Override
    public boolean equals(Object obj){
       
        Pacote pacote = (Pacote)obj;
        if (pacote.idrastreio == this.idrastreio){
            return true;
        } 
        else{
            return false;
        }
        
    }
    public Pacote(String cpf, String destinatario, int idrastreio){
        this.cpf= cpf;
        this.destinatario = destinatario;
        this.idrastreio = idrastreio;
    }
    public Pacote(){};    
    public void getDesc(){
        int ultimo = locais.size()>0? locais.size()-1:0;
        Registro locx = locais.get(ultimo);
        System.out.println("A entrega de número "+idrastreio +" de Destinatário "+destinatario +" está em " + locx.getLocal()+".");
        for (int k = 1; k<=locais.size();k++){
            System.out.println(k + " - " + locais.get(k-1));
        }


    }
    public List<Registro> getLocais(){
        return locais;
    }
    public void setRegistros(List<Registro> locais){
        this.locais = locais;
    }
    //INSERIR d no FORMATO DD/MM/AAAA ex 12042025
    public void addReg(Registro registro){
        registro.setPacote(this);
        this.locais.add(registro);
    }


}
