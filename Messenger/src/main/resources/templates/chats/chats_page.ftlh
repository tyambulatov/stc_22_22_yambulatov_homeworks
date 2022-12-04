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
                    <a href="/users/${userId}/deleteUserFromChat?chat-id=${chat.id}">Удалить из чата</a>
                </td>
            </tr>
        </#list>
    </table>
    <h2>Вступить в чат</h2>
    <form action="/users/${userId}/addUserToChat" method="post">
        <label>Название чата
            <select name="chat-id">
                <#list chatsWithoutUser as chat>
                    <option value="${chat.id}">${chat.name}</option>
                </#list>
            </select>
        </label>
        <br>
        <input type="submit" value="Вступить">
    </form>
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