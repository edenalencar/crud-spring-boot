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

package br.com.bytescorp.demo.repository;

import br.com.bytescorp.demo.model.Estudante;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EstudanteRepository extends PagingAndSortingRepository<Estudante, Long> {
    List<Estudante> findByNomeContains(String nome);
    Estudante findEstudanteByNome(String nome);
}
