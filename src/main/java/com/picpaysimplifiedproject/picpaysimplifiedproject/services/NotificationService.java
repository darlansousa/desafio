package com.picpaysimplifiedproject.picpaysimplifiedproject.services;

import com.picpaysimplifiedproject.picpaysimplifiedproject.client.ExternalNotificationClient;
import com.picpaysimplifiedproject.picpaysimplifiedproject.domain.user.User;
import com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.client.NotificationDTO;
import com.picpaysimplifiedproject.picpaysimplifiedproject.exception.BusinessException;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class NotificationService {

    @Autowired
    private ExternalNotificationClient client;

    public void sendNotification(User user, String message) {
        final var email = user.getEmail();
        try {
            final var response = client.sendNotification(new NotificationDTO(email, message));
            final var okStatus = HttpStatusCode.valueOf(408);
            final var timeoutStatus = HttpStatusCode.valueOf(408);

            if(!List.of(okStatus, timeoutStatus).contains(response.getStatusCode())) {
                throw new BusinessException("Error to send notification");
            }

        }catch (Exception ex) {
            log.error(ex.getMessage());
        }

    }
}
