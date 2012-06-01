import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.onlinebank.client.BankServiceAsync;
import com.onlinebank.client.event.GoToRegistrationButtonClickedEvent;
import com.onlinebank.client.event.GoToRegistrationButtonClickedEventHandler;
import com.onlinebank.client.exception.UserNotRegisteredException;
import com.onlinebank.client.exception.WrongPasswordException;import com.onlinebank.client.model.User;
import com.onlinebank.client.presenter.LoginMessages;
import com.onlinebank.client.presenter.LoginPresenter;
import com.onlinebank.client.view.LoginView;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
* @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
*/


@RunWith(JMock.class)
public class LoginPresenterTest {

  private String username;
  private String password;

  private Mockery context = new JUnit4Mockery();
  private LoginView loginView = context.mock(LoginView.class);
  private BankServiceAsync bankServiceAsync = context.mock(BankServiceAsync.class);
  private LoginMessages loginMessages = context.mock(LoginMessages.class);
  private InstanceMatcher<AsyncCallback<User>> asyncCallbackInstanceMatcher = new InstanceMatcher<AsyncCallback<User>>();
  private String message = "";

  private LoginPresenter loginPresenter;
  
  private User user = new User("Ivancho", "dsafsa");
  @Before
  public void createTestableData(){
    username = "Lilith";
    password = "123456";
    loginPresenter = new LoginPresenter(bankServiceAsync, new SimpleEventBus(), loginView, loginMessages);
  }

  @Test
  public void sendRequestToTheServerInTheLoginMethod(){
    
    context.checking(new Expectations(){{
      oneOf(bankServiceAsync).login(with(username), with(password), with(asyncCallbackInstanceMatcher));
    }});
    
    loginPresenter.login(username, password);

  }
  
  @Test
  public void userIsRedirectedToUserPageOnSuccess(){
  
    context.checking(new Expectations(){{
      oneOf(bankServiceAsync).login(with(username), with(password), with(asyncCallbackInstanceMatcher));
      oneOf(loginView).goToUserPage();
    }});
    
    loginPresenter.login(username, password);
    asyncCallbackInstanceMatcher.getInstance().onSuccess(user);
  }

  @Test
  public void messageForNonExistingUsernameIsSetOnLoginOnFailureWithUserNotRegisteredException(){
    context.checking(new Expectations(){{
      oneOf(bankServiceAsync).login(with(username), with(password), with(asyncCallbackInstanceMatcher));
      oneOf(loginMessages).usernameNotRegistered();
      will(returnValue(message));
      oneOf(loginView).setStatusMessage(message);
    }});

    loginPresenter.login(username, password);
    asyncCallbackInstanceMatcher.getInstance().onFailure(new UserNotRegisteredException());
  }
  
  @Test
  public void messageForWrongPasswordIsSetOnFailureWithWrongPasswordException(){
    
    context.checking(new Expectations(){{
      
      oneOf(bankServiceAsync).login(with(username), with(password), with(asyncCallbackInstanceMatcher));
      oneOf(loginMessages).wrongPassword();
      will(returnValue(message));
      oneOf(loginView).setStatusMessage(message);
    }});

    loginPresenter.login(username, password);
    asyncCallbackInstanceMatcher.getInstance().onFailure(new WrongPasswordException());
  }


  class FakeHandler implements GoToRegistrationButtonClickedEventHandler {
    private boolean eventWasFired = false;

    public void assertHasReceivedClickEvent() {
      assertThat(eventWasFired, is(equalTo(true)));
    }

    @Override
    public void onGoToRegistrationButtonClicked(GoToRegistrationButtonClickedEvent event) {
      eventWasFired = true;
    }
  }


  /**
   * Test the behavior of onGoToLoginButtonClicked with a fake event handler
   */
  @Test
  public void fireEventBus() {
    SimpleEventBus eventBus = new SimpleEventBus();
    FakeHandler fakeHandler = new FakeHandler();


    LoginPresenter loginPresenter1 = new LoginPresenter(bankServiceAsync, eventBus, loginView, loginMessages);
    eventBus.addHandler(new GoToRegistrationButtonClickedEvent().getAssociatedType(), fakeHandler);


    context.checking(new Expectations() {{
      oneOf(loginView).setStatusMessage("");
    }});

    loginPresenter1.onGotToRegistrationButtonClicked();

    fakeHandler.assertHasReceivedClickEvent();
  }

}
