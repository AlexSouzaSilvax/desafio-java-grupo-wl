/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import Util.Criptografia;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class UsuarioController {

    Usuario usuario = new Usuario();
    UsuarioService usuarioService = new UsuarioService();
    List<Usuario> listaUsuario = new ArrayList<>();

    private Integer tela = 0;

    public UsuarioController() {
        lista();
    }

    public void lista() {
        listaUsuario = usuarioService.lista();
    }

    public void novo() {
        usuario = new Usuario();
        tela = 1;
    }

    public void seleciona(Usuario pUsuario) {
        usuario = pUsuario;
        tela = 1;
    }

    public void salvar() {
        if (!validacoes()) {
            if (usuario.getId() == null) {
                usuario.setSenha(gerarSenha());
            }
            usuarioService.salvar(usuario);
            voltar();
        }
    }

    public String gerarSenha() {
        // 1º 3 digitos do cpf
        String param1 = usuario.getDocumento().substring(0, 3);

        // Mes data de nascimento
        int mesNasc = (int) (usuario.getDataNascimento().getMonth() + 1);
        String param2 = "";
        if (String.valueOf(mesNasc).length() == 1) {
            param2 = "0" + mesNasc;
        }

        return Criptografia.criptografarSHA1(param1 + param2);
    }

    public boolean validacoes() {
        //validando se tem numeros na string
        char c;
        boolean result = false;
        for (int i = 0; i < usuario.getNome().length(); i++) {
            c = usuario.getNome().charAt(i);
            result = String.valueOf(c).matches("[0-9]*");
            //System.out.println("É número? " + result);
        }
        if (result) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "NOME está inválido", ""));
            //System.out.println("NOME inválido");
            return true;
        }

        //verificando duplicidade de cpf
        if (usuario.getId() == null) { //validação apenas para cadastro novo
            if (verificaDplCpf(usuario.getDocumento())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF já foi cadastrado", ""));
                //System.out.println("CPF já foi cadastrado");
                return true;
            }
        }

        //nao permitir dataNascimento ser maior que data atual e que seja menor que 01/01/1900       
        if (usuario.getDataNascimento().after(new Date()) || usuario.getDataNascimento().before(new GregorianCalendar(1900, Calendar.JANUARY, 1, 0, 0, 0).getTime())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data de Nascimento inválida", ""));
            //System.out.println("Data de Nascimento inválida");
            return true;
        }

        return false;
    }

    public void deletar() {
        usuarioService.deletar(usuario.getId());
        voltar();
    }

    public void voltar() {
        lista();
        tela = 0;
    }

    public boolean verificaDplCpf(String cpf) {
        return usuarioService.verificaDplCpf(cpf);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Integer getTela() {
        return tela;
    }

    public void setTela(Integer tela) {
        this.tela = tela;
    }

}
