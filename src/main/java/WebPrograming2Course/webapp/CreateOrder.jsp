<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Управління Замовленням</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; background-color: #f4f7f6; padding: 20px; }
        .form-container { background: white; padding: 25px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); width: 50%; margin: auto; }
        h2 { text-align: center; color: #333; }
        .form-group { margin-bottom: 15px; }
        label { font-weight: bold; display: block; margin-bottom: 5px; color: #555; }
        select, input[type="text"] { width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .checkbox-group { border: 1px solid #ccc; padding: 10px; border-radius: 4px; max-height: 150px; overflow-y: auto; background: #fafafa; }
        .checkbox-group label { font-weight: normal; display: inline-block; margin-left: 5px; }
        .btn-submit { background-color: #ff6b6b; color: white; border: none; padding: 10px 20px; font-size: 16px; border-radius: 4px; cursor: pointer; width: 100%; }
        .btn-submit:hover { background-color: #ff4c4c; }
        .btn-cancel { display: block; text-align: center; margin-top: 10px; color: #666; text-decoration: none; }
    </style>
</head>
<body>

<div class="form-container">
    <h2>
        <c:if test="${order != null}">📝 Редагувати Замовлення #${order.id}</c:if>
        <c:if test="${order == null}">🍕 Нове Замовлення</c:if>
    </h2>

    <form action="orders" method="post">
        <c:if test="${order != null}">
            <input type="hidden" name="id" value="<c:out value='${order.id}' />" />
        </c:if>

        <div class="form-group">
            <label>👤 Замовник (Повна інформація):</label>
            <select name="customer_id" required>
                <option value="">-- Оберіть клієнта --</option>
                <c:forEach var="customer" items="${listCustomers}">
                    <option value="${customer.id}"
                        <c:if test="${order != null && order.customer.id == customer.id}">selected</c:if>>
                        ${customer.name} ${customer.surname} | Тел: ${customer.phone}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>🏪 Піцерія (Місце видачі):</label>
            <select name="pizzeria_id" required>
                <option value="">-- Оберіть піцерію --</option>
                <c:forEach var="pizzeria" items="${listPizzerias}">
                    <option value="${pizzeria.id}"
                        <c:if test="${order != null && order.pizzeria.id == pizzeria.id}">selected</c:if>>
                        ${pizzeria.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label>🍕 Оберіть піци (можна декілька):</label>
            <div class="checkbox-group">
                <c:forEach var="pizza" items="${listPizzas}">
                    <!-- Логіка перевірки, чи була ця піца вже у замовленні -->
                    <c:set var="isChecked" value="false" />
                    <c:if test="${order != null}">
                        <c:forEach var="orderPizza" items="${order.pizzas}">
                            <c:if test="${orderPizza.id == pizza.id}">
                                <c:set var="isChecked" value="true" />
                            </c:if>
                        </c:forEach>
                    </c:if>

                    <div>
                        <input type="checkbox" name="pizza_ids" value="${pizza.id}" id="pizza_${pizza.id}"
                            <c:if test="${isChecked}">checked</c:if> >
                        <label for="pizza_${pizza.id}">${pizza.name}</label>
                    </div>
                </c:forEach>
            </div>
        </div>

        <button type="submit" class="btn-submit">💾 Зберегти Замовлення</button>
        <a href="orders" class="btn-cancel">Скасувати та повернутися</a>
    </form>
</div>

</body>
</html>