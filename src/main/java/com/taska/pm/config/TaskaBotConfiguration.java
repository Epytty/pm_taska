package com.taska.pm.config;

import com.taska.pm.bot.TaskaBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TaskaBotConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi(TaskaBot taskaBot) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(taskaBot);
        return telegramBotsApi;
    }
}
