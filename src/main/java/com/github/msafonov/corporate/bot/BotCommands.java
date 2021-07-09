package com.github.msafonov.corporate.bot;

import com.github.msafonov.corporate.bot.controllers.EntityController;
import com.github.msafonov.corporate.bot.entities.Action;
import com.github.msafonov.corporate.bot.entities.AuthorizationCode;
import com.github.msafonov.corporate.bot.entities.Employee;
import com.github.msafonov.corporate.bot.entities.TypeOfAction;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class BotCommands {
    private FileStorage fileStorage;
    private Keyboard keyboard;
    private EntityController entityController;
    private Authorization authorization;
    private EmployeeLoader employeeLoader;
    private AuthorizationCodeLoader authorizationCodeLoader;

    public BotCommands(FileStorage fileStorage, Keyboard keyboard, EntityController entityController, Authorization authorization) {
        this.fileStorage = fileStorage;
        this.keyboard = keyboard;
        this.entityController = entityController;
        this.authorization = authorization;
        this.employeeLoader = new EmployeeLoader(entityController);
        this.authorizationCodeLoader = new AuthorizationCodeLoader(entityController);
    }

    public ReplyValues processCommand(String message, EntityController entityController, String chat_id) throws IOException, TelegramApiException {
        chat_id = "90";
        //Загружаем работника по chat_id

        EmployeeLoader employeeLoader = new EmployeeLoader(entityController);
        Employee employee = employeeLoader.getEmployee(chat_id);

        //Если работник уже использовал код доступа
        if (authorization.isRegistered(employee)) {
            switch (employeeLoader.getLastAction(employee).getTypeOfAction().getTypeAction()) {
                case INIT:
                    switch (message) {
                        case "Запросить кадровые шаблоны":
                            System.out.println("NEN");
                            addAction(employee, TypeAction.GET_TEMPLATES);
                            keyboard.createTemplatesKeyboard();
                            return new ReplyValues("Сейчас получите кадровые шаблоны");
                        case "Заявление на ежегодный отпуск":
                            addAction(employee, TypeAction.GET_VAC_WITH);
                            File vac = fileStorage.getFile(TemplateTypes.VACATION_WITH);
                            return new ReplyValues("Введите команду", vac);
                        case "Заявление на отпуск без сохранения зп":
                            addAction(employee, TypeAction.GET_VAC_NO);
                            File vac_no = fileStorage.getFile(TemplateTypes.VACATION_NO);
                            return new ReplyValues("Введите команду", vac_no);
                        case "Заявление на увольнение":
                            addAction(employee, TypeAction.GET_DISCHARGE);
                            File discharge = fileStorage.getFile(TemplateTypes.DISCHARGE);
                            return new ReplyValues("Введите команду", discharge);
                        case "Запросить обходной лист":
                            addAction(employee, TypeAction.GET_DISCHARGE_LIST);
                            File dischargeList = fileStorage.getFile(TemplateTypes.DISCHARGE_LIST);
                            return new ReplyValues("Введите команду", dischargeList);
                        default:
                            keyboard.createUserKeyboard();
                            return new ReplyValues("Введите команду");
                    }
                case INPUT_CODE:
                    employee.setFio(message);
                    entityController.update(employee);
                    addAction(employee, TypeAction.INPUT_NAME);
                    return new ReplyValues("Введите номер телефона:");
                case INPUT_NAME:
                    employee.setPhone(message);
                    entityController.update(employee);
                    addAction(employee, TypeAction.INPUT_PHONE);
                    return new ReplyValues("Введите ваш email:");
                case INPUT_PHONE:
                    employee.setEmail(message);
                    entityController.update(employee);
                    addAction(employee, TypeAction.INIT);
                    return new ReplyValues("Регистрация окончена." +
                            "\nВаше ФИО: " + employee.getFio() +
                            "\nНомер телефона: " + employee.getPhone() +
                            "\nEmail: " + employee.getEmail());
//                case INPUT_EMAIL:
//                    break;
                default:
                    keyboard.createUserKeyboard();
                    return new ReplyValues("Введите команду");
            }
            //Иначе если он админ
        } else if (authorization.isAdministrator(chat_id)) {
            //действия если он админ
            //Иначе работник новый
            keyboard.createAdminKeyboard();
            return new ReplyValues("Введите команду");

        } else {
            System.out.println("HHHHHWW");
            newEmployee(message, chat_id);
            keyboard.createEmptyKeyboard();
            return new ReplyValues("Введите команду");

        }

    }

    public ReplyValues newEmployee(String message, String chatId) {
        String regex = "[0-9]+";
        AuthorizationCode authorizationCode;
        //Если пользователь отправил только цифры, то предполагаем что это код доступа
        if (message.matches(regex)) {
            //Загружаем код
            authorizationCode = authorizationCodeLoader.getAuthorizationCode(message);
            if (authorization.isFreeCode(authorizationCode)) {
                //Создаем сотрудника
                Employee employee = new Employee();
                employee.setUserId(chatId);
                employee.setEmail(null);
                employee.setFio(null);
                employee.setPhone(null);

                //Регистрируем chat_id сотрудника
                authorization.register(employee, authorizationCode);

                addAction(employee, TypeAction.INPUT_CODE);
                return new ReplyValues("Код успешно активирован\nВведите ваше ФИО:");
            } else {
                return new ReplyValues("Введенный код недоступен.");
            }
            //Иначе приветствуем его
        } else {
            return new ReplyValues("Здравствуйте. Введите уникальный код доступа.");
        }
    }

    public void addAction(Employee employee, TypeAction typeAction) {
        var equals = new HashMap<String, Object>();
        equals.put("typeAction", typeAction);
        var criteriaQuery = entityController.getWhereEqual(TypeOfAction.class, equals);
        var typeOfAction = entityController.querySingle(criteriaQuery);
        if (typeOfAction == null)
            return;

        //Добавляем действие для сотрудника
        Action action = new Action();
        action.setEmployee(employee);
        action.setDateOfAction(LocalDateTime.now());
        action.setTypeOfAction(typeOfAction);
        entityController.save(action);
    }

}
