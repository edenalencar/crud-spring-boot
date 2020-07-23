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

package br.com.bytescorp.demo.dao;

import br.com.bytescorp.demo.GenericDao;
import br.com.bytescorp.demo.handler.RestResponseExceptionHandler;
import br.com.bytescorp.demo.model.AbstractEntity;
import br.com.bytescorp.demo.model.Estudante;
import br.com.bytescorp.demo.model.RestResponsePage;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

public class EstudantesDAO implements GenericDao {

    /**
     * Acesso como usuário
     */
    private final RestTemplate restTemplateUsuario;

    /**
     * Acesso como admin
     */
    private final RestTemplate restTemplateAdmin;


    public EstudantesDAO() {
        restTemplateUsuario = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/usuario/estudantes")
                .basicAuthentication("eden.alencar", "192325ED$")
                .errorHandler(new RestResponseExceptionHandler())//Linha que define a classe de error padrao, para tratar erros sem precisar colocar try catch em todos os metodos
                .build();
        restTemplateAdmin = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/admin/estudantes")
                .errorHandler(new RestResponseExceptionHandler())
                .basicAuthentication("eden.alencar", "192325ED$")
                .build();
    }

    private static HttpHeaders criarJSONHearder() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     * Exemplo de como chamar endpoint rest GET retornando um object
     * {id} url final
     * Estudante.class Objeto esperado pelo retorno
     * 9 parametro passado como id
     */
//    public Estudante obterEstudantePorId(Long id) {
//        Estudante estudanteUsuario = restTemplateUsuario.getForObject("/{id}", Estudante.class, 9);
//        return  estudanteUsuario;
//    }

//    public Estudante salvar(@RequestBody Estudante estudante) {
//        Estudante estudanteCriado = restTemplateAdmin.postForObject("/", estudantePost,Estudante.class);
//        return  estudanteAdminObject;
//    }

//    public ResponseEntity<Estudante> salvar(@RequestBody Estudante estudante) {
//        ResponseEntity<Estudante> estudanteCriado = restTemplateAdmin.postForEntity("/", estudante, Estudante.class);
//        return  estudanteCriado;
//    }





    //Resposta usando uma Lista ReponseEntity sem o Pageable
//     public ResponseEntity<?> listarTudo() {
//      ResponseEntity<List<Estudante>> listaEstudantes = restTemplateUsuario.exchange("/", HttpMethod.GET, null, new ParameterizedTypeReference<List<Estudante>>() {
//    });
//         return listaEstudantes;
//     }
//    Resposta usando uma Lista ReponseEntity com Pageable


    @Override
    public ResponseEntity<?> criar(AbstractEntity abstractEntity) {
        ResponseEntity<Estudante> estudanteCriado = restTemplateAdmin.exchange("/", HttpMethod.POST, new HttpEntity<>(abstractEntity, criarJSONHearder()), Estudante.class);
        return estudanteCriado;
    }

    @Override
    public ResponseEntity<?> obterTodos() {
        ResponseEntity<RestResponsePage<Estudante>> listaEstudantes = restTemplateUsuario.exchange("/?sort=nome,desc", HttpMethod.GET, null, new ParameterizedTypeReference<RestResponsePage<Estudante>>() {
        });
        return listaEstudantes;
    }

    @Override
    public ResponseEntity<?> obterPorId(Long id) {
        ResponseEntity<Estudante> estudanteResponseEntity = restTemplateUsuario.getForEntity("/{id}", Estudante.class, 9);
        return estudanteResponseEntity;
    }

    @Override
    public ResponseEntity<?> obterPorNome(String nome) {
        ResponseEntity<Estudante> estudanteResponseEntity = restTemplateUsuario.getForEntity("/obterEstudantePorNome/{nome}", Estudante.class, "Liony Gomes Vieira");
        return estudanteResponseEntity;
    }

    @Override
    public void salvar(Estudante estudante) {
        restTemplateAdmin.put("/",estudante);
    }

    @Override
    public void excluir(long id) {
        restTemplateAdmin.delete("/{id}",id);
    }
}
