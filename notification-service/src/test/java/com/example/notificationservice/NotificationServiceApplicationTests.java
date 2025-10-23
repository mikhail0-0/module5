package com.example.notificationservice;

import com.example.notificationservice.dto.SendEmailDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationServiceApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    static final String testEmailFrom = "test@service.com";
    static final String testLogin = "test";
    static final String testPassword = "test";


    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration
                    .aConfig()
                    .withUser(testEmailFrom, testLogin, testPassword))
                    .withPerMethodLifecycle(false);


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSendEmail() throws Exception {
        String testFromEmail = "notification@service.com";
        String testToEmail = "test@user.com";
        String testSubject = "testSubject";
        String testMessage= "testMessage";
        SendEmailDto dto = new SendEmailDto();
        dto.setFromEmail(testFromEmail);
        dto.setToEmail(testToEmail);
        dto.setSubject(testSubject);
        dto.setMessage(testMessage);

        mockMvc.perform(post("/mail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        var messages = greenMail.getReceivedMessages();
        assertThat(messages.length).isEqualTo(1);
        var targetMessage = messages[0];
        assertThat(targetMessage.getContent().toString()).isEqualTo(testMessage);
        assertThat(targetMessage.getSubject()).isEqualTo(testSubject);
    }

}
