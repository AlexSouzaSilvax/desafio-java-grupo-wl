
import Usuario.Usuario;
import Usuario.UsuarioService;
import java.util.Date;
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
public class InsertUsuarioTeste {
    
    public static void main(String[] args) {
        
        UsuarioService usuarioService = new UsuarioService();
        Usuario usuario = new Usuario();
        
        usuario.setNome("Apenas um teste");
        usuario.setDocumento("000.000.000-00");
        usuario.setDataNascimento(new Date());
        usuario.setSenha("123456789");
        
        usuarioService.salvar(usuario);
        
    }
}
