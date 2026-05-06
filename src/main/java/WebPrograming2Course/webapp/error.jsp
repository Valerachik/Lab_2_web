<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Помилка</title></head>
<body style="text-align:center; padding: 50px; font-family: sans-serif;">
    <h1 style="color: red;">Ой, щось пішло не так! 🚫</h1>
    <p>${errorMessage != null ? errorMessage : "Невідома помилка в обробці запиту."}</p>
    <a href="orders" style="display:inline-block; margin-top:20px; padding:10px 20px; background:#007bff; color:white; text-decoration:none; border-radius:5px;">Повернутися до замовлень</a>
</body>
</html>