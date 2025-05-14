import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Pessoa {
    protected String nome;
    protected String email;
    protected String cpf;
    protected String telefone;
}
public class Cliente extends Pessoa {
    private LocalDate dataCadastro;
    private ArrayList<listaAnimais> listaAnimais;

}

public class Funcionario extends Pessoa {
    protected String cargo;

}
public class Veterinario extends Funcionario {
    private String nome;
    private String especialidade;
}
public class Recepcionista extends Funcionario {
    private Boolean turno;
    
}