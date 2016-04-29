<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Estabelecimento</title>
    </head>
    <body>
        <jsp:include page="fragments/menu.jspf" />
        <h1>Editar Estabelecimento</h1>
        <form method="post">
            <div>
                <label>ID: ${estabelecimento.id}
                <input type="hidden" name="id" value="${estabelecimento.id}"/>
                </label>
            </div>
            <div>
                <label>Nome:
                <input type="text" name="nome"  value="${estabelecimento.nome}"/>
                </label>
            </div>
            <div>
                <label>Endereço:
                    <textarea name="endereco" >${estabelecimento.endereco}</textarea>
                </label>
            </div>
            <div>
                <label>Votos:
                <input type="number" name="votos"  value="${estabelecimento.votos}"/>
                </label>
            </div>
            <div>
                <input type="submit" />
            </div>
        </form>
    </body>
</html>
