import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Pessoa {
    protected String nome;
    protected String email;
    protected String cpf;
    protected String telefone;
}
class Cliente extends Pessoa {
    private LocalDate dataCadastro;
    private ArrayList<listaAnimais> listaAnimais;

}

class Funcionario extends Pessoa {
    protected String cargo;

}
class Veterinario extends Funcionario {
    private String nome;
    private String especialidade;
}
class Recepcionista extends Funcionario {
    private Boolean turno;

}