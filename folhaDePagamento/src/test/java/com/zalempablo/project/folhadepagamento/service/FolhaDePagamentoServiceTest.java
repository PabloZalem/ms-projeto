package com.zalempablo.project.folhadepagamento.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.ResponseEntity;

import com.zalempablo.project.folhadepagamento.entities.FolhaDePagamento;
import com.zalempablo.project.folhadepagamento.entities.Trabalhador;
import com.zalempablo.project.folhadepagamento.feingclient.TrabalhadorFeignClient;

public class FolhaDePagamentoServiceTest {

    @InjectMocks
    private FolhaDePagamentoService folhaDePagamentoService;

    @Spy
    private TrabalhadorFeignClient trabalhadorFeignClient;

    @Test
    public void testGetPagamento() {
        // Configurar anotações mock diretamente no método de teste
        MockitoAnnotations.initMocks(this);

        // Defina o comportamento real do método findById
        Trabalhador trabalhadorReal = new Trabalhador(/* dados reais */);
        Mockito.doReturn(ResponseEntity.ok(trabalhadorReal)).when(trabalhadorFeignClient).findById(1L);

        // Chame o método a ser testado
        FolhaDePagamento folhaDePagamento = folhaDePagamentoService.getPagamento(1L, 10); // Supondo que o ID do trabalhador seja 1 e 10 dias

        // Verifique se o resultado é o esperado
        assertEquals(null, folhaDePagamento.getNome());
        //assertEquals(null, folhaDePagamento.getGanhaPorDia(), 0.001); // Use um delta para comparar valores de ponto flutuante
        // Verifique outras propriedades da FolhaDePagamento, se necessário
    }
}
