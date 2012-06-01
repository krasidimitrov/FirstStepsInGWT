package com.onlinebank.client.presenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.onlinebank.client.BankServiceAsync;
import com.onlinebank.client.exception.IncorrectDataFormatException;
import com.onlinebank.client.exception.InsufficientBalanceException;
import com.onlinebank.client.view.UserPanelView;

import java.math.BigDecimal;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class UserPanelPresenter implements Presenter, UserPanelView.Presenter{

  private final BankServiceAsync rpcService;
  private final EventBus eventBus;
  private final UserPanelView userPanelView;

  public UserPanelPresenter(BankServiceAsync rpcService, EventBus eventBus, UserPanelView userPanelView) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.userPanelView = userPanelView;
  }

  @Override
  public void go(HasWidgets container) {
    container.clear();
    userPanelView.setPresenter(this);
    container.add((Widget) userPanelView);
  }

  @Override
  public void onDepositButtonClicked() {
    deposit();
  }

  @Override
  public void onWithdrawButtonClicked() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void onLogoutButtonClicked() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public void deposit() {

    rpcService.deposit(new BigDecimal(10), new AsyncCallback<BigDecimal>() {
      @Override
      public void onFailure(Throwable caught) {
        if (caught instanceof IncorrectDataFormatException){
          userPanelView.setStatusMessage("");
        }
      }

      @Override
      public void onSuccess(BigDecimal result) {
        userPanelView.setStatusMessage("");
        userPanelView.setBalanceShown(result.toString());
      }
    });
  }

  public void withdraw() {

    rpcService.withdraw(new BigDecimal(10), new AsyncCallback<BigDecimal>() {
      @Override
      public void onFailure(Throwable caught) {
        if(caught instanceof  IncorrectDataFormatException){
          userPanelView.setStatusMessage("");
        }
        if(caught instanceof InsufficientBalanceException){
          userPanelView.setStatusMessage("");
        }
      }

      @Override
      public void onSuccess(BigDecimal result) {
        userPanelView.setStatusMessage("");
        userPanelView.setBalanceShown(result.toString());
      }
    });
  }
}
