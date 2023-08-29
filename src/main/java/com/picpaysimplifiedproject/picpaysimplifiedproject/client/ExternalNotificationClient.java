package com.picpaysimplifiedproject.picpaysimplifiedproject.client;


import com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.client.AuthorizerResponseDTO;
import com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.client.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${spring.client.urls.external-notification}", name = "notification")
public interface ExternalNotificationClient {

    @PostMapping("notify")
    ResponseEntity<Void> sendNotification(@RequestBody NotificationDTO notification);
}
