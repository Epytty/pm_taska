package com.taska.pm.service.impl;

import com.taska.pm.bot.BotMessageSender;
import com.taska.pm.bot.patterns.MessagePatterns;
import com.taska.pm.service.TaskaBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskaBotServiceImpl implements TaskaBotService {

    private final BotMessageSender messageSender;

    @Override
    public void startCommand(Long chatId, String firstName) {
        messageSender.sendMessage(chatId, String.format(MessagePatterns.START_COMMAND, firstName));
    }

    @Override
    public void helpCommand(Long chatId) {
        messageSender.sendMessage(chatId, String.format(MessagePatterns.HELP_COMMAND));
    }

    @Override
    public void unknownCommand(Long chatId) {
        messageSender.sendMessage(chatId, String.format(MessagePatterns.UNKNOWN_COMMAND));
    }
}
