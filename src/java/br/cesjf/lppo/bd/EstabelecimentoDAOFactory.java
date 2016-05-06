package br.cesjf.lppo.bd;

public class EstabelecimentoDAOFactory {

    public static EstabelecimentoDAO getDAO() throws Exception {
        return getDAO(2);
    }

    public static EstabelecimentoDAO getDAO(int tipo) throws Exception {
        switch (tipo) {
            case DAO_JDBC:
                return new EstabelecimentoDAOJDBC();
            case DAO_JDBC_PREP:
                return new EstabelecimentoDAOJDBCPrep();
            default:
                return new EstabelecimentoDAOJDBCPrep();
        }
    }
    public static final int DAO_JDBC_PREP = 2;
    public static final int DAO_JDBC = 1;

}
