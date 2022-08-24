import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class TgBot extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message, update);

        firstKeyboard(chatId, message);

        if (message.equals("Forward")||message.equals("Back")){
             keyboard(chatId, message);
         }

    }


    public synchronized void sendMsg(String chatId, String message, Update update){

        if ("/run".equals(message)) {
            String firstName = update.getMessage().getFrom().getFirstName();
            SendMessage response = new SendMessage();
            response.enableMarkdown(true);
            response.setChatId(chatId);
            response.setText("Hi, " + firstName + ", bot is successfully launched!");


            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void firstKeyboard(String chatId, String msg){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> firstKeyboard = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        row1.add("Go Shopping");
        row2.add("Shop's Info");
        firstKeyboard.add(row1);
        firstKeyboard.add(row2);

        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setKeyboard(firstKeyboard);
        keyboardMarkup.setOneTimeKeyboard(false);
        message.setReplyMarkup(keyboardMarkup);


        switch (msg) {
            case "/start" -> {
                message.setText("Welcome to our shop!");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            case "Go Shopping" -> keyboard(chatId, msg);

            case "Shop's Info" -> {
                message.setText("Info");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    long pageCounter = 1;

    public synchronized void keyboard(String chatId, String msg){

        SendMessage message = new SendMessage();
        message.setChatId(chatId);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();
        row1.add("Back");
        row1.add(String.valueOf(pageCounter));
        row1.add("Forward");
        keyboard.add(row1);

        if (msg.equals("Forward")){
            pageCounter++;
            row1.set(1, String.valueOf(pageCounter));
            System.out.println("123");
        } else if (msg.equals("Back") && pageCounter > 1) {
            pageCounter--;
            row1.set(1, String.valueOf(pageCounter));
            System.out.println("2345");
        }


        message.setText(String.valueOf(pageCounter));


        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }


    @Override
    public String getBotUsername() {
        return "R231Bot";
    }

    @Override
    public String getBotToken() {
        return "5793024716:AAHBn08nBwGe0D864bLUFX4UtFhLt412ppk";
    }
}
