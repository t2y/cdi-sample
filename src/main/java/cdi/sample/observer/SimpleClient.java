package cdi.sample.observer;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleClient {

  private final int id;
  private final String name;

  public SimpleClient(int id) {
    this.id = id;
    this.name = "simple";
  }

  @PostConstruct
  public void afterCreate() {
    log.info("SimpleClient is created");
  }

  public void showId() {
    log.info("SimpleClient.id: " + String.valueOf(this.id));
  }
}
