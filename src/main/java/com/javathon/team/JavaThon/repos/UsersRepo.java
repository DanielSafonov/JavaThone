package com.javathon.team.JavaThon.repos;

//Доступ к профилям пользователей
//Интерфейс для работы с БД
public interface UsersRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);  //Метод поиска профиля по username
}