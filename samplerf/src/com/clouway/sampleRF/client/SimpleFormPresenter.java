package com.clouway.sampleRF.client;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;


/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class SimpleFormPresenter implements Presenter, SimpleView.Presenter {


  private final PersonRequestFactory requestFactory;
  private final SimpleView simpleView;

  public SimpleFormPresenter(PersonRequestFactory requestFactory, SimpleView simpleView) {
    this.requestFactory = requestFactory;
    this.simpleView = simpleView;
  }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    simpleView.setPresenter(this);
    container.add((Widget) simpleView);
  }


  @Override
  public void onSaveButtonClicked() {
    addPerson();
  }

  @Override
  public void onGetButtonClicked() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public void addPerson(){
    String name = simpleView.getNameValue();
    String nick = simpleView.getNickValue();
    requestFactory.personRequest().save(name, nick).fire(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {
        simpleView.showSuccessMessage();
      }

    });
  }


}
