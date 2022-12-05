<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Сообщение</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<#include "../header/navigation.ftlh">
<br>
<a href="/chats/${message.chat.id}">Перейти к чату сообщения</a>
<h2>Информация о сообщении</h2>
<td>Название чата: ${message.chat.name}</td>
<br>
<td>Время отправки: ${message.sendTime}</td>
<br>
<td>Содержание: ${message.content}</td>
<br>
<h2>Редактировать содержание</h2>
<form action="/messages/${message.id}/update" method="post">
    <label>
        <input type="text" name="content" value="${message.content}">
    </label>
    <br>
    <input autofocus type="submit" value="Сохранить"/>
</form>
</body>
</html>