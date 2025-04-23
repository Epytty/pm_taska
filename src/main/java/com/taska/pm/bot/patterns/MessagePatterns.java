package com.taska.pm.bot.patterns;


public final class MessagePatterns {

    public static final String PARSE_MODE_MARKDOWN = "Markdown";
    public static final String START_COMMAND = """
            *Привет, %s!* 👋
            Я буду оповещать тебя о поступлении новых задач и напоминать о дедлайнах!
            
            _Подробнее ты можешь ознакомиться с моими командами, написав /help_
            """;
    public static final String HELP_COMMAND = """
            Вот команды, которые я знаю:
            
            *Общие команды*
            */help* - список доступных команд и их описание.
            */someCommand* - comand description
            */someCommand* - comand description
            
            *Работа с задачами*
            */someCommand* - comand description
            */someCommand* - comand description
            */someCommand* - comand description
            */someCommand* - comand description

            """;
    public static final String UNKNOWN_COMMAND = """
            Я не знаю такой команды 😢
            
            _Я надресирован выполнять команды из /help_
            """;
}
