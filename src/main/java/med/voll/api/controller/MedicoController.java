package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));

    }
    @GetMapping
    public List <DadosListagemMedico> listar(){
    return repository.findAll().stream().map(DadosListagemMedico::new).toList();

    }

}
