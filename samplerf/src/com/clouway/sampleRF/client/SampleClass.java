package com.clouway.sampleRF.client;

import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class SampleClass extends Receiver<Void>{
  @Override
  public void onSuccess(Void response) {
    Window.alert("Person Saved Successful!");
  }
}
