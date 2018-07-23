package com.diling;

import com.diling.configuration.Monitoring;
import com.diling.service.CameraMonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationStartup {
    private static Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
    @Autowired
    private Monitoring monitoring;
    @Autowired
    private CameraMonitoringService cameraMonitoringService;

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("onApplicationEvent");
        List<String> clients = monitoring.getClients();
        clients.forEach(client -> {
            String[] clientInfo = client.split(":");
            if (clientInfo.length != 2) {
                throw new RuntimeException("Client configuration is not correct.");
            }

            String hostName = clientInfo[0];
            Integer portNumber = Integer.parseInt(clientInfo[1]);
            cameraMonitoringService.monitoring(hostName, portNumber);
        });
    }
}
