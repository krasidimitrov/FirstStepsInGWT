import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.onlinebank.client.BankServiceAsync;
import com.onlinebank.client.exception.IncorrectDataFormatException;
import com.onlinebank.client.exception.InsufficientBalanceException;
import com.onlinebank.client.presenter.UserPanelPresenter;
import com.onlinebank.client.view.UserPanelView;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@RunWith(JMock.class)
public class UserPanelPresenterTest {

  Mockery context = new JUnit4Mockery();

  private BankServiceAsync bankServiceAsync = context.mock(BankServiceAsync.class);
  private UserPanelView userPanelView = context.mock(UserPanelView.class);
  private InstanceMatcher<AsyncCallback<BigDecimal>> depositAsyncCallbackInstanceMatcher = new InstanceMatcher<AsyncCallback<BigDecimal>>();
  private InstanceMatcher<AsyncCallback<BigDecimal>> withdrawAsyncCallbackInstanceMatcher = new InstanceMatcher<AsyncCallback<BigDecimal>>();
  private BigDecimal sum = new BigDecimal(10);

  private UserPanelPresenter userPanelPresenter;

  @Before
  public void createTestableData() {

    userPanelPresenter = new UserPanelPresenter(bankServiceAsync, new SimpleEventBus(), userPanelView);
  }

  @Test
  public void requestIsSendToTheServerOnDeposit() {

    context.checking(new Expectations() {{
      oneOf(bankServiceAsync).deposit(with(sum), with(depositAsyncCallbackInstanceMatcher));
    }});

    userPanelPresenter.deposit();

  }

  @Test
  public void depositOnSuccess() {


    context.checking(new Expectations() {
      {
        oneOf(bankServiceAsync).deposit(with(sum), with(depositAsyncCallbackInstanceMatcher));
        oneOf(userPanelView).setBalanceShown(sum.toString());
        oneOf(userPanelView).setStatusMessage("");
      }
    });

    userPanelPresenter.deposit();
    depositAsyncCallbackInstanceMatcher.getInstance().onSuccess(sum);
  }

  @Test
  public void depositOnFailureWithIncorrectDataFormatException() {

    context.checking(new Expectations() {{
      oneOf(bankServiceAsync).deposit(with(sum), with(depositAsyncCallbackInstanceMatcher));
      oneOf(userPanelView).setStatusMessage("");
    }});

    userPanelPresenter.deposit();
    depositAsyncCallbackInstanceMatcher.getInstance().onFailure(new IncorrectDataFormatException());
  }

  @Test
  public void requestIsSendToTheServerOnWithdraw() {

    context.checking(new Expectations() {{
      oneOf(bankServiceAsync).withdraw(with(sum), with(withdrawAsyncCallbackInstanceMatcher));
    }});

    userPanelPresenter.withdraw();

  }

  @Test
  public void withdrawOnSuccess() {

    context.checking(new Expectations() {{
      oneOf(bankServiceAsync).withdraw(with(sum), with(withdrawAsyncCallbackInstanceMatcher));
      oneOf(userPanelView).setBalanceShown(sum.toString());
      oneOf(userPanelView).setStatusMessage("");
    }});

    userPanelPresenter.withdraw();
    withdrawAsyncCallbackInstanceMatcher.getInstance().onSuccess(sum);
  }

  @Test
  public void withdrawOnFailureWithIncorrectDataFormatException(){

    context.checking(new Expectations() {{
      oneOf(bankServiceAsync).withdraw(with(sum), with(withdrawAsyncCallbackInstanceMatcher));
      oneOf(userPanelView).setStatusMessage("");
    }});

    userPanelPresenter.withdraw();
    withdrawAsyncCallbackInstanceMatcher.getInstance().onFailure(new IncorrectDataFormatException());

  }

  public void withdrawOnFailureWithInsufficientBalanceException(){

    context.checking(new Expectations() {{
      oneOf(bankServiceAsync).withdraw(with(sum), with(withdrawAsyncCallbackInstanceMatcher));
      oneOf(userPanelView).setStatusMessage("");
    }});

    userPanelPresenter.withdraw();
    withdrawAsyncCallbackInstanceMatcher.getInstance().onFailure(new InsufficientBalanceException());

  }

}
