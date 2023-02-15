package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
    return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

    }
    @PutMapping
    @Transactional
    public void atualizar (@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();

    }

}
