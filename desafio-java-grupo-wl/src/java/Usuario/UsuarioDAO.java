/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrator
 */
public class UsuarioDAO {

    public static List<Usuario> lista() {

        try {
            Connection conexao = Conexao.getConexao();
            List<Usuario> listaUsuario = new ArrayList<>();

            PreparedStatement ps = conexao.prepareStatement("SELECT id, nome, documento, data_nascimento, senha FROM usuario");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Usuario u = new Usuario();

                u.setId(rs.getInt("ID"));
                u.setNome(rs.getString("NOME"));
                u.setDocumento(rs.getString("DOCUMENTO"));
                u.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
                u.setSenha(rs.getString("SENHA"));

                listaUsuario.add(u);

            }

            ps.execute();
            ps.close();

            return listaUsuario;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar DAO " + ex.getMessage());
            return null;
        }
    }

    public Usuario inserir(Usuario usuario) {
        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement ps;

            ps = conexao.prepareStatement("{ call pc_insert_usuario (?, ?, ?, ?) }");

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getDocumento());
            ps.setDate(3, new java.sql.Date(usuario.getDataNascimento().getTime()));
            ps.setString(4, usuario.getSenha());

            ps.execute();
            Conexao.fecharConexao();

            System.out.println("---------------------> Salvo com sucesso.");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", ""));            
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao alterar!", ""));
            System.out.println("Erro ao salvar DAO " + ex.getMessage());
        }
        return usuario;
    }

    public Usuario alterar(Usuario usuario) {

        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement ps;

            ps = conexao.prepareStatement("{ call pc_update_usuario (?, ?, ?, ?) }");

            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getDocumento());
            ps.setDate(4, new java.sql.Date(usuario.getDataNascimento().getTime()));

            ps.execute();
            Conexao.fecharConexao();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!", ""));
            System.out.println("---------------------> Alterado com sucesso.");
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao alterar!", ""));
            System.out.println("Erro ao alterar DAO " + ex.getMessage());
        }
        return usuario;
    }

    public void deletar(int pId) {

        try {
            Connection conexao = Conexao.getConexao();
            PreparedStatement ps;
            ps = conexao.prepareStatement("{ call pc_delete_usuario (?) }");

            ps.setInt(1, pId);

            ps.execute();
            Conexao.fecharConexao();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Deletado com sucesso!", ""));
            System.out.println("---------------------> Alterado com sucesso.");
        } catch (SQLException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao deletar!", ""));
            System.out.println("Erro ao deletar DAO " + ex.getMessage());
        }
    }

}
