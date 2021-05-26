package com.example.camel.rest2amqp.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSenderRouter extends RouteBuilder {

    public static final String ACTIVEMQ_REST_2_AMPQ_EXPORT = "activemq:rest2ampq.export";
    public static final String DOC_CONTEXT_PATH = "/rest";
    public static final String API_PATH = "/doc";

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .contextPath(DOC_CONTEXT_PATH)
                .apiContextPath(API_PATH)
                .apiProperty("api.title", "Camel 2 Amqp Test")
                .enableCORS(true);

        /**
         * curl --location --request POST 'http://localhost:8080/rest/rest2ampq' \
         * --header 'Content-Type: application/json' \
         * --data-raw '{ "route": "fromRestEndpoint" }'
         */
        rest("/rest2ampq").post().route()
                .log("${body}")
                .to(ACTIVEMQ_REST_2_AMPQ_EXPORT);


        from("timer:active-mq-timer?period=60000")
                .transform().constant("{ \"route\": \"fromTimer\" }")
                .log("${body}")
                .to(ACTIVEMQ_REST_2_AMPQ_EXPORT);

//        from("file:files/json")
//                .log("${body}")
//                .to(ACTIVEMQ_REST_2_AMPQ_EXPORT);

        /**
         * Publish manually:
         * { "route": "fromExportQueue" }
         */
//        from("jms:rest2ampq.import")
//                .log("${body}")
//                .to(ACTIVEMQ_REST_2_AMPQ_EXPORT);
    }
}
