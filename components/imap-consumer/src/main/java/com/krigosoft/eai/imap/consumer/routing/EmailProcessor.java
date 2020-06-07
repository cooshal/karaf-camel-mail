package com.krigosoft.eai.imap.consumer.routing;

import java.util.Map;
import javax.activation.DataHandler;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.LoggerFactory;

public class EmailProcessor implements Processor {

    protected org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    
    @Override
    public void process(Exchange exchange) throws Exception {

        Map<String, DataHandler> attachments = exchange.getIn().getAttachments();
        
        System.out.println(attachments.size());

        if (attachments.size() > 0) {
            for (String name : attachments.keySet()) {
                DataHandler dh = attachments.get(name);
                // get the file name
                String filename = dh.getName();
                
                log.info(filename);

                // check if the attachment is an xml file
                // if not move to another attachment
                if(!filename.endsWith(".xml")) {
                    continue;
                }
                
                log.info("email has an xml attachment");
                
                // get the content and convert it to byte[]
                byte[] data = exchange
                        .getContext()
                        .getTypeConverter()
                        .convertTo(byte[].class, dh.getInputStream());
                
                exchange.getIn().setBody(data);
                break;
            }
        }

    }

}
