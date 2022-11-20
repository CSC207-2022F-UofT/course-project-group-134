package chat_use_case;

import chat_use_case.boundaries.ChatDsBoundary;
import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatSendMessageModel;
import chat_use_case.models.ChatLogRequestModel;
import entities.Chat;
import entities.ChatMessage;
import entities.User;

import java.util.ArrayList;

public class ChatDsGateway implements ChatDsBoundary {

    ArrayList<Chat> chats ;
    public ChatDsGateway(){
        this.chats = new ArrayList<>();
    }

    //todo make into an acutal db



    public Chat getChat(User user1, User user2){
        for(Chat c : chats){
            User[] u = c.getUsers();
            if((user1.getEmail().equals(u[0].getEmail()) && user2.getEmail().equals(u[1].getEmail())) ||
                    (user1.getEmail().equals(u[0].getEmail()) && user2.getEmail().equals(u[1].getEmail())) ){
                return c;
            }
        }
        return null;
    }

    @Override
    public void createChat(ChatCreationRequestModel m) {
        User[] u = m.getUsers();
        Chat c = new Chat(u[0], u[1]);
        chats.add(c);
        //todo: what if chat already exists? too bad!
    }

    @Override
    public void sendMessage(ChatSendMessageModel m) {
        User sender = m.getSender();
        User reciever = m.getReciever();
        Chat c =  getChat(sender, reciever);
        c.sendMessage(new ChatMessage(sender, m.getMessage()));
    }

    @Override
    public ChatDataRecieveModel getMessageList(ChatLogRequestModel rq) {
        User[] u = rq.getUsers();
        Chat c = getChat(u[0], u[1]);
        if(c == null){
            return new ChatDataRecieveModel(new ArrayList(), false);
        }
        return new ChatDataRecieveModel(c.getChatLog(), true);
    }
}
