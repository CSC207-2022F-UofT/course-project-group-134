package chat_use_case.boundaries;

import chat_use_case.models.ChatRecieveMessagesModel;

public interface ChatOutputBoundary {

    public ChatRecieveMessagesModel getMessageList();

}
