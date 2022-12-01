<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Курс</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<a href="/courses">Ко всем курсам</a>
<h1>${course.title}</h1>
<h2>Редактирование курса</h2>

<form action="/courses/${course.id}/update" method="post">
    <label>
        Название:
        <input type="text" name="title" value="${course.title}">
    </label>
    <br>
    <label>
        Описание:
        <input type="text" name="description" value="${course.description}">
    </label>
    <br>
    <label>
        Дата начала:
        <input type="date" name="start" value=${course.start}>
    </label>
    <br>
    <label>
        Дата конца:
        <input type="date" name="finish" value=${course.finish}>
    </label>
    <br>
    <input autofocus type="submit" value="Сохранить"/>
</form>

<h2>Студенты в курсе</h2>
<table>
    <tr>
        <th>Email</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Действие</th>
    </tr>
    <#list inCourseStudents as student>
        <tr>
            <td>${student.email}</td>
            <td>${student.firstName}</td>
            <td>${student.lastName}</td>
            <td>
                <a href="/users/${student.id}">Перейти</a>
            </td>
        </tr>
    </#list>
</table>

<h2>Добавить студента на курс</h2>
<form action="/courses/${course.id}/students" method="post">
    <label>Студенты
        <select name="student-id">
            <#list notInCourseStudents as student>
                <option value="${student.id}">${student.firstName} ${student.lastName}</option>
            </#list>
        </select>
    </label>
    <input type="submit" value="Добавить">
</form>

<h2>Уроки курса</h2>
<table>
    <tr>
        <th>Название</th>
        <th>Конспект</th>
        <th>Действие</th>
    </tr>
    <#list courseLessons as lesson>
        <tr>
            <td>${lesson.name}</td>
            <td>${lesson.summary}</td>
            <td>
                <a href="/lessons/${lesson.id}">Перейти</a>
            </td>
        </tr>
    </#list>
</table>

<h2>Добавить урок на курс</h2>
<form action="/courses/${course.id}/lessons" method="post">
    <label>уроки
        <select name="lesson-id">
            <#list lessonsNotInCourse as lesson>
                <option value="${lesson.id}">${lesson.name} ${lesson.summary}</option>
            </#list>
        </select>
    </label>
    <input type="submit" value="Добавить">
</form>

</body>
</html>