package br.com.antonio.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;

import br.com.antonio.dto.ProdutoDTO;
import br.com.antonio.exception.ProdutoException;
import br.com.antonio.model.Produto;

@ApplicationScoped
public class ProdutoService {
	
	public List<Produto> buscarTodosProdutos() {
		return Produto.listAll();
	}
	
	public void salvarProduto(ProdutoDTO produtoDTO) {
		Produto produto = Produto.builder().nome(produtoDTO.getNome()).valor(produtoDTO.getValor()).build();
		produto.persist();
	}
	
	public void atualizaProduto(@PathParam("id") Long id, ProdutoDTO produtoDTO) throws ProdutoException {
		Optional<Produto> produtoOp = Produto.findByIdOptional(id);
		
		if(produtoOp.isPresent()) {
			Produto produto = (Produto) produtoOp.get();
			produto.setNome(produtoDTO.getNome());
			produto.setValor(produtoDTO.getValor());
			produto.persist();
					
		} else {
			throw new ProdutoException("Produto não encontrado");
		}
	}
	
	public void removerProduto(@PathParam("id") Long id) {
		Optional<Produto> produtoOp = Produto.findByIdOptional(id);
		
		produtoOp.ifPresentOrElse(Produto::delete, () -> {
			throw new NotFoundException();
		});
	}

}
