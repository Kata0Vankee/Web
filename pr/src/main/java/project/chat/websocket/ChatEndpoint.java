package project.chat.websocket;

import project.controller.SaveChat;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.CloseReason;

import project.model.Message;


@ServerEndpoint(value = "/chat/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatEndpoint {
    private Session session;
    private static final Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();
    static boolean checkAll = true;
    static String userAll;
    public String imageMessage;
    public SaveChat save = new SaveChat();
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {            
        session.getUserProperties().put("username", username);
        this.session = session;
        chatEndpoints.add(this);
        users.put(session.getId(), username);              
}

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException, SQLException, ClassNotFoundException {
        //message.setFrom(users.get(session.getId()));
        String sender = message.getFrom();
        String receiver = message.getTo();
        String content = message.getContent();
        String type = message.getType();
        System.out.println(sender+" "+type+" "+content.length()+" "+receiver+" "+message.getTypeMessage());
        if (imageMessage == null){
            imageMessage = "";
        }
        if (message.getTypeMessage().equals("single")) {
            if (type.equals("text")) {            
            save.SaveThisMessage(sender, receiver, content);
            broadcast(message);
        } else {
            if (type.equals("image")) {
                imageMessage += content;
                System.out.println(content);                
            } else {
                imageMessage += content;
                System.out.println(content);
                System.out.println(imageMessage.length());
                save.SaveThisMessage(sender, receiver, imageMessage);
                imageMessage = "";                
            }
            broadcast(message);
        }
        } else {
            if (type.equals("text")) {       
                System.out.println("group message save");
            save.saveThisMessageGroup(message);
            broadcast(message);
        } else {
            if (type.equals("image")) {
                imageMessage += content;
                System.out.println(content);                
            } else {
                imageMessage += content;
                System.out.println(content);
                System.out.println(imageMessage.length());
                Message messTemp = new Message();
                messTemp.setContent(imageMessage);
                messTemp.setFrom(message.getFrom());
                messTemp.setTo(message.getTo());
                save.saveThisMessageGroup(messTemp);
                imageMessage = "";                
            }
            broadcast(message);
        }
        }
               
        /*System.out.println("user :" +users);
        System.out.println("chat end point :" +chatEndpoints);
        for (Map.Entry<String,String> user : users.entrySet()) {
            String key =  user.getKey();
            String value = user.getValue();
            System.out.println("user key, value :" + key + " " + value);
        }
        System.out.println("gui toi "+ message.getTo());
        String getTo = message.getTo();
        getTo.split(" ");
        System.out.println("");*/
    }

    @OnClose
    public void onClose(Session session, CloseReason cl) throws IOException, EncodeException {
        chatEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        System.out.println(cl.getCloseCode());
        System.out.println("close");
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Message message) throws IOException, EncodeException {
        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote()
                        .sendObject(message);
                    //System.out.println("end point "+endpoint.session.getUserProperties().get("username"));                    
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}