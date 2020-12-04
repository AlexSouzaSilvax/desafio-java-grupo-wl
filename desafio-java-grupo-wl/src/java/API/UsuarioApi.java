/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import Usuario.Usuario;
import Usuario.UsuarioService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author alex
 */
@Path("/usuario")
public class UsuarioApi {

    @Context
    private UriInfo context;

    UsuarioService usuarioService = new UsuarioService();
    Usuario usuario = new Usuario();

    @GET
    @Produces("application/json")
    @Path("/listar")
    public Response listar() {
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
        return Response.status(200).entity(gson.toJson(usuarioService.lista())).header("result", "true").header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Produces("application/json")
    @Path("/novo")
    public Response inserir(String pJson) {
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HH:mm:ss").create();
        usuario = (Usuario) gson.fromJson(pJson, Usuario.class);
        usuarioService.salvar(usuario);
        return Response.status(200).header("result", "true").header("msg", "Usuário adicionado com sucesso").header("Access-Control-Allow-Origin", "*").build();
    }

    @GET
    @Produces("application/json")
    @Path("/remover")
    public Response remover(@HeaderParam("id") int id) {
        usuarioService.deletar(id);
        return Response.status(200).header("result", "true").header("msg", "Usuário removido com sucesso").header("Access-Control-Allow-Origin", "*").build();
    }

}
