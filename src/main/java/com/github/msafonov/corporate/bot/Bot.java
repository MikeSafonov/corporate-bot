package com.github.msafonov.corporate.bot;

import com.github.msafonov.corporate.bot.controllers.EntityController;
import com.github.msafonov.corporate.bot.entities.Action;
import com.github.msafonov.corporate.bot.entities.AuthorizationCode;
import com.github.msafonov.corporate.bot.entities.Employee;
import com.github.msafonov.corporate.bot.entities.TypeOfAction;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.util.HashMap;


public class Bot extends TelegramLongPollingBot {
    private BotProperties botProperties;
    private EntityController entityController;
    private Authorization authorization;
    private EmployeeLoader employeeLoader;
    private AuthorizationCodeLoader authorizationCodeLoader;

    public Bot(BotProperties botProperties, EntityController entityController) {
        this.botProperties = botProperties;
        this.entityController = entityController;
        authorization = new Authorization(entityController);
        employeeLoader = new EmployeeLoader(entityController);
        authorizationCodeLoader = new AuthorizationCodeLoader(entityController);
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            var chat_id = update.getMessage().getChatId().toString();
            String receiveMessage = update.getMessage().getText();

            //Загружаем работника по chat_id
            Employee employee;
            employee = employeeLoader.getEmployee(chat_id);

            //Если работник уже использовал код доступа
            if (authorization.isRegistered(employee)) {
                switch (employeeLoader.getLastAction(employee).getTypeOfAction().getTypeAction()) {
                    case INIT:
                        break;
                    case INPUT_CODE:
                        employee.setFio(receiveMessage);
                        entityController.update(employee);
                        addAction(employee, TypeAction.INPUT_NAME);
                        SendMessage(update, "Введите номер телефона:");
                        break;
                    case INPUT_NAME:
                        employee.setPhone(receiveMessage);
                        entityController.update(employee);
                        addAction(employee, TypeAction.INPUT_PHONE);
                        SendMessage(update, "Введите ваш email:");
                        break;
                    case INPUT_PHONE:
                        employee.setEmail(receiveMessage);
                        entityController.update(employee);
                        addAction(employee, TypeAction.INPUT_EMAIL);
                        SendMessage(update, "Регистрация окончена." +
                                "\nВаше ФИО: " + employee.getFio() +
                                "\nНомер телефона: " + employee.getPhone() +
                                "\nEmail: " + employee.getEmail());
                        break;
                    case INPUT_EMAIL:
                        break;
                    default:
                        break;
                }
                //Иначе если он админ
            } else if (authorization.isAdministrator(chat_id)) {
                //действия если он админ
                //Иначе работник новый
            } else {
                newEmployee(update);
            }
        }
    }

    public void newEmployee(Update update) {
        String regex = "[0-9]+";
        String receiveMessage = update.getMessage().getText();

        AuthorizationCode authorizationCode;
        //Если пользователь отправил только цифры, то предполагаем что это код доступа
        if (receiveMessage.matches(regex)) {
            //Загружаем код
            authorizationCode = authorizationCodeLoader.getAuthorizationCode(receiveMessage);
            if (authorization.isFreeCode(authorizationCode)) {
                var chat_id = update.getMessage().getChatId().toString();

                //Создаем сотрудника
                Employee employee = new Employee();
                employee.setUserId(chat_id);
                employee.setEmail(null);
                employee.setPhone(null);
                employee.setFio(null);

                //Регистрируем chat_id сотрудника
                authorization.register(employee, authorizationCode);

                addAction(employee, TypeAction.INPUT_CODE);
                SendMessage(update, "Код успешно активирован.");
                SendMessage(update, "Введите ваше ФИО:");
            } else {
                SendMessage(update, "Введенный код недоступен.");
            }
            //Иначе приветствуем его
        } else {
            SendMessage(update, "Здравствуйте. Введите уникальный код доступа.");
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

    public boolean SendMessage(Update update, String text) {
        SendMessage sendMessage = new SendMessage();
        var chat_id = update.getMessage().getChatId().toString();
        sendMessage.setChatId(chat_id);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String getBotUsername() {

        return botProperties.getBotName();
    }

    @Override
    public String getBotToken() {

        return botProperties.getToken();
    }


}
