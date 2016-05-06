package br.cesjf.lppo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstabelecimentoDAOJDBCPrep {
        private PreparedStatement operacaoListarTodos;
        private PreparedStatement operacaoCriar;
        private PreparedStatement operacaoExcluirPorId;

        
    public EstabelecimentoDAOJDBCPrep() throws Exception {
            try {
                operacaoListarTodos = ConexaoJDBC.getInstance().prepareStatement("SELECT * FROM estabelecimento");
                operacaoCriar = ConexaoJDBC.getInstance().prepareStatement("INSERT INTO estabelecimento(nome, endereco) VALUES(?, ?)", new String[]{"id"});
                operacaoExcluirPorId = operacaoExcluirPorId = ConexaoJDBC.getInstance().prepareStatement("DELETE FROM estabelecimento WHERE id=?");

            } catch (SQLException ex) {
                Logger.getLogger(EstabelecimentoDAOJDBCPrep.class.getName()).log(Level.SEVERE, null, ex);
                throw new Exception(ex);
            }
    }
        
        
    public List<Estabelecimento> listaTodos() throws Exception {
        List<Estabelecimento> todos = new ArrayList<>();
        try {
            ResultSet resultado = operacaoListarTodos.executeQuery();
            while (resultado.next()) {
                Estabelecimento estab = new Estabelecimento();
                estab.setId(resultado.getLong("id"));
                estab.setNome(resultado.getString("nome"));
                estab.setEndereco(resultado.getString("endereco"));
                estab.setVotos(resultado.getInt("votos"));
                todos.add(estab);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EstabelecimentoDAOJDBCPrep.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }

        return todos;
    }

    public void criar(Estabelecimento novoEstab) throws Exception {
        try {
            System.out.println("Antes de criar:" + novoEstab);            
            operacaoCriar.setString(1, novoEstab.getNome());
            operacaoCriar.setString(2, novoEstab.getEndereco());
            operacaoCriar.executeUpdate();
            ResultSet keys = operacaoCriar.getGeneratedKeys();
            if (keys.next()) {
                novoEstab.setId(keys.getLong(1));
            }
            System.out.println("Depois de criar:" + novoEstab);
        } catch (SQLException ex) {
            Logger.getLogger(EstabelecimentoDAOJDBCPrep.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
    }

    public void excluirPorId(Long id) throws Exception {
        try {
            operacaoExcluirPorId.setLong(1, id);
            operacaoExcluirPorId.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EstabelecimentoDAOJDBCPrep.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EstabelecimentoDAOJDBCPrep.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex);
        }
        return estab;
    }
    
    
    
}
