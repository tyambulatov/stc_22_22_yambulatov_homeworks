<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Урок</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<a href="/lessons">Ко всем урокам</a>
<h1>${lesson.name}</h1>
<h2>Редактирование курса</h2>

<form action="/lessons/${lesson.id}/update" method="post">
    <label>
        Название:
        <input type="text" name="name" value="${lesson.name}">
    </label>
    <br>
    <label>
        Конспект:
        <input type="text" name="summary" value="${lesson.summary}">
    </label>
    <br>
    <label>
        Время начала:
        <input type="time" name="startTime" value=${lesson.startTime}>
    </label>
    <br>
    <label>
        Время конца:
        <input type="time" name="finishTime" value=${lesson.finishTime}>
    </label>
    <br>
    <input autofocus type="submit" value="Сохранить"/>
</form>

</body>
</html>