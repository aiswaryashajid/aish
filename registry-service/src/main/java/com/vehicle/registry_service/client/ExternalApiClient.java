
package com.vehicle.registry_service.client;

import java.time.Duration;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import com.vehicle.registry_service.exception.TimeOutException;

@Service
public class ExternalApiClient {

  private static final Logger log = LoggerFactory.getLogger(ExternalApiClient.class);
  private final WebClient webClient;

  public ExternalApiClient(WebClient webClient) {
    this.webClient = webClient;
  }

  public <T> T get(String url, HttpHeaders headers, Class<T> responseType) {

    try {

      log.info("Calling external API: {}", url);
      log.debug("Request Headers: {}", headers);

      return webClient.get().uri(url).headers(h -> h.addAll(headers)).retrieve()

          // Convert to response Type
          .bodyToMono(responseType)

          // Timeout
          .timeout(Duration.ofSeconds(5))

          // Block
          .block();


    } catch (WebClientResponseException ex) {

      // 4xx errors
      log.error("Client error while calling API: {}", url);
      log.error("Status Code: {}", ex.getStatusCode());
      log.error("Response Body: {}", ex.getResponseBodyAsString(), ex);

      throw ex;

    } catch (WebClientRequestException ex) {

      // Connection refused / network issues
      log.error("Connection error (service down / refused): {}", url, ex);
      throw ex;


    } catch (Exception e) {

      log.error("Exception Occured: {}", url, e);
      if (e.getCause() instanceof TimeoutException) {
        throw new TimeOutException(e.getMessage());
      }

      throw e;
    }



  }
}
