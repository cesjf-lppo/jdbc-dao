package br.cesjf.lppo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstabelecimentoDAOJDBC {

    public List<Estabelecimento> listaTodos() throws Exception {
        List<Estabelecimento> todos = new ArrayList<>();
        try {
            Connection conexao = ConexaoJDBC.getInstance();
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
            Logger.getLogger(EstabelecimentoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }

        return todos;
    }

    public void criar(Estabelecimento novoEstab) throws Exception {
        try {
            System.out.println("Antes de criar:" + novoEstab);
            Connection conexao = ConexaoJDBC.getInstance();
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate(
                    String.format(
                            "INSERT INTO estabelecimento(nome, endereco) VALUES('%s','%s')",
                            novoEstab.getNome(),
                            novoEstab.getEndereco()
                    ),
                    new String[]{"id"}
            );
            ResultSet keys = operacao.getGeneratedKeys();
            if (keys.next()) {
                novoEstab.setId(keys.getLong(1));
            }
            System.out.println("Depois de criar:" + novoEstab);
        } catch (SQLException ex) {
            Logger.getLogger(EstabelecimentoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public void excluirPorId(Long id) throws Exception {
        try {
            Connection conexao = ConexaoJDBC.getInstance();
            Statement operacao = conexao.createStatement();
            operacao.executeUpdate("DELETE FROM estabelecimento WHERE id=" + id);
        } catch (SQLException ex) {
            Logger.getLogger(EstabelecimentoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }

    }

    public void excluir(Estabelecimento estab) throws Exception {
        excluirPorId(estab.getId());
    }

    public void salvar(Estabelecimento estab) throws Exception {
        Connection conexao = ConexaoJDBC.getInstance();
        Statement operacao = conexao.createStatement();
        try {
            operacao.executeUpdate(String.format("UPDATE estabelecimento SET nome='%s', endereco='%s', votos=%d WHERE id=%d", estab.getNome(), estab.getEndereco(), estab.getVotos(), estab.getId()));
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public Estabelecimento buscaPorId(Long id) throws Exception {
        Estabelecimento estab = null;
        try {
            Connection conexao = ConexaoJDBC.getInstance();
            Statement operacao = conexao.createStatement();
            ResultSet resultado = operacao.executeQuery(String.format("SELECT * FROM estabelecimento WHERE id=%d", id));
            if (resultado.next()) {
                estab = new Estabelecimento();
                estab.setId(resultado.getLong("id"));
                estab.setNome(resultado.getString("nome"));
                estab.setEndereco(resultado.getString("endereco"));
                estab.setVotos(resultado.getInt("votos"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EstabelecimentoDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
        return estab;
    }
    
    
    
}
