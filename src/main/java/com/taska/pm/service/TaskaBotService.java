package com.taska.pm.service;


public interface TaskaBotService {

    void startCommand(Long chatId, String firstName);
    void helpCommand(Long chatId);
    void unknownCommand(Long chatId);
}
