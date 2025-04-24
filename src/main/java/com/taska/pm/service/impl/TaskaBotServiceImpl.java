package com.taska.pm.service.impl;

import com.taska.pm.bot.patterns.MessagePatterns;
import com.taska.pm.exception.message.ExceptionMessages;
import com.taska.pm.service.TaskaBotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskaBotServiceImpl implements TaskaBotService {

    private final TelegramLongPollingBot bot;

    @Override
    public void sendMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);
        sendMessage.setParseMode(MessagePatterns.PARSE_MODE_MARKDOWN);
        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(String.format(ExceptionMessages.SEND_MESSAGE_ERROR), e);
        }
    }

    @Override
    public void startCommand(Long chatId, String firstName) {
        sendMessage(chatId, String.format(MessagePatterns.START_COMMAND, firstName));
    }

    @Override
    public void helpCommand(Long chatId) {
        sendMessage(chatId, String.format(MessagePatterns.HELP_COMMAND));
    }

    @Override
    public void unknownCommand(Long chatId) {
        sendMessage(chatId, String.format(MessagePatterns.UNKNOWN_COMMAND));
    }
}
