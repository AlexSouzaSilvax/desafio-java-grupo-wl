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

/**
 *
 * @author Administrator
 */
public class UsuarioDAO {
        public static List<Usuario> lista() {

        try {
            Connection conexao = Conexao.getConexao();
            List<Usuario> listaUsuario = new ArrayList<>();

            PreparedStatement ps = conexao.prepareStatement("SELECT id, nome, documento, data_nascimento, senha FROM usuarios");

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
            System.out.println("Listou");

            return listaUsuario;
        } catch (SQLException ex) {
            System.out.println("Erro ao listar DAO " + ex.getMessage());
            return null;
        }
    }
}
