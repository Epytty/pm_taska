package com.taska.pm.service;


public interface TaskaBotService {

    void sendMessage(Long chatId, String text);
    void startCommand(Long chatId, String firstName, String telegramUsername);
    void helpCommand(Long chatId);
    void unknownCommand(Long chatId);
}
