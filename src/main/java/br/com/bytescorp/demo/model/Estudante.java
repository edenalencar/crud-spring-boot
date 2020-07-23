package br.com.bytescorp.demo.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
public class Estudante extends AbstractEntity{
    @NotEmpty(message = "O campo nome do estudante é obrigatorio")
    private String nome;
    public String getNome() {
        return nome;
    }

    public Estudante() {
    }

    public Estudante(@NotEmpty(message = "O campo nome do estudante é obrigatorio") String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Estudante{" +
                "nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Estudante estudante = (Estudante) o;
        return Objects.equals(nome, estudante.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nome);
    }
}
