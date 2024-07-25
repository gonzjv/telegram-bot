package tutorial;

import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot{
    private final String ADMIN_CHAT_ID = "-1002192550630";
    @Override
    public String getBotUsername() {
        return "KrymMost_admin_bot";
    }
    @Override
    public String getBotToken() {
        return "7375300549:AAHcgQqgYbRkirqqkuR780farHVRWHMFx0g";  
    }

    @Override
    public void onUpdateReceived(Update arg0) {
        Message userMessage = arg0.getMessage();
        String text = userMessage.getText();
        User user = userMessage.getFrom();
        String userName = user.getUserName();
        Long userId = user.getId();

        //System.out.println("Message:   " + userMessage);        
        System.out.println("Text:   " + text);        
        System.out.println("getFrom:   " + user);        
        System.out.println("username:   " + userName);        
        System.out.println("userID:   " + userId); 
        
/*         SendMessage messageToAdmin = SendMessage.builder()
            .chatId(ADMIN_CHAT_ID)
            .text(userId + userName + text)
            .build();
 */

        ForwardMessage forwardMessage = new ForwardMessage();
        forwardMessage.setChatId(ADMIN_CHAT_ID);
        forwardMessage.setFromChatId(String.valueOf(userId));
        forwardMessage.setMessageId(userMessage.getMessageId());
        /* 
         * 
         SendMessage messageToAdmin = new SendMessage();
         messageToAdmin.setChatId(ADMIN_CHAT_ID.toString());
         messageToAdmin.setText(userId + userName + text);
         */

        try {
            execute(forwardMessage);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
        
        
    }
}
