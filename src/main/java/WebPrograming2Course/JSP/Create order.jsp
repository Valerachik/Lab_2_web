<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Форма Піци</title>
</head>
<body>
    <h2>
        <c:if test="${pizza != null}">Редагувати піцу</c:if>
        <c:if test="${pizza == null}">Додати нову піцу</c:if>
    </h2>

    <form action="pizzas" method="post">
        <c:if test="${pizza != null}">
            <input type="hidden" name="id" value="<c:out value='${pizza.id}' />" />
        </c:if>

        <p>
            <label>Назва піци:</label><br>
            <input type="text" name="name" value="<c:out value='${pizza.name}' />" required />
        </p>

        <p>
            <label>Оберіть замовника:</label><br>
            <select name="customer_id">
                <option value="">-- Без замовника --</option>
                <c:forEach var="customer" items="${listCustomers}">
                    <option value="${customer.id}"
                        <c:if test="${pizza.customer != null && pizza.customer.id == customer.id}">selected</c:if> >
                        ${customer.name} ${customer.surname} (Тел: ${customer.phone})
                    </option>
                </c:forEach>
            </select>
        </p>

        <button type="submit">💾 Зберегти</button>
        <a href="pizzas">Скасувати</a>
    </form>
</body>
</html>