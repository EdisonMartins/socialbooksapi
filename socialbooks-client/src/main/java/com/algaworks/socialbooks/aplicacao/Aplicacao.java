package com.algaworks.socialbooks.aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.algaworks.socialbooks.client.LivrosClient;
import com.algaworks.socialbooks.client.domain.Livro;

public class Aplicacao {
	public static void main(String[] args) throws ParseException {
		LivrosClient cliente = new LivrosClient("http://localhost:8080", "algaworks", "s3nh4");
		List<Livro> livros = cliente.listar();
		
		for (Livro livro : livros) {
			System.out.println("Livro: " + livro.getNome());
		}
		
		Livro livro = new Livro();
		livro.setNome("Git passo-a-passo-2");
		livro.setEditora("AlgaWorks");
		SimpleDateFormat publicacao = new SimpleDateFormat("dd/MM/yyyy");
		livro.setPublicacao(publicacao.parse("01/01/2016"));
		livro.setResumo("este livro aborda tecnicas de desenvolvimento de APIs.");
		String localizacao = cliente.salvar(livro);
		System.out.println("URI do livro salvo: " + localizacao);
		Livro livroBuscado = cliente.buscar(localizacao);
		System.out.println("Livro buscado: " + livroBuscado.getNome());
	}

}
