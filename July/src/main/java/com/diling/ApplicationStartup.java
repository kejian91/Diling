package com.diling;

import com.diling.configuration.Monitoring;
import com.diling.service.CameraMonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//@Component
public class ApplicationStartup {
    private static Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
    @Autowired
    private Monitoring monitoring;
    @Autowired
    private CameraMonitoringService cameraMonitoringService;

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("onApplicationEvent");
        List<Socket> sockets = initMonitoringServer();
        sockets.forEach(socket -> cameraMonitoringService.monitoring(socket));
    }

    private List<Socket> initMonitoringServer() {
        List<Socket> sockets = new ArrayList<>();
        List<String> clients = monitoring.getClients();
        logger.info("monitoring=" + clients);
        clients.forEach(client -> {
            String[] clientInfo = client.split(":");
            if (clientInfo.length != 2) {
                throw new RuntimeException("Client configuration is not correct.");
            }

            String hostName = clientInfo[0];
            Integer portNumber = Integer.parseInt(clientInfo[1]);
            try {
                Socket socket = new Socket(hostName, portNumber);
                sockets.add(socket);
            } catch (IOException e) {
                logger.error("Failed to init client for " + client, e);
                throw new RuntimeException(e);
            }
        });
        return sockets;
    }
}
