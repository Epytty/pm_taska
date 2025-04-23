package com.taska.pm.bot;


import com.taska.pm.bot.patterns.MessagePatterns;
import com.taska.pm.exception.message.ExceptionMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class BotMessageHandler implements BotMessageSender {

    private final TelegramLongPollingBot bot;

    public BotMessageHandler(TelegramLongPollingBot bot) {
        this.bot = bot;
    }

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
}