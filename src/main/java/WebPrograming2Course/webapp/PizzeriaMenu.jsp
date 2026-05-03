<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Список Піц</title>
    <style>
        table { border-collapse: collapse; width: 50%; margin-top: 20px; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .btn { padding: 5px 10px; text-decoration: none; border: 1px solid #ccc; background: #eee; color: black; }
    </style>
</head>
<body>
    <h2>Управління Піцерією 🍕</h2>

    <a href="pizzas?action=new" class="btn">➕ Створити нову піцу</a>

    <table>
        <tr>
            <th>ID</th>
            <th>Назва Піци</th>
            <th>ID Замовника</th> <th>Дії</th>
        </tr>
        <c:forEach var="pizza" items="${listPizza}">
            <tr>
                <td><c:out value="${pizza.id}" /></td>
                <td><c:out value="${pizza.name}" /></td>
                <td><c:out value="${pizza.customer != null ? pizza.customer.id : 'Немає'}" /></td>
                <td>
                    <a href="pizzas?action=edit&id=<c:out value='${pizza.id}'/>">✏️ Редагувати</a> |
                    <a href="pizzas?action=delete&id=<c:out value='${pizza.id}'/>" onclick="return confirm('Точно видалити?');">🗑️ Видалити</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>