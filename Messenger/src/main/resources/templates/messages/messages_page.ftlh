<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Сообщения</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<#include "../header/navigation_admin.ftlh">
<h2>Все сообщения</h2>
<table>
    <tr>
        <th>Содержание</th>
        <th>Чат</th>
        <th>Время отправки</th>
        <th>Действие</th>
    </tr>
    <#list messages as message>
        <tr>
            <td>${message.content}</td>
            <td>${message.chat.name}</td>
            <td>${message.sendTime}</td>
            <td>
                <a href="/messages/${message.id}">Перейти</a>
                <a href="/messages/${message.id}/delete">Удалить</a>
            </td>
        </tr>
    </#list>
</table>
<h2>Добавить сообщение</h2>
<form action="/messages" method="post">
    <label>
        Выбор чата:
        <select name="chat-id">
            <#list chats as chat>
                <option value="${chat.id}">${chat.name} </option>
            </#list>
        </select>
        <br>
        Сообщение:
        <input type="text" name="content" placeholder="Введите сообщение">
    </label>
    <br>
    <input autofocus type="submit" value="Добавить"/>
</form>

</body>
</html>