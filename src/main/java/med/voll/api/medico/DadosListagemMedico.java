package med.voll.api.medico;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade ) {

    public DadosListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
