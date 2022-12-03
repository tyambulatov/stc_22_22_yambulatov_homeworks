<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Пользователи</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<#include "../header/navigation_admin.ftlh">
<h2>Список пользователей</h2>
<table>
    <tr>
        <th>Email</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Действие</th>
    </tr>
    <#list users as user>
        <tr>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>
                <a href="/users/${user.id}">Перейти</a>
                <a href="/users/${user.id}/delete">Удалить</a>
            </td>
        </tr>
    </#list>
</table>
<h2>Добавить пользователя</h2>
<form action="/users" method="post">
    <label>
        Имя:
        <input type="text" name="firstName" placeholder="Введите имя">
    </label>
    <br>
    <label>
        Фамилия:
        <input type="text" name="lastName" placeholder="Введите фамилию">
    </label>
    <br>
    <label>
        Email:
        <input type="email" name="email" placeholder="Введите почту">
    </label>
    <br>
    <label>
        Пароль:
        <input type="password" name="password" placeholder="Введите пароль">
    </label>
    <br>
    <input autofocus type="submit" value="Добавить"/>
</form>
</body>
</html>