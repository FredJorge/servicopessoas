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

import br.unibh.servicospessoas.entidades.Cep;
import br.unibh.servicospessoas.persistencia.CepDAO;

@Path("/cep")
public class ServicoCepRestJson {


    @GET         
    @Produces(MediaType.APPLICATION_JSON)    
    @Path("/listar")
    public List<Cep>listar()
    {
        CepDAO dao = new CepDAO();
        return dao.findAll();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar/{cep}")
    public Cep find(@PathParam("cep") int cep)
    {
    	CepDAO dao = new CepDAO();
    	return dao.find(new Long(cep));
    }
    
    @GET         
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("text/plain")
    @Path("/{endereco}")
    public List<Cep>listar(@PathParam("endereco") String endereco)
    {
        CepDAO dao = new CepDAO();
        return dao.findCep(endereco);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/salvar")
    public Cep insert(Cep p)
    {
    	System.out.println("Entrou");
    	CepDAO dao = new CepDAO();
    	dao.insert(p);
    	return dao.find(p.getCep());
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{cep}")
    public Cep update(@PathParam("cep") int cep, Cep p)
    {
    	CepDAO dao = new CepDAO();
    	p.setCep(new Long(cep));
    	dao.update(p);
    	return dao.find(new Long(cep));
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{cep}")
    public void excluir(@PathParam("cep") int cep)
    {
    	CepDAO dao = new CepDAO();
    	Cep p = new Cep();
    	p.setCep(new Long(cep));
    	dao.delete(p);
    	
    	System.out.println("Removido com sucesso!");
    }
	
}
