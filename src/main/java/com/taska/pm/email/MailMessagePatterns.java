package com.taska.pm.email;


public final class MailMessagePatterns {

    public static final String REGISTER_MESSAGE_SUBJECT = """
        Доступ к TaskaPM
    """;
    public static final String REGISTER_MESSAGE_TEXT = """
        Добро пожаловать в команду!
        Доступ к PM-системе:
        Логин: %s
        Пароль: %s
    """;
}
