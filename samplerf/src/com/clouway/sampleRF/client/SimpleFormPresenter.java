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
  private PersonProxy personProxyForEdit;
  private PersonProxy personProxyForAdd;
  private SimpleViewImpl.Driver editorDriver;



  public SimpleFormPresenter(PersonRequestFactory requestFactory, SimpleView simpleView) {
    this.requestFactory = requestFactory;
    this.simpleView = simpleView;
    editorDriver = simpleView.getDriver();
    editorDriver.initialize(this.requestFactory,(SimpleViewImpl) this.simpleView);
    setUpToAddNewPerson();
  }

  private void setUpToAddNewPerson(){
    PersonRequest request = requestFactory.personRequest();
    personProxyForAdd = request.create(PersonProxy.class);
    editorDriver.edit(personProxyForAdd, request);
    
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
  public void onSelectButtonClicked() {
    selectPerson();
  }

  @Override
  public void onEditButtonClicked() {
    editPerson();
  }

  public void addPerson(){
    PersonRequest personRequest = (PersonRequest) editorDriver.flush();
    personRequest.save(personProxyForAdd).to(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {
        simpleView.showSuccessMessage();
      }
    }).fire();
    
  }

  public void selectPerson(){
    PersonRequest personRequest = requestFactory.personRequest();
    personRequest.getPersonFromNick(simpleView.getNickValue()).fire(new Receiver<PersonProxy>() {
      @Override
      public void onSuccess(PersonProxy response) {
        personProxyForEdit = response;

        editorDriver.edit(personProxyForEdit, requestFactory.personRequest());

      }
    });
  }



  public void editPerson(){

    PersonRequest request = (PersonRequest) editorDriver.flush();
    request.update(personProxyForEdit).to(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {
        simpleView.showSuccessMessage();
      }

    }).fire();

  }


}
