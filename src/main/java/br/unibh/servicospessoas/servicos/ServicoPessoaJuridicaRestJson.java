package br.unibh.servicospessoas.servicos;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unibh.servicospessoas.entidades.PessoaJuridica;
import br.unibh.servicospessoas.persistencia.PessoaJuridicaDAO;

//The Java class will be hosted at the URI path "/pessoajuridica"

@Path("/pessoajuridica")
public class ServicoPessoaJuridicaRestJson 
{

    @GET         
    @Produces(MediaType.APPLICATION_JSON)    
    @Path("/listar")
    public List<PessoaJuridica>listar()
    {
        PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
        return dao.findAll();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public PessoaJuridica find(@PathParam("id") int id)
    {
    	PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
    	return dao.find(new Long(id));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/salvar")
    public PessoaJuridica insert(PessoaJuridica p)
    {
    	System.out.println("Entrou");
    	PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
    	dao.insert(p);
    	return dao.findCnpj(p.getCnpj());
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public PessoaJuridica update(@PathParam("id") int id, PessoaJuridica p)
    {
    	PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
    	p.setId(new Long(id));
    	dao.update(p);
    	return dao.find(new Long(id));
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void excluir(@PathParam("id") int id)
    {
    	PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
    	PessoaJuridica p = new PessoaJuridica();
    	p.setId(new Long(id));
    	dao.delete(p);
    	
    	System.out.println("Removido com sucesso!");
    }
    
}
