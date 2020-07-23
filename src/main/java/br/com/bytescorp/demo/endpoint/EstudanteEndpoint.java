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

package br.com.bytescorp.demo.endpoint;


import br.com.bytescorp.demo.error.RecursoNaoEncontradoException;
import br.com.bytescorp.demo.model.Estudante;
import br.com.bytescorp.demo.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("v1")
public class EstudanteEndpoint {
    private final EstudanteRepository estudanteDAO;

    @Autowired
    public EstudanteEndpoint(EstudanteRepository estudanteDAO) {
        this.estudanteDAO = estudanteDAO;
    }

    @GetMapping(path = "usuario/estudantes")
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(estudanteDAO.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "usuario/estudantes/{id}")
    public ResponseEntity<?> obterEstudantePorId(@PathVariable Long id) {
        verificarSeExisteEstudante(id);
        return new ResponseEntity<>(estudanteDAO.existsById(id),HttpStatus.OK);
    }

    @GetMapping(path = "usuario/estudantes/obterEstudantePorNome/{nome}")
    public ResponseEntity<?> obterEstudantePorNome(@PathVariable String nome) {
        final Estudante byNomeContains = estudanteDAO.findEstudanteByNome(nome);
        return new ResponseEntity<>(byNomeContains, (byNomeContains != null ? HttpStatus.OK:HttpStatus.NOT_FOUND));
    }

    @PostMapping(path = "admin/estudantes")
    @Transactional
    public ResponseEntity<?> salvar(@RequestBody Estudante estudante) {
        return new ResponseEntity<>(estudanteDAO.save(estudante), HttpStatus.OK);
    }

    @DeleteMapping(path = "admin/estudantes/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> excluir(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails);
        verificarSeExisteEstudante(id);
        estudanteDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "admin/estudantes")
    public ResponseEntity<?> atualizar(@Valid @RequestBody Estudante estudante) {
        verificarSeExisteEstudante(estudante.getId());
        return new ResponseEntity<>(estudanteDAO.save(estudante), HttpStatus.OK);
    }

    private void verificarSeExisteEstudante(Long id) {
        if (!estudanteDAO.findById(id).isPresent())
            throw new RecursoNaoEncontradoException("Estudante não encontrado para id: " + id);
    }
}
