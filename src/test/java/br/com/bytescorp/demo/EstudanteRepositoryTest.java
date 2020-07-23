/*
 * Copyright (c) 2020 BytesCorp Software
 * Todos os direitos reservados
 *
 * Este produto está protegido por direitos autorais e distribuído sob
 * licenças que restringem a cópia, distribuição e descompilação.
 * @author Eden Alencar
 * @since 1.0
 *
 */

package br.com.bytescorp.demo;

import br.com.bytescorp.demo.model.Estudante;
import br.com.bytescorp.demo.repository.EstudanteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)   //<- ativa testes usando o próprio bd
public class EstudanteRepositoryTest {
    @Autowired
    EstudanteRepository estudanteRepository;

    @Test
    public void whenCreate_thenPersistData() {

        Estudante student = new Estudante("Teste jUnit");
        this.estudanteRepository.save(student);

        assertThat(student.getId()).isNotNull();
        assertThat(student.getNome()).isEqualTo("Teste jUnit");
//        assertThat(student.getEmail()).isEqualTo("marcos.teste@email.com");

    }

    @Test
    public void whenDelete_thenRemoveData() {
        Estudante student = new Estudante("Éden");
        this.estudanteRepository.save(student);
        estudanteRepository.delete(student);
        assertThat(estudanteRepository.findById(student.getId())).isEmpty();

    }

    @Test
    public void whenUpdate_thenChangeAndPersistData() {
        Estudante student = new Estudante("Éden");
        this.estudanteRepository.save(student);

        student = new Estudante("Éden Dois");
        this.estudanteRepository.save(student);

        student = estudanteRepository.findById(student.getId()).orElse(null);

        assertThat(student.getNome()).isEqualTo("Éden Dois");
//        assertThat(student.getEmail()).isEqualTo("marcos.testedois@email.com");
    }

    @Test
    public void whenFindByNameIgnoreCaseContaining_thenIgnoreCase() {
        Estudante student1 = new Estudante("Eden");
        Estudante student2 = new Estudante("eden");

        this.estudanteRepository.save(student1);
        this.estudanteRepository.save(student2);

        List<Estudante> studentList = estudanteRepository.findByNomeContains("eden");

        assertThat(studentList.size()).isEqualTo(1);

    }

//    @Test
//    public void whenNotEmptyName_thenNoConstraintViolations() {
//        Exception exception = assertThrows(
//                ConstraintViolationException.class,
//                () -> studentRepository.save(new Student("", "email@gmail.com")));
//
//        assertTrue(exception.getMessage().contains("O campo nome do estudante é obrigatório"));
//    }
//
//    @Test
//    public void whenNotEmptyEmail_thenNoConstraintViolations() {
//        Exception exception = assertThrows(
//                ConstraintViolationException.class,
//                () -> studentRepository.save(new Student("Marcos", "")));
//
//        assertTrue(exception.getMessage().contains("O campo email é obrigatório"));
//    }
//
//    @Test
//    public void whenValidEmail_thenNoConstraintViolations() {
//        Exception exception = assertThrows(
//                ConstraintViolationException.class,
//                () -> studentRepository.save(new Student("Marcos", "wrongemail.email")));
//
//        assertTrue(exception.getMessage().contains("O email deve ser válido"));
//    }
}
