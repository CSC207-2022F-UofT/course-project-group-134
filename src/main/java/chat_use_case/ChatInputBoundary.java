package chat_use_case;

import chat_use_case.models.ChatCreationModel;

public interface ChatInputBoundary {

    public void CreateChat(ChatCreationModel c);

    public void SendMessage();

}
