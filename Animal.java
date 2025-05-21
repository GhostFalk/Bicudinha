public class Animal {
    private String nome;
    private String especie;
    private int idade;
    private String sexo;
    private double peso;
    private Cliente dono;

    public Animal(String nome, String especie, int idade, String sexo, double peso, Cliente dono) {
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
        this.sexo = sexo;
        this.peso = peso;
        this.dono = dono;
    }

    public void exibirFicha() {
        System.out.println("Animal: " + nome + ", Espécie: " + especie + ", Sexo: " + sexo + ", Peso: " + peso + "kg");
    }

    public void vacinar() {
        System.out.println(nome + " foi vacinado.");
    }

    public void atualizarPeso(double novoPeso) {
        this.peso = novoPeso;
    }

    public String getNome() {
        return nome;
    }
}