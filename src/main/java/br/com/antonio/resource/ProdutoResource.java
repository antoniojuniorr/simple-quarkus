package br.com.antonio.resource;


import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.antonio.dto.ProdutoDTO;
import br.com.antonio.exception.ProdutoException;
import br.com.antonio.model.Produto;
import br.com.antonio.service.ProdutoService;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class ProdutoResource {
	
	@Inject
	ProdutoService produtoService;
	
	@GET
	public List<Produto> buscarTodosProdutos() {
		return produtoService.buscarTodosProdutos();
	}
	
	@POST
	@Transactional
	public void salvarProduto(ProdutoDTO produtoDTO) {
		produtoService.salvarProduto(produtoDTO);
	}
	
	@PUT
	@Path("{id}")
	@Transactional
	public void atualizaProduto(@PathParam("id") Long id, ProdutoDTO produtoDTO) throws ProdutoException {
		produtoService.atualizaProduto(id, produtoDTO);
	}
	
	@DELETE
	@Path("{id}")
	@Transactional
	public void removerProduto(@PathParam("id") Long id) {
		produtoService.removerProduto(id);
	}
	
}
