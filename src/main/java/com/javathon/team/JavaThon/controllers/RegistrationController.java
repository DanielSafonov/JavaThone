package com.javathon.team.JavaThon.controllers;

import com.javathon.team.JavaThon.entities.User;
import com.javathon.team.JavaThon.repositories.HackathonRepository;
import com.javathon.team.JavaThon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller //Контроллер регистрации нового пользователя
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HackathonRepository hackathonRepository;

    @GetMapping("/registration") //Обработка GET запросов на адрес /registration вызовом метода registration()
    public String registration(Model model){
        return "registration";  //Возвращает имя View (веб-страницы)
    }

    @PostMapping("/registration") //Обработка POST запроса на /registration
    public String addUser(User user, Map<String, Object> model){
        //Поиск такого phoneNumber в базе (phoneNumber уже занят)
        hackathonRepository.findAll();
        User userFromDb = userRepository.findByPhoneNumber(user.getPhoneNumber());

        //Проверка phoneNumber
        if(userFromDb != null){
            //phoneNumber занят
            model.put("message", "Пользователь с таким номером телефона уже существует! Регистрация не удалась."); //Передача строки в модель
            return "registration"; //Возврат на страницу регистрациии
        }

        //Username не занят
        userRepository.save(user); //Сохранить пользователя в БД

        return "redirect:/login"; //Редирект на страницу авторизации
    }
}
