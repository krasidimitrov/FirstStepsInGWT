package com.clouway.sampleRF.client;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class EditPresenter implements Presenter, EditPersonView.Presenter{


  private final PersonRequestFactory requestFactory;
  private final EditPersonView editPersonView;

  public EditPresenter(PersonRequestFactory requestFactory, EditPersonView editPersonView){
    this.requestFactory = requestFactory;
    this.editPersonView = editPersonView;
  }


  @Override
  public void go(HasWidgets container) {
    container.clear();
    editPersonView.setPresenter(this);
    container.add((Widget) editPersonView);
  }


  @Override
  public void onEditButtonClicked() {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
