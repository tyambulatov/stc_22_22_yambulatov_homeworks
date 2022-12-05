<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Чаты</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<#include "../header/navigation.ftlh">
<#if role == 'ADMIN'>
    <h2>Чаты</h2>
    <table>
        <tr>
            <th>Название</th>
            <th>Действия</th>
        </tr>
        <#list chats as chat>
            <tr>
                <td>${chat.name}</td>
                <td>
                    <a href="/chats/${chat.id}">Перейти</a>
                    <a href="/chats/${chat.id}/delete">Удалить</a>
                </td>
            </tr>
        </#list>
    </table>
</#if>
<#if role == 'USER'>
    <h2>Чаты пользователя</h2>
    <table>
        <tr>
            <th>Название</th>
            <th>Действия</th>
        </tr>
        <#list userChats as chat>
            <tr>
                <td>${chat.name}</td>
                <td>
                    <a href="/chats/${chat.id}">Перейти</a>
                    <a href="/chats/${chat.id}/users/${userId}/delete">Удалить из чата</a>
                </td>
            </tr>
        </#list>
    </table>
    <h2>Чаты в которых не состоит пользователь</h2>
    <select name="chat-id">
        <#list chatsWithoutUser as chat>
            <option value="${chat.id}">${chat.name}</option>
        </#list>
    </select>
</#if>
<h2>Создать чат</h2>
<form action="/chats" method="post">
    <label>
        Название:
        <input type="text" name="name" placeholder="Введите название чата">
    </label>
    <br>
    <input autofocus type="submit" value="Создать"/>
</form>
</body>
</html>