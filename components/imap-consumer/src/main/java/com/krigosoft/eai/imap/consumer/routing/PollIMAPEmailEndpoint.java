package com.krigosoft.eai.imap.consumer.routing;

import org.apache.camel.builder.RouteBuilder;

public class PollIMAPEmailEndpoint extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("imaps://{{IMAP_SERVER_URL}}"
                + "?username={{IMAP_EMAIL_USER}}"
                + "&password={{IMAP_EMAIL_PASS}}"
                + "&unseen=true"
                + "&delete=false"
                + "&initialDelay=100"
                + "&delay={{IMAP_POLL_DURATION}}")
                .routeId("pollIMAPServerRoute")
                
                // process the email and extract attachments
                .process(new EmailProcessor())
                
                // save a copy of the original content
                .to("file:{{ORDERS_STORE_LOCATION}}")
                .to("log:info");
    }

}
