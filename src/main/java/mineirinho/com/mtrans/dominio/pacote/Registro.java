package mineirinho.com.mtrans.dominio.pacote;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
@Table (name = "registro")
@Entity
public class Registro {
    @Override
    public String toString() {
        return local +" - " + data;
    }
    
    public Registro(String local,LocalDateTime data){
        this.local = local;
        this.data = data;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String local;
    private LocalDateTime data;
    public String getLocal(){
        return this.local;
    }

    @ManyToOne
    @JoinColumn(name = "pacote_id")

    private Pacote pacote;

}
