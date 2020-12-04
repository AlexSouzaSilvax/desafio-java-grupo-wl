
import Usuario.UsuarioService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alex
 */
public class VerificaDplCpfTeste {

    public static void main(String[] args) {
        UsuarioService uService = new UsuarioService();
        System.out.println(uService.verificaDplCpf("000.000.000-00"));
    }

}
