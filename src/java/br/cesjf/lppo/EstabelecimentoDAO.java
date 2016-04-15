package br.cesjf.lppo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstabelecimentoDAO {

    List<Estabelecimento> listaTodos() {
        List<Estabelecimento> todos = new ArrayList<>();
        try {
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2016-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery("SELECT * FROM estabelecimento");
            while (resultado.next()) {
                Estabelecimento estab = new Estabelecimento();
                estab.setId(resultado.getLong("id"));
                estab.setNome(resultado.getString("nome"));
                estab.setEndereco(resultado.getString("endereco"));
                estab.setVotos(resultado.getInt("votos"));
                todos.add(estab);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EstabelecimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return todos;
    }

    void criar(Estabelecimento novoEstab) {
        try {
            Connection conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/lppo-2016-1", "usuario", "senha");
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate(String.format("INSERT INTO estabelecimento(nome, endereco) VALUES('%s','%s')", novoEstab.getNome(), novoEstab.getEndereco()));

        } catch (SQLException ex) {
            Logger.getLogger(EstabelecimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
