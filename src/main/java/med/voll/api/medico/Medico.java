package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
//A partir disso, a JPA estabelecerá a ligação entre a entidade e uma tabela de mesmo nome no banco de dados
@Entity(name = "Medico")
//Essa anotação e responsável por criar o getter de cada campo em que ela estiver denotada
@Getter
//Essa anotação é responsável por gerar um construtor sem parâmetros, vale ressaltar que se houver campos finais
// em sua classe deve usar um atributo force = true em sua anotação
@NoArgsConstructor
//Essa anotação é responsável por gerar um construtor com 1 parâmetro para cada atributo de sua classe
@AllArgsConstructor
//A anotação permite evitar a necessidade de escrever manualmente os métodos equals() e hashcode() nas classes Java
@EqualsAndHashCode(of = "id")
public class Medico {

    // é utilizada para informar ao JPA qual campo/atributo de uma entidade estará relacionado à chave primária
    // da respectiva tabela no banco de dados
    @Id
    // é utilizada para informar que a geração do valor do identificador único da entidade será gerenciada pelo
    // provedor de persistência. Essa anotação deve ser adicionada logo após a anotação @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    private String nome;
    private String email;

    private String telefone;
    private String crm;
    //Especifica que uma propriedade ou campo persistente deve ser mantido como um tipo enume
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;


    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());


    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
        this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());

        }
    }

    public void excluir() {
        this.ativo = false;

    }
}
