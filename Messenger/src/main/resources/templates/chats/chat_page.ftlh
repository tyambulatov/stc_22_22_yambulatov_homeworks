<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Чат</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<#include "../header/navigation.ftlh">
<h1>Чат: ${chat.name}</h1>
<h2>Редактирование чата</h2>
<form action="/chats/${chat.id}/update" method="post">
    <label>
        Название:
        <input type="text" name="name" value="${chat.name}">
    </label>
    <br>
    <input autofocus type="submit" value="Сохранить"/>
</form>

<h2>Учасники чата</h2>
<table>
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <#if role == 'ADMIN'>
            <th>Email</th>
        </#if>
        <th>Действие</th>
    </tr>
    <#list inChatUsers as user>
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <#if role == 'ADMIN'>
                <td>${user.email}</td>
            </#if>
            <td>
                <a href="/users/${user.id}">Перейти</a>
                <#if role == 'ADMIN'>
                    <a href="/chats/${chat.id}/users/${user.id}/delete">Удалить из чата</a>
                </#if>
            </td>
        </tr>
    </#list>
</table>
<#if role == 'ADMIN'>
    <h2>Добавить пользователя в чат</h2>
    <form action="/chats/${chat.id}/users" method="post">
        <label>Пользователи
            <select name="user-id">
                <#list notInChatUsers as user>
                    <option value="${user.id}">${user.firstName} ${user.lastName}</option>
                </#list>
            </select>
        </label>
        <br>
        <input type="submit" value="Добавить">
    </form>
</#if>
<h2>Сообщения чата</h2>
<table>
    <tr>
        <th>Содержание</th>
        <th>Время отправки</th>
        <th>Действие</th>
    </tr>
    <#list chatMessages as message>
        <tr>
            <td>${message.content}</td>
            <td>${message.sendTime}</td>
            <td>
                <a href="/messages/${message.id}">Перейти</a>
                <a href="/messages/${message.id}/delete">Удалить</a>
            </td>
        </tr>
    </#list>
</table>
<h2>Написать сообщение в чат</h2>
<form action="/messages" method="post">
    <label>
        Номер чата:
        <input type="number" name="chat-id" value=${chat.id}>
    </label>
    <br>
    <label>
        Сообщение:
        <input type="text" name="content" placeholder="Сообщение">
    </label>
    <br>
    <input autofocus type="submit" value="Добавить">
</form>
</body>
</html>