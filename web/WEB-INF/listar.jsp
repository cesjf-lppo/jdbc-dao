
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Estabelecimentos</title>
    </head>
    <body>
        <c:if test="${erro != null}">
            <div style="border: 1px solid red;">${erro}</div>
        </c:if>
        <h1>Listar Estabelecimentos</h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Votos</th>
                    <th>Nome</th>
                    <th>Endereço</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${estabelecimentos}" var="estabelecimento">
                    <tr>
                        <td>${estabelecimento.id}</td>
                        <td>${estabelecimento.votos}</td>
                        <td>${estabelecimento.nome}</td>
                        <td>${estabelecimento.endereco}</td>
                        <td>[<a href="excluir.html?id=${estabelecimento.id}">x</a>]</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
