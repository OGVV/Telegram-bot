package com.github.Telegrambot.TestTelegramBot.Practice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Pattern;

@ComponentScan
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

        if (update.hasMessage() && update.getMessage().hasText()) {

            String chatId = update.getMessage().getChatId().toString();
            String message = update.getMessage().getText();
            SendMessage sm = new SendMessage();

            sm.setChatId(chatId);

            if (Pattern.matches("^[A-Z]{2,4}/[A-Z]{2,4}", message)) {
                sm.setText("I nothing know about pair " + message);
            } else
                sm.setText("You can contact with me any time");



            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}

