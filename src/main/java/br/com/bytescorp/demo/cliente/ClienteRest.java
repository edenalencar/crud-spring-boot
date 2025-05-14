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

package br.com.bytescorp.demo.cliente;

import br.com.bytescorp.demo.GenericDao;
import br.com.bytescorp.demo.dao.EstudantesDAO;
import br.com.bytescorp.demo.model.Estudante;
import br.com.bytescorp.demo.model.RestResponsePage;

import java.util.List;

public class ClienteRest {
    public static void main(String[] args) {
        Estudante estudante = new Estudante();
        estudante.setNome("Ciclano da Silva");

        GenericDao genericDao = new EstudantesDAO();

        RestResponsePage restResponsePage = (RestResponsePage) genericDao.obterTodos().getBody();
        List<Estudante> estudanteList = restResponsePage.getContent();

        System.out.println("Lista Tudo" + estudanteList +"/n");
        System.out.println("Obtem por id" + genericDao.obterPorId(Long.valueOf(4))+ "/n");
        System.out.println(genericDao.criar(estudante));
        System.out.println("Obter por nome " + genericDao.obterPorNome("Liony Gomes Vieira"));

        estudante.setId(4L);
        estudante.setNome("Fulano de Tal");
        genericDao.salvar(estudante);

        genericDao.excluir(57);

    }

}
