<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Уроки</title>
    <#--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/milligram/1.4.1/milligram.css">-->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<table>
    <tr>
        <th>Название</th>
        <th>Конспект</th>
        <th>Действие</th>
    </tr>
    <#list lessons as lesson>
        <tr>
            <td>${lesson.name}</td>
            <td>${lesson.summary}</td>
            <td>
                <a href="/lessons/${lesson.id}">Перейти</a>
                <a href="/lessons/${lesson.id}/delete">Удалить</a>
            </td>
        </tr>
    </#list>
</table>
<form action="/lessons" method="post">
    <label>
        Название:
        <input type="text" name="name" placeholder="Введите название">
    </label>
    <br>
    <label>
        Конспект:
        <input type="text" name="summary" placeholder="Введите конспект">
    </label>
    <br>
    <input autofocus type="submit" value="Добавить"/>
</form>
</body>
</html>