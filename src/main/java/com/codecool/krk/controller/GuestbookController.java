package com.codecool.krk.controller;

import com.codecool.krk.model.GuestbookEntry;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class GuestbookController implements HttpHandler {

    private List<GuestbookEntry> entries;

    public GuestbookController(){
        super();
        entries = new ArrayList<>();
    }


    public void handle(HttpExchange httpExchange) throws IOException {

        String response = "";
        String method = httpExchange.getRequestMethod();

        if(method.equals("GET")){
            response = createHtml();
        }

        if(method.equals("POST")) {
            InputStreamReader ipsr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(ipsr);
            String formData = br.readLine();

            System.out.println(formData);

            createEntryFromForm(formData);
            response = createHtml();
        }

        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void createEntryFromForm(String formData) throws UnsupportedEncodingException{
        List<String> valuesList = new ArrayList<>();

        String[] pairs = formData.split("&");
        for(String pair : pairs){
            String[] keyValue = pair.split("=");

            String value = new URLDecoder().decode(keyValue[1], "UTF-8");

            valuesList.add(value);
        }
        entries.add(new GuestbookEntry(valuesList.get(0), valuesList.get(1)));
    }

    private String createHtml() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        StringWriter writer = new StringWriter();
        Context context = new Context();
        context.setVariable("entries", this.entries);

        templateEngine.process("guestbook", context, writer);
        return writer.toString();
    }
}