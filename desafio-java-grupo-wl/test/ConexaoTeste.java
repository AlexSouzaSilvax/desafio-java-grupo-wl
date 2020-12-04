/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Util.Conexao;
import java.sql.Connection;

/**
 *
 * @author Administrator
 */
public class ConexaoTeste {

    public static void main(String[] args) {
        Connection conexao = Conexao.getConexao();
        System.out.println(conexao);
    }
}
