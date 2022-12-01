<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Курсы</title>
    <#--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/milligram/1.4.1/milligram.css">-->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<table>
    <tr>
        <th>Название</th>
        <th>Описание</th>
        <th>Действие</th>
    </tr>
    <#list courses as course>
        <tr>
            <td>${course.title}</td>
            <td>${course.description}</td>
            <td>
                <a href="/courses/${course.id}">Перейти</a>
                <a href="/courses/${course.id}/delete">Удалить</a>
            </td>
        </tr>
    </#list>
</table>
<form action="/courses" method="post">
    <label>
        Название:
        <input type="text" name="title" placeholder="Введите название">
    </label>
    <br>
    <label>
        Описание:
        <input type="text" name="description" placeholder="Введите описание">
    </label>
    <br>
    <input autofocus type="submit" value="Добавить"/>
</form>
</body>
</html>