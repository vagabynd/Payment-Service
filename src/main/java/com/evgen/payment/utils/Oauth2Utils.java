package com.evgen.payment.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Component;

@Component
public class Oauth2Utils {

  private final String authorizationRequestBaseUri = "oauth2/authorization";
  private final ClientRegistrationRepository clientRegistrationRepository;

  @Autowired
  public Oauth2Utils(ClientRegistrationRepository clientRegistrationRepository) {
    this.clientRegistrationRepository = clientRegistrationRepository;
  }

  public void setOauth2AuthenticationUrls(Map<String, String> oauth2AuthenticationUrls) {
    Iterable<ClientRegistration> clientRegistrations = null;
    ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
        .as(Iterable.class);
    if (type != ResolvableType.NONE &&
        ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
      clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
    }
    if (clientRegistrations != null) {
      clientRegistrations.forEach(registration -> oauth2AuthenticationUrls
          .put(registration.getClientName(), authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
    }
  }
}