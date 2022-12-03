<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Войти в чат</title>
</head>
<body>
<form action="/signIn" method="post">
    <label>Ваш Email
        <input type="email" placeholder="Введите email" name="email">
    </label>
    <br>
    <label>Ваш Пароль
        <input type="password" placeholder="Введите password" name="password">
    </label>
    <br>
    <label>
        <input type="checkbox" name="rememberMe">Запомни меня
    </label>
    <br>
    <input type="submit">
</form>
</body>
</html>
