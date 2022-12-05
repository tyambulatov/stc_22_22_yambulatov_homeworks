<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Пользователь</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<#include "../header/navigation.ftlh">
<h2>Профиль пользователя: ${user.firstName} ${user.lastName}</h2>
<#if role == 'ADMIN'>
    <h2>Редактировать данные пользвателя</h2>
    <form action="/users/${user.id}/update" method="post">
        <label>
            Имя:
            <input type="text" name="firstName" value="${user.firstName}">
        </label>
        <br>
        <label>
            Фамилия:
            <input type="text" name="lastName" value="${user.lastName}">
        </label>
        <br>
        <input autofocus type="submit" value="Сохранить"/>
    </form>
</#if>

<h2>Чаты пользователя</h2>
<table>
    <tr>
        <th>Название чата</th>
        <#if role == 'ADMIN'>
            <th>Действие</th>
        </#if>
    </tr>
    <#list userChats as chat>
        <tr>
            <td>${chat.name}</td>
            <td>
                <#if role == 'ADMIN'>
                    <a href="/chats/${chat.id}">Перейти</a>
                    <a href="/chats/${chat.id}/users/${userId}/delete">Выйти из чата</a>
                </#if>
            </td>
        </tr>
    </#list>
</table>
</body>
</html>