package chat_use_case;

import chat_use_case.boundaries.ChatDsBoundary;
import chat_use_case.models.ChatCreationModel;
import chat_use_case.models.ChatRecieveMessagesModel;
import chat_use_case.models.ChatSendMessageModel;
import chat_use_case.models.RequestChatlogModel;
import entities.Chat;
import entities.ChatMessage;
import entities.User;

import java.util.ArrayList;

public class ChatDsGateway implements ChatDsBoundary {


    //todo make into an acutal db

    ArrayList<Chat> chats ;


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


    public void sendMessage(User sender, User reciever, String Message){

        Chat c = getChat(sender, reciever);


    }

    @Override
    public void createChat(ChatCreationModel m) {
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
    public ChatRecieveMessagesModel getMessageList(RequestChatlogModel rq) {

    }
}
