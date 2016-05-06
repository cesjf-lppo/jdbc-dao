package br.cesjf.lppo.bd;

import br.cesjf.lppo.Estabelecimento;
import java.util.List;

public interface EstabelecimentoDAO {

    Estabelecimento buscaPorId(Long id) throws Exception;

    void criar(Estabelecimento novoEstab) throws Exception;

    void excluir(Estabelecimento estab) throws Exception;

    void excluirPorId(Long id) throws Exception;

    List<Estabelecimento> listaTodos() throws Exception;

    void salvar(Estabelecimento estab) throws Exception;
    
}
