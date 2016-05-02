package br.cesjf.lppo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author igor
 */
@WebServlet(name = "EstabelecimentoController",
        urlPatterns = {"/listar.html", "/novo.html", "/excluir.html", "/editar.html"})
public class EstabelecimentoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().contains("listar.html")) {
            List<Estabelecimento> lista = new ArrayList<>();
            try {
                EstabelecimentoDAOPrep dao = new EstabelecimentoDAOPrep();
                lista = dao.listaTodos();
            } catch (Exception ex) {
                Logger.getLogger(EstabelecimentoController.class.getName()).log(Level.SEVERE, null, ex);
                lista = new ArrayList<Estabelecimento>();
                request.setAttribute("erro", "Problema ao listar os estabelecimentos!");
            }

            request.setAttribute("estabelecimentos", lista);
            request.getRequestDispatcher("/WEB-INF/listar.jsp").forward(request, response);
        } else if (request.getRequestURI().contains("novo.html")) {
            request.getRequestDispatcher("/WEB-INF/novo.jsp").forward(request, response);

        } else if (request.getRequestURI().contains("excluir.html")) {
            Long id = Long.parseLong(request.getParameter("id"));
            try {
                EstabelecimentoDAOPrep dao = new    EstabelecimentoDAOPrep();
                dao.excluirPorId(id);
            } catch (Exception ex) {
                Logger.getLogger(EstabelecimentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("listar.html");
        } else if (request.getRequestURI().contains("editar.html")) {
            Long id = Long.parseLong(request.getParameter("id"));
            EstabelecimentoDAO dao = new EstabelecimentoDAO();
            try {
                Estabelecimento estab = dao.buscaPorId(id);
                if (estab != null) {
                    request.setAttribute("estabelecimento", estab);
                    request.getRequestDispatcher("/WEB-INF/editar.jsp").forward(request, response);
                    return;
                }
            } catch (Exception ex) {
                Logger.getLogger(EstabelecimentoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("listar.html");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().contains("novo.html")) {
            Estabelecimento novoEstab = new Estabelecimento();
            novoEstab.setNome(request.getParameter("nome"));
            novoEstab.setEndereco(request.getParameter("endereco"));

        try {
            EstabelecimentoDAOPrep dao = new EstabelecimentoDAOPrep();

                dao.criar(novoEstab);
            } catch (Exception ex) {
                Logger.getLogger(EstabelecimentoController.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("listar.html?erro=Erro ao criar o Estabelecimento!");
                return;

            }

            response.sendRedirect("listar.html");
        } else if (request.getRequestURI().contains("editar.html")) {
            Long id = Long.parseLong(request.getParameter(
                    "id"));
            try {
                EstabelecimentoDAO dao = new EstabelecimentoDAO();
                Estabelecimento estab = dao.buscaPorId(id);
                if (estab != null) {
                    estab.setNome(request.getParameter("nome"));
                    estab.setEndereco(request.getParameter("endereco"));
                    estab.setVotos(Integer.parseInt(request.getParameter("votos")));
                    dao.salvar(estab);
                    response.sendRedirect("listar.html");
                }
            } catch (Exception ex) {
                Logger.getLogger(EstabelecimentoController.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("editar.html?id=" + id);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
