public class Animal {
    private String nome;
    private String especie;
    private int idade;
    private String raca;
    private String sexo;
    private double peso;
    private Cliente dono;

    public Animal(String nome, String especie, int idade, String raca, String sexo, double peso, Cliente dono) {
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
        this.raca = raca;
        this.sexo = sexo;
        this.peso = peso;
        this.dono = dono;
    }

    public void exibirFicha() {
        System.out.println("Animal: " + nome + ", Espécie: " + especie + ", Raça: " + raca + ", Sexo: " + sexo + ", Peso: " + peso + "kg");
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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }
}
