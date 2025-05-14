package com.taska.pm.bot;


import com.taska.pm.bot.commands.BotCommands;
import com.taska.pm.service.TaskaBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class TaskaBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final TaskaBotService botService;

    public TaskaBot(@Value("${bot.token}") String botToken,
                    @Value("${bot.name}") String botUsername,
                    @Lazy TaskaBotService botService) {
        super(botToken);
        this.botUsername = botUsername;
        this.botService = botService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        String telegramUsername = update.getMessage().getChat().getUserName();
        switch (message) {
            case BotCommands.START -> {
                String firstName = update.getMessage().getChat().getFirstName();
                botService.startCommand(chatId, firstName, telegramUsername);
            }
            case BotCommands.HELP -> botService.helpCommand(chatId);
            default -> botService.unknownCommand(chatId);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
