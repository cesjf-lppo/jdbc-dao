<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo Estabelecimento</title>
    </head>
    <body>
        <jsp:include page="fragments/menu.jspf" />
        <h1>Novo Estabelecimento</h1>
        <form method="post">
            <div>
                <label>Nome:
                <input type="text" name="nome" />
                </label>
            </div>
            <div>
                <label>Endereço:
                    <textarea name="endereco" ></textarea>
                </label>
            </div>
            <div>
                <input type="submit" />
            </div>
        </form>
    </body>
</html>
