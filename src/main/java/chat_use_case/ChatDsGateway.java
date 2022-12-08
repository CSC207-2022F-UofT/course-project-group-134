package chat_use_case;

import chat_use_case.boundaries.ChatDsBoundary;
import chat_use_case.models.ChatCreationRequestModel;
import chat_use_case.models.ChatDataRecieveModel;
import chat_use_case.models.ChatSendMessageModel;
import chat_use_case.models.ChatLogRequestModel;
import entities.*;
import user_access_use_case.SignUpDsGateway;
import user_access_use_case.SignUpGateway;

import java.io.*;
import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is the Data Storage gateway for the Chat. This follows the singleton design pattern; only one instance of this
 * class should exist.
 *
 * @author Aaron Ma
 */
public class ChatDsGateway implements ChatDsBoundary {
    /**
     * The list of chats
     */
    List<Chat> chats;

    /**
     * The number of chats that are being managed. This is initialized as one but gets changed as the program operates.
     */
    private int managed_chats = 1;

    /**
     * File storage location
     */
    private static final String STORAGE_LOCATION = "./src/main/java/data_storage/chat_store";
    /**
     * File storage directory
     */
    private final File storage_dir;
    /**
     * Singleton instance initialized as one, but is updated by getInstance().
     */
    private static ChatDsGateway instance = null;

    /**
     * Gets the instance of this class.
     *
     * @return The instance of this class that is used.
     * @throws IOException Exception is thrown if file cannot be read/written/created.
     */
    public static ChatDsGateway getInstance() throws IOException{
        if(instance == null){
            instance = new ChatDsGateway();
        }
        return instance;
    }

    /**
     * Constructor. This initializes the instance.
     */
    private ChatDsGateway() {
        this.chats = new ArrayList<>();
        this.storage_dir = new File(STORAGE_LOCATION);
        if (!storage_dir.exists()){
            storage_dir.mkdir();
        }else{
            chats = read();
        }
        System.out.println(chats.size());

    }

    /**
     * This gets the file name for the input chat.
     * @param c File to find the file name for
     * @return Returns the file that the chat is stored in
     */
    private String getChatFileName(Chat c){

        String[]u = {c.getUsers()[0].getEmail(), c.getUsers()[1].getEmail()};
        Arrays.sort(u);
        return "Chat_"
                + u[0].split("@")[0]
                + "_" + u[1].split("@")[0];
    }

    /**
     * Creates chat, adds it to database
     * @param m information needed to create the chat(sender, receiver)
     * @throws IOException if something goes wrong with the file/IO system, then we throw this error
     */
    @Override
    public void createChat(ChatCreationRequestModel m) throws IOException {
        User[] u = m.getUsers();
        Chat c = new Chat(u[0], u[1]);
        chats.add(c);
        writeChat(c);
    }

    /**
     * Sends a message, updates the database accordingly
     * @param m The message information (sender, receiver, message text)
     * @throws IOException if something goes wrong with the file/IO system, then we throw this error
     */
    @Override
    public void sendMessage(ChatSendMessageModel m) throws IOException {
        User sender = m.getSender();
        User reciever = m.getReciever();
        Chat c =  getChat(sender, reciever);
        ChatMessage msg = new ChatMessage(sender, m.getMessage());
        c.sendMessage(msg);
        writeChat(c);
    }

    /**
     * This retrieves the list of messages from the database
     * @param rq information needed to retrieve a list of messages (the two users)
     * @return The list of messages, in the form of a ChatDataRecieveModel
     */
    @Override
    public ChatDataRecieveModel getMessageList(ChatLogRequestModel rq) {
        User[] u = rq.getUsers();
        Chat c = getChat(u[0], u[1]);
        if(c == null){
            return new ChatDataRecieveModel(new ArrayList(), false);
        }
        return new ChatDataRecieveModel(c.getChatLog(), true);
    }

    /**
     * Writes a chat to the database
     * @param c chat to be written to database
     * @throws IOException if something goes wrong with the file/IO system, then we throw this error
     */
    private void writeChat(Chat c) throws IOException{
        String fileName = this.STORAGE_LOCATION +   "/" + getChatFileName(c);
        File f = new File(fileName);
        f.delete();
        f.createNewFile();
        BufferedWriter out  = new BufferedWriter(new FileWriter(fileName));
        out.write(c.getUsers()[0].getEmail() + "," + c.getUsers()[1].getEmail() + "\n");

        for(ChatMessage msg : c.getChatLog()){
            out.write(msg.getSender().getEmail() + "," + msg.getContents() + "\n") ;
        }
        out.flush();
        out.close();
    }

    /**
     * Reads the database and fetches the list of chats
     * @return the list of chats
     */
    private List<Chat> read(){
        List<Chat> rtn = new ArrayList<Chat>();
        System.out.println("hi");

        for(File f : storage_dir.listFiles()){


            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String[] emails = br.readLine().split(",");
                SignUpDsGateway userGateway = new SignUpGateway();
                BuyerFactory buyerFactory = new BuyerFactory();
                SellerFactory sellerFactory = new SellerFactory();
                UserFactory userFactory = new UserFactory(buyerFactory, sellerFactory);

                ArrayList<ChatMessage> messages = new ArrayList<>();
                User user1 = userGateway.readUser(emails[0], userFactory);
                User user2 = userGateway.readUser(emails[1], userFactory);
                if(user1 == null || user2 == null){
                    continue;
                }

                //TODO: USer might not exist lol
                String s = br.readLine();

                while(s != null){
                    String[] tokens = s.split(",");

                    User sender = tokens[0].equals(user1.getEmail()) ? user1 : user2;

                    StringBuilder sb = new StringBuilder("");
                    for(int i = 1; i < tokens.length-1; i++){
                        sb.append(tokens[i]);
                    }
                    sb.append(tokens[tokens.length-1]);
                    messages.add(new ChatMessage(sender, sb.toString()));
                    s = br.readLine();
                }
                br.close();
                Chat c = new Chat(user1, user2, messages);
                rtn.add(c);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return rtn;
    }

    /**
     * Gets the chats between two different users
     * @param user1 One of the users that is sending/receiving a message
     * @param user2 The other user that is sending/receiving a message
     * @return The Chat that represents all the chat messages sent and received between the two input users.
     */
    private Chat getChat(User user1, User user2){
        for(Chat c : chats) {
            User[] u = c.getUsers();
            System.out.println("getchat loop:s");
            System.out.println(u[0].getEmail());
            System.out.println(u[1].getEmail());

            if ((user1.getEmail().equals(u[0].getEmail()) && user2.getEmail().equals(u[1].getEmail())) ||
                    (user1.getEmail().equals(u[1].getEmail()) && user2.getEmail().equals(u[0].getEmail()))) {
                return c;
            }
        }
        Chat c = new Chat(user1, user2);
        chats.add(c);
        return c;
    }

}
