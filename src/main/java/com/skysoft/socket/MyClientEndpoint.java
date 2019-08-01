package com.skysoft.socket;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class MyClientEndpoint {
	private Session session = null;
	private MessageHandler messageHandler;
	
	private static MyClientEndpoint instance = null;
	
	public static MyClientEndpoint getInstance() {
		if (instance == null) {
			synchronized (MyClientEndpoint.class) {
				if (instance == null) {
					instance = new MyClientEndpoint();
				}
			}
		}
		return instance;
	}
	
	 public MyClientEndpoint()
	    {
	        URI uri;
	        try
	        {
	            uri = new URI("ws://localhost:8080/WebSocket/server");
	            try 
	            {
	                ContainerProvider.getWebSocketContainer().connectToServer(this, uri);
	            } catch (Exception e)
	            {
	                e.printStackTrace();
	            }
	        } catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	    }
	     public MyClientEndpoint(Object obj ,URI endpointURI) {
	            try {
	                WebSocketContainer container = ContainerProvider.getWebSocketContainer();
	                container.connectToServer(obj, endpointURI);
	            } catch (Exception e) {
	                throw new RuntimeException(e);
	            }
	        }
	     
	    @OnOpen
	    public void handleOpen(Session session) {
	        this.session = session;
	        System.out.println("Connected to Server!");
	    }

	    @OnMessage
	    public void handleMessage(String message) {
	        System.out.println("Response from Server: " + message);
	        if (this.messageHandler != null) {

	            this.messageHandler.handleMessage(message);
	        }
	    }

	    @OnClose
	    public void handleClose() {
	        System.out.println("Disconnected to Server!");
	    }

	    @OnError
	    public void handleError(Throwable t) {
	        t.printStackTrace();
	    }

	    public void addMessageHandler(MessageHandler messageHandler) {
	        this.messageHandler = messageHandler;
	    }

	    public void sendMessage(String message) throws Exception {
	        // this.session.getBasicRemote().sendText(message);
	        this.session.getAsyncRemote().sendText(message);
	    }

	    public void disconnect() throws Exception {
	        this.session.close();
	    }

	    public static interface MessageHandler
	    {
	        public void handleMessage(String message);
	    }
	
}
