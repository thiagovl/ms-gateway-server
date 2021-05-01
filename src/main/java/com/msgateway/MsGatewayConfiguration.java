package com.msgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;

@Configuration
public class MsGatewayConfiguration {
	
	@Value("${URI_INDEX_USERS}")
	private String apiIndex;	
	
	@Value("${URI_MICROSERVICE_USERS}")
	private String uriMicroservice;	
	
	@Value("${URI_AUTHENTICATE}")
	private String uriAuthenticate;
	
	@Value("${URI_USERS}")
	private String uriUsers;
	
	@Bean
	public ServerCodecConfigurer serverCodecConfigurer() {
	   return ServerCodecConfigurer.create();
	}

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
		return builder.routes()
				// Route to /api/index in the ms-auth microservice 
				.route(p -> p.path(apiIndex)	
						.uri(uriMicroservice))	
				// Route to /authentication in the ms-auth microservice 
				.route(p -> p.path(uriAuthenticate)
						.uri(uriMicroservice))	
				// Route to /apiusers/** in the ms-auth microservice - GET | POST | PUT | DELETE
				.route(p -> p.path(uriUsers)
						.uri(uriMicroservice))	
		.build();
	}
	
	
}
