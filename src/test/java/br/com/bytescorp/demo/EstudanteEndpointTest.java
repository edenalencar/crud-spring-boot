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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//Server para gerar uma porta aleatoria e não avacalhar se estiver uma instancia padrao funcionando
@AutoConfigureMockMvc
public class EstudanteEndpointTest {
    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int porta;
    @MockBean
    private EstudanteRepository estudanteRepository;
    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static  class Config{
        @Bean
        public RestTemplateBuilder restTemplateBuilder(){
            return new RestTemplateBuilder().basicAuthentication("eden.alencar","192325ED$");
        }
    }
    @BeforeTestMethod
    public void setup(){
        Estudante estudante = new Estudante("Éden rico");
        Optional estudanteOptional = Optional.of(estudante);
        when(estudanteRepository.findById(30L)).thenReturn(estudanteOptional);
    }
    @Test
    public void listarEstudanteQuandoUsuarioESenhaEstivremIncorretosRetornandoStatusCode401(){
        System.out.println(porta);
        testRestTemplate = testRestTemplate.withBasicAuth("1","1");
        final ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/v1/usuario/estudantes", String.class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(401);
    }
    @Test
    public void obterEstudanteQuandoUsuarioESenhaEstivremIncorretosRetornandoStatusCode401(){
        testRestTemplate = testRestTemplate.withBasicAuth("1","1");
        final ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/v1/usuario/estudantes/{id}", String.class,30L);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(401);
    }
    @Test
    public void listarEstudanteQuandoUsuarioESenhaEstivremCorretosRetornandoStatusCode200(){
        final List<Estudante> estudante = asList(new Estudante("Éden Alencar"), new Estudante("Éden Alencar 2"));
        when(estudanteRepository.findAll()).thenReturn(estudante);
        final ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/v1/usuario/estudantes/", String.class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    public void obterEstudanteQuandoUsuarioESenhaEstivremCorretosRetornandoStatusCode200(){
        final ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:8080/v1/usuario/estudantes/{id}", String.class,15L);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    public void obterEstudanteQuandoUsuarioESenhaEstivremCorretosEEstundateNaoEncontradoRetornandoStatusCode404(){
        final ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:8080/v1/usuario/estudantes/obterEstudantePorNome/{nome}", String.class,"Doninha");
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
    }
    @Test
    @WithMockUser(username = "", password = "", roles = {"ADMIN"})
    public void excluirQuandoUsuarioPossuirRoleAdminEEstudanteExistirRetornandoStatusCode200() throws Exception {
        Estudante estudante = new Estudante("Ciclano da Silva");
        estudante.setId(82L);

        when(estudanteRepository.findById(82L)).thenReturn(java.util.Optional.of(estudante));
        BDDMockito.doNothing().when(estudanteRepository).deleteById(82L);

        mockMvc.perform(delete("http://localhost:8080/v1/admin/estudantes/{id}",82L))
                .andExpect(status().isOk())
                .andDo(print());

        verify(estudanteRepository).deleteById(82L);

//        ResponseEntity<String> responseEntity = testRestTemplate.exchange("http://localhost:8080/v1/admin/estudantes/{id}", HttpMethod.DELETE, null, String.class, 84L);
//        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
}
