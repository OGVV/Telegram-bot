package com.github.Telegrambot.TestTelegramBot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BotFunc extends TelegramLongPollingBot {

    @Value("${bot_login}")
    private String login;

    @Value("${bot_password}")
    private String password;
    @Override
    public String getBotUsername() {
        return login;
    }

    @Override
    public String getBotToken() {
        return password;
    }

    @Override
    public void onUpdateReceived(Update update) {
       if (update.hasMessage() && update.getMessage().hasText()){
           String message = update.getMessage().getText().trim();
           String chatId = update.getMessage().getChatId().toString();

           SendMessage sm = new SendMessage();
           sm.setChatId(chatId);
           sm.setText("иди нахуй глеб");

           try {
               execute(sm);
           } catch (TelegramApiException e) {
               //todo add logging to the project.
               e.printStackTrace();
           }
       }
       }
    }

