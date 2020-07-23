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

import br.com.bytescorp.demo.model.AbstractEntity;
import br.com.bytescorp.demo.model.Estudante;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface GenericDao {
    public ResponseEntity<?> criar(@RequestBody AbstractEntity abstractEntity);
    public ResponseEntity<?> obterTodos();
    public ResponseEntity<?> obterPorId(Long id);
    public ResponseEntity<?> obterPorNome(String nome);
    public void salvar(Estudante estudante);
    public void excluir(long id);


}
