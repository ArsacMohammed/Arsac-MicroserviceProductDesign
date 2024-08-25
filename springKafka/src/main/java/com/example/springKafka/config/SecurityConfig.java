//package com.example.springKafka.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtDecoders;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SecurityConfig {
//
//    /**
//     * For the backend-resources, I indicate that all the endpoints are protected.
//     * To request any endpoint, the OAuth2 protocol is necessary, using the server configured and with the given scope.
//     * Thus, a JWT will be used to communicate between the backend-resources and backend-auth when backend-resources
//     * needs to validate the authentication of a request.
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeHttpRequests ->
//                        authorizeHttpRequests
//                                .requestMatchers("/**")
//                                .hasAuthority("SCOPE_message.read")
//                                .anyRequest()
////                                .authenticated()
//                )
//                .oauth2ResourceServer(oauth2ResourceServer ->
//                        oauth2ResourceServer
//                                .jwt(jwt ->
//                                        jwt.decoder(jwtDecoder())
//                                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
//                                )
//                );
//        return http.build();
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return JwtDecoders.fromIssuerLocation("http://localhost:9000");
//    }
//
//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        // Configure the converter if needed
//        return converter;
//    }
//}