package com.diling.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

@Service
public class CameraMonitoringService {
    private static Logger logger = LoggerFactory.getLogger(CameraMonitoringService.class);

    @Async
    public void monitoring(Socket socket) {
        while (true) {
            String port = String.valueOf(socket.getPort());
            logger.info("Start monitoring port=" + port);
            try (InputStream is = socket.getInputStream()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String socketInput;
                StringBuilder messageBuilder = new StringBuilder();
                while ("end".equals(socketInput = in.readLine())) {
                    messageBuilder.append(socketInput);
                }
                logger.info("Receive message from port=" + port + " is '" + messageBuilder.toString() + "'");
            } catch (IOException e) {
                logger.error("Failed to get socket input stream for port=" + port, e);
            }
        }
    }
}
