package com.diling.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

@Service
public class CameraMonitoringService {
    private static Logger logger = LoggerFactory.getLogger(CameraMonitoringService.class);

    @Autowired
    private ProcessService processService;

    @Async
    public void monitoring(String host, int port) {
        while (true) {
            try (Socket socket = new Socket(host, port)) {
                logger.info("Start monitoring port=" + port);
                try (InputStream is = socket.getInputStream()) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(is));
                    String socketInput;
                    while ((socketInput = in.readLine()) != null) {
                        processService.saveRecord(socketInput, new Date(), String.valueOf(port));
                    }
                } catch (IOException e) {
                    logger.error("Failed to get socket input stream for port=" + port, e);
                }
            } catch (IOException e) {
                logger.error("Failed to initialize client for host=" + host + " and port=" + port, e);
            }


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
