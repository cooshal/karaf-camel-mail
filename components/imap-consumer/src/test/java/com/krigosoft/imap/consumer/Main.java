package com.krigosoft.imap.consumer;

import org.apache.camel.test.blueprint.CamelBlueprintHelper;
import org.osgi.framework.BundleContext;

public class Main {
    
    public static void main(String[] args) throws Exception{
        //create context and embedded broker
        BundleContext context = CamelBlueprintHelper.createBundleContext("imap-consumer-context", "OSGI-INF/blueprint/imap-consumer-context.xml", false);
    }
}