<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Профиль</title>
</head>
<body>
<#include "header/navigation.ftlh">
<h2>Профиль</h2>
<td>Имя: ${user.firstName}</td>
<br>
<td>Фамилия: ${user.lastName}</td>
<br>
<td>email: ${user.email}</td>
</body>
</html>
