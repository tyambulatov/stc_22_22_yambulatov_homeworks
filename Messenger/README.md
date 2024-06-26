# Проект итоговой аттестации. Тема: приложение "Мессенджер"

## Функционал:

### Сущности
* Имеет три сущности: Пользователь(`User`), чат(`Chat`), сообщение(`Message`);
### Страницы
* На каждую сущность имеется по две страницы. Это страница со всеми сущностями и страница отедельной сущности.
* На страницах сущностей реализованы переходы между ними.
### Функционал
* Реализованы функции CRUD для каждой сущности.
* Реализованы функции: 
  * добавления/удаления пользователя из чата, 
  * добавления/удаления сообщения из чата.
### Роли
* Сделаны две роли пользователя приложения: роль администартора(`ADMIN`) и роль пользователя(`USER`);
### Модульный тест
* Реализован модульный тест с использованием стеды тестирования Mockito. 
* Имеется тест для метода `getInChatUsers()` сервиса `ChatsService`.
### Безопасность
* Пароли в базе данных хранятся в хешированном виде.
* #### Страницы
  * **Администратор** имеет доступ ко всем страницам приложения.
  * **Пользователь** не имеет доступа к страницам: `/users` и `/messages`. Он не может видеть всех пользователей мессенджера и всех сообщений в мессенджере.
* #### Функции
  * **Администратор** имеет доступ ко всем функциям CRUD.
  * **Пользователь** не имеет доступа к операциям:
      * Пользователи(`User`): создания, обновления и удаления пользователя; получение всех пользователей;
      * Чаты(`Chat`): удаление чата;
      * Сообщения(`Message`): получение всех сообщений.

### Ямбулатов Тимур