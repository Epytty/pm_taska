package com.taska.pm.bot;


public interface BotMessageSender {

    void sendMessage(Long chatId, String text);
}