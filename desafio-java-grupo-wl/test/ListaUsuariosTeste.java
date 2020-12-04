
import Usuario.Usuario;
import Usuario.UsuarioService;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alex
 */
public class ListaUsuariosTeste {

    public static void main(String[] args) {

        UsuarioService usuarioService = new UsuarioService();

        List<Usuario> lista = usuarioService.lista();

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(
                    "ID: " + lista.get(i).getId()
                    + " NOME: " + lista.get(i).getNome()
                    + " DOCUMENTO: " + lista.get(i).getDocumento()
                    + " DATA_NASCIMENTO: " + lista.get(i).getDataNascimento()
                    + " SENHA: " + lista.get(i).getSenha()
            );
        }

    }
}
