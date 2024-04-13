
<div>

##  Task Manager
</div>

---
### Разработчик
- [Муслимов Владислав](https://github.com/MuVlad)
- Контактная информация: *[muslimov.vlad@mail.ru]()*

---
## Описание проекта и его функциональность

Проект Task Manager представляет собой систему управления задачами, 
которая позволяет пользователям создавать, просматривать, обновлять и удалять задачи.

Документация к API генерируется с помощью Swagger.

---
## Запуск приложения
* Для запуска приложения Вам потребуется выполнить несколько шагов:
  - Клонировать проект и открыть его в среде разработки (например, *IntelliJ IDEA* или *VSCode*);
  - В файле **application.yml** указать путь к Вашей базе данных;
  - Запустить метод **main** в файле **TaskManagerApplication.java**

После выполнения всех шагов, веб-приложение будет доступно по адресу: http://localhost:8080

Swagger будет доступен по адресу: http://localhost:8080/swagger-ui/index.html

---
## Стэк технологий
* **Backend**:
    - Java 17
    - Gradle
    - Spring Boot
    - Spring Web
    - Spring Data JPA
    - Swagger
    - Lombok
    - Mapstruct


* **SQL**:
    - PostgreSQL


* **Tests**:
  - JUnit
  - Mockito