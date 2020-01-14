package cdi.sample.observer;

import javax.inject.Inject;

public class SimpleService {

  @Inject @SimpleClientWithArgs private SimpleClient client;

  public void greet() {
    this.client.showId();
  }
}
