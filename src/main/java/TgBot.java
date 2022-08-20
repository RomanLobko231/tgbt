import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TgBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        String command = update.getMessage().getText();
        String firstName = update.getMessage().getFrom().getFirstName();

        switch (command) {
            case "/start" -> {
                String message = "Let's start!";
                SendMessage response = new SendMessage();
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);

                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            case "/run" -> {
                String message = "Hi, " + firstName + ", bot is successfully launched!";
                SendMessage response = new SendMessage();
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);

                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            default -> {
                String message = "I have only one command yet, please wait for updates";
                SendMessage response = new SendMessage();
                response.setChatId(update.getMessage().getChatId().toString());
                response.setText(message);

                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
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
