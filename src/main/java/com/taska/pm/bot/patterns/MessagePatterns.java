package com.taska.pm.bot.patterns;


public final class MessagePatterns {

    public static final String PARSE_MODE_MARKDOWN = "Markdown";
    public static final String START_COMMAND = """
            *Привет, %s!* 👋
            Напиши свой логин с системы TaskaPM, чтобы я мог тебя проверить.
            """;

    public static final String USER_REGISTER_CHECK_SUCCESS = """
            Отлично, я тебя узнал!
            Теперь я буду оповещать тебя о поступлении новых задач и напоминать о дедлайнах!
            
            _Подробнее ты можешь ознакомиться с моими командами, написав /help_
            """;

    public static final String USER_REGISTER_CHECK_FAIL = """
            К сожалению, не вижу тебя.
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

    public static final String NEW_TASK_NOTIFICATION = """
            Назначена новая задача [%s](http://127.0.0.1:8080/projects/%s/%s)!
           
            *Заголовок:* `%s`

            *Сроки выполнения:*
            `%s` - `%s`
            
            *Создал:* `%s`
            """;

    public static final String TASK_CHANGES_NOTIFICATION = """
            Изменения в задаче [%s](http://127.0.0.1:8080/projects/%s/%s)!
            
            *Заголовок:* `%s`
            
            *Сроки выполнения:*
            `%s` - `%s`
            
            *Отредактировал:* `%s`
            """;
}
