package com.codecool.krk.controller;

import com.codecool.krk.model.GuestbookEntry;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

public class GuestbookController implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {

    }

    private String createHtml(List<GuestbookEntry> entries) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        StringWriter writer = new StringWriter();
        Context context = new Context();
        context.setVariable("entries", entries);

        templateEngine.process("guestbook", context, writer);

        return writer.toString();
    }
}