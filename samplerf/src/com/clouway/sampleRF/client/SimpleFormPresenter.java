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
  public void onSelectButtonClicked() {
    selectPerson();
  }

  @Override
  public void onEditButtonClicked() {
    editPerson();
  }

  public void addPerson(){
    PersonRequestFactory.PersonRequest personRequest = requestFactory.personRequest();
    PersonProxy personProxy = personRequest.create(PersonProxy.class);
    personProxy.setRealName(simpleView.getNameValue());
    personProxy.setNickName(simpleView.getNickValue());
    personProxy.setPhone(simpleView.getPhoneValue());
    personProxy.setOccupation(simpleView.getOccupationValue());
    personRequest.save(personProxy).fire(new Receiver<Void>() {
      @Override
      public void onSuccess(Void response) {
        simpleView.showSuccessMessage();
      }

    });
  }

  public void selectPerson(){
    PersonRequestFactory.PersonRequest personRequest = requestFactory.personRequest();
    personRequest.getPersonFromNick(simpleView.getNickValue()).fire(new Receiver<PersonProxy>() {
      @Override
      public void onSuccess(PersonProxy response) {
        personProxyForEdit = response;
        simpleView.setNameValue(response.getRealName());
        simpleView.setNickValue(response.getNickName());
        simpleView.setPhoneValue(response.getPhone());
        simpleView.setOccupationValue(response.getOccupation());
      }
    });
  }

  public void editPerson(){
    PersonRequestFactory.PersonRequest personRequest = requestFactory.personRequest();
    personRequest.getPersonFromNick(simpleView.getNickValue()).to(new Receiver<PersonProxy>() {
      @Override
      public void onSuccess(PersonProxy person) {
        PersonRequestFactory.PersonRequest updateRequest = requestFactory.personRequest();
        person = updateRequest.edit(person);
        person.setRealName(simpleView.getNameValue());
        person.setPhone(simpleView.getPhoneValue());
        person.setOccupation(simpleView.getOccupationValue());

        updateRequest.update(person).to(new Receiver<Void>() {
          @Override
          public void onSuccess(Void response) {
            simpleView.showSuccessMessage();
          }
        }).fire();
      }
    }).fire();

  }


}
