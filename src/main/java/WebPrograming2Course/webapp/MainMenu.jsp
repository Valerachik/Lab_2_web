<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Список Замовлень</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f9f9f9; padding: 20px; }
        h2 { color: #333; }
        .btn-new { display: inline-block; padding: 10px 15px; background-color: #28a745; color: white; text-decoration: none; border-radius: 5px; font-weight: bold; margin-bottom: 20px;}
        .btn-new:hover { background-color: #218838; }
        table { width: 80%; border-collapse: collapse; background: white; box-shadow: 0 1px 3px rgba(0,0,0,0.2); }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #ff6b6b; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .action-links a { text-decoration: none; margin-right: 10px; color: #007bff; font-weight: bold; }
        .action-links a.delete { color: #dc3545; }
    </style>
</head>
<body>
    <h2>📋 Журнал Замовлень Піцерії</h2>

    <a href="orders?action=new" class="btn-new">➕ Створити нове замовлення</a>

    <table>
        <tr>
            <th>ID Замовлення</th>
            <th>Клієнт</th>
            <th>Піцерія (Філія)</th>
            <th>Кількість піц</th>
            <th>Дії</th>
        </tr>
        <c:forEach var="order" items="${listOrders}">
            <tr>
                <td><strong>#<c:out value="${order.id}" /></strong></td>
                <!-- Часткова інформація (лише ім'я) -->
                <td><c:out value="${order.customer.name}" /></td>
                <td><c:out value="${order.pizzeria.name}" /></td>
                <td><c:out value="${order.pizzas.size()}" /> шт.</td>
                <td class="action-links">
                    <a href="orders?action=edit&id=<c:out value='${order.id}'/>">✏️ Деталі/Редагувати</a>
                    <a href="orders?action=delete&id=<c:out value='${order.id}'/>" class="delete" onclick="return confirm('Точно видалити це замовлення?');">🗑️ Видалити</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>