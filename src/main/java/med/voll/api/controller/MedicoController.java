package med.voll.api.controller;

import med.voll.api.endereco.DadosEndereco;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
    //anotação indicando para o spring carregar quando for iniciado o projeto
@RestController
    //anotação indicando que esta sendo mapeando a url /medicos
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
        private MedicoRepository repository;
    // verbo http que salva no banco de dados
    @PostMapping
    @Transactional
    // metodo que indica o cadastro de medicos @requestBody puxa do corpo da requisção o metodo json
    public void cadastrar(@RequestBody DadosCadastroMedico dados){
    repository.save(new Medico(dados));



    }

}
