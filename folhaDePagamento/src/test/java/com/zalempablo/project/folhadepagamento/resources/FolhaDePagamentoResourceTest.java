package com.zalempablo.project.folhadepagamento.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.zalempablo.project.folhadepagamento.entities.FolhaDePagamento;
import com.zalempablo.project.folhadepagamento.service.FolhaDePagamentoService;

@SpringBootTest
public class FolhaDePagamentoResourceTest {

    @Autowired
    private FolhaDePagamentoResource resource;

    @MockBean
    private FolhaDePagamentoService folhaDePagamentoService;

    @Test
    public void testGetPagamento() {
        // Defina comportamentos de mock para folhaDePagamentoService e TrabalhadorFeignClient
        when(folhaDePagamentoService.getPagamento(anyLong(), anyInt()))
            .thenReturn(new FolhaDePagamento("Nome do Trabalhador", 100.0, 10));

        // Realize chamada ao endpoint GET /folhadepagamentos/{trabalhador}/dias/{dias} e verifique a resposta.
        ResponseEntity<FolhaDePagamento> response = resource.getPagamento(1L, 10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Nome do Trabalhador", response.getBody().getNome());
        assertEquals(100.0, response.getBody().getGanhaPorDia(), 0.01);
        assertEquals(10, response.getBody().getDiasTrablhados());
    }

    @Test
    public void testGetPagamentoFallback() {
        // Simule um cenário de fallback
        doThrow(new RuntimeException("Tempo limite de Hystrix excedido"))
            .when(folhaDePagamentoService)
            .getPagamento(anyLong(), anyInt());

        // Realize chamada ao endpoint GET /folhadepagamentos/{trabalhador}/dias/{dias} e verifique a resposta de fallback.
        ResponseEntity<FolhaDePagamento> response = resource.getPagamento(1L, 10);
        assertEquals(HttpStatus.OK, response.getStatusCode()); // O fallback deve retornar HttpStatus.OK
        assertEquals("Brann", response.getBody().getNome());
        assertEquals(400.0, response.getBody().getGanhaPorDia(), 0.01);
        assertEquals(10, response.getBody().getDiasTrablhados());
    }

    @Test
    @Disabled("Teste desativado devido a um problema conhecido que está sendo corrigido.")
    public void testGetPagamentoClientError() {
        // Simule um erro 4xx (cliente) ao chamar folhaDePagamentoService
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpClientErrorException exception = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Requisição inválida", headers, null, null);

        when(folhaDePagamentoService.getPagamento(anyLong(), anyInt()))
            .thenThrow(exception);

        // Realize chamada ao endpoint GET /folhadepagamentos/{trabalhador}/dias/{dias} e verifique a resposta de erro 4xx.
        ResponseEntity<FolhaDePagamento> response = resource.getPagamento(1L, 10);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Disabled("Teste desativado devido a um problema conhecido que está sendo corrigido.")
    public void testGetPagamentoServerError() {
        // Simule um erro 5xx (servidor) ao chamar folhaDePagamentoService
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpServerErrorException exception = new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro no servidor", headers, null, null);

        when(folhaDePagamentoService.getPagamento(anyLong(), anyInt()))
            .thenThrow(exception);

        // Realize chamada ao endpoint GET /folhadepagamentos/{trabalhador}/dias/{dias} e verifique a resposta de erro 5xx.
        ResponseEntity<FolhaDePagamento> response = resource.getPagamento(1L, 10);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
