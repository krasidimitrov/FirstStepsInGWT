import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.onlinebank.client.BankServiceAsync;
import com.onlinebank.client.event.GoToLoginButtonClickedEvent;
import com.onlinebank.client.event.GoToLoginButtonClickedEventHandler;
import com.onlinebank.client.exception.IncorrectDataFormatException;
import com.onlinebank.client.exception.UsernameAlreadyExistsException;
import com.onlinebank.client.model.User;
import com.onlinebank.client.presenter.RegisterPresenter;
import com.onlinebank.client.presenter.RegistrationMessages;
import com.onlinebank.client.view.RegisterView;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

@RunWith(JMock.class)
public class RegisterPresenterTest {
  private Mockery context = new JUnit4Mockery();
  private RegisterView registerView = context.mock(RegisterView.class);
  private BankServiceAsync bankServiceAsync = context.mock(BankServiceAsync.class);
  private RegistrationMessages registrationMessages = context.mock(RegistrationMessages.class);

  private final InstanceMatcher<AsyncCallback<Void>> asyncCallbackInstanceMatcher = new InstanceMatcher<AsyncCallback<Void>>();
  private final InstanceMatcher<User> userInstanceMatcher = new InstanceMatcher<User>();
  private User user;
  private String message = "";

  private RegisterPresenter registerPresenter;
  private SimpleEventBus simpleEventBus = new SimpleEventBus();

//  @Test
//  public void userIsRedirectedToMainPageWhenLoginSuccess() throws Exception {
//
//    final User user = new User("test", "aaa");
//
//    context.checking(new Expectations() {{
//      oneOf(registerView).getUser();
//      will(returnValue(user));
//
//      oneOf(bankServiceAsync).register(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));
//      oneOf(registerView).redirectToMainPage();
//    }});
//
//    RegisterPresenter presenter = new RegisterPresenter(bankServiceAsync,new SimpleEventBus(),registerView);
//    presenter.registerUser();
//    asyncCallbackInstanceMatcher.getInstance().onSuccess(null);
//
//    assertThat(userInstanceMatcher.getInstanceHistory().get(0),is(equalTo(null)));
//    assertThat(userInstanceMatcher.getInstance(), is(sameInstance(user)));
//  }

  @Before
  public void createTestableData() {
    user = new User("Krasi", "password");
    registerPresenter = new RegisterPresenter(bankServiceAsync, simpleEventBus, registerView, registrationMessages);
  }

  @Test
  public void sendRequestToTheServerInTheRegisterUserMethod() {

    context.checking(new Expectations() {
      {
        oneOf(registerView).getUser();
        will(returnValue(user));
        oneOf(bankServiceAsync).register(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));

      }
    });

    registerPresenter.registerUser();
  }


  @Test
  public void messageForSuccessfulRegistrationIsSetOnSuccess() {
    context.checking(new Expectations() {{
      oneOf(registerView).getUser();
      will(returnValue(user));
      oneOf(bankServiceAsync).register(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));

      oneOf(registrationMessages).getSuccessMessage();
      will(returnValue(message));
      oneOf(registerView).setStatusMessage(message);
    }});

    registerPresenter.registerUser();
    asyncCallbackInstanceMatcher.getInstance().onSuccess(null);
//    assertThat(userInstanceMatcher.getInstance(), is(sameInstance(user)));

  }

  @Test
  public void messageForIncorrectDataIsSetOnFailureWithIncorrectDataFormatException() {
    context.checking(new Expectations() {{
      oneOf(registerView).getUser();
      will(returnValue(user));
      oneOf(bankServiceAsync).register(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));
      oneOf(registrationMessages).getWrongUsernameOrPasswordMessage();
      will(returnValue(message));
      oneOf(registerView).setStatusMessage(message);
    }});

    registerPresenter.registerUser();
    asyncCallbackInstanceMatcher.getInstance().onFailure(new IncorrectDataFormatException());
  }

  @Test
  public void messageForExistingUsernameIsSetOnFailureWithUsernameAlreadyExistsException() {
    context.checking(new Expectations() {{
      oneOf(registerView).getUser();
      will(returnValue(user));
      oneOf(bankServiceAsync).register(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));
      oneOf(registrationMessages).getUsernameAlreadyExistsMessage();
      will(returnValue(message));
      oneOf(registerView).setStatusMessage(message);
    }});

    registerPresenter.registerUser();
    asyncCallbackInstanceMatcher.getInstance().onFailure(new UsernameAlreadyExistsException());
  }

  /**
   * Fake Handler which implements my custom EventHandler. Used to test the behavior that the event bus fire an event in a presenter method
   */
  class FakeHandler implements GoToLoginButtonClickedEventHandler {
    private boolean eventWasFired = false;

    public void assertHasReceivedClickEvent() {
      assertThat(eventWasFired, is(equalTo(true)));
    }

    @Override
    public void onGoToLoginButtonClicked(GoToLoginButtonClickedEvent event) {
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


    RegisterPresenter registerPresenter1 = new RegisterPresenter(bankServiceAsync, eventBus, registerView, registrationMessages);
    eventBus.addHandler(new GoToLoginButtonClickedEvent().getAssociatedType(), fakeHandler);


    context.checking(new Expectations() {{
      oneOf(registerView).setStatusMessage("");
    }});

    registerPresenter1.onGoToLoginButtonClicked();

    fakeHandler.assertHasReceivedClickEvent();
  }


  @Test
  public void serviceMethodIsNotInvokedInCaseOfShorttUsername() {
    final User userWithShortUsername = new User("Sh", "password");

    context.checking(new Expectations() {{
      oneOf(registerView).getUser();
      will(returnValue(userWithShortUsername));
      oneOf(registrationMessages).getWrongUsernameOrPasswordMessage();
      will(returnValue(message));
      oneOf(registerView).setStatusMessage(message);
    }});

    registerPresenter.registerUser();
  }

  @Test
  public void serviceMethodIsNotInvokedInCaseOfUsernameWithProhibitedSymbols() {
    final User userWithUsernameWithProhibitedSymbols = new User("Usern!%", "password");

    context.checking(new Expectations() {{
      oneOf(registerView).getUser();
      will(returnValue(userWithUsernameWithProhibitedSymbols));
      oneOf(registrationMessages).getWrongUsernameOrPasswordMessage();
      will(returnValue(message));
      oneOf(registerView).setStatusMessage(message);
    }});

    registerPresenter.registerUser();
  }

  @Test
  public void serviceMethodIsNotInvokedInCaseOfShortPassword() {
    final User userWithShortPassword = new User("Username", "pa");

    context.checking(new Expectations() {{
      oneOf(registerView).getUser();
      will(returnValue(userWithShortPassword));
      oneOf(registrationMessages).getWrongUsernameOrPasswordMessage();
      will(returnValue(message));
      oneOf(registerView).setStatusMessage(message);
    }});

    registerPresenter.registerUser();
  }

  @Test
  public void serviceMethodIsNotInvokedInCaseOfPasswordWithProhibitedSymbols() {
    final User userWithPasswordWithProhibitedSymbols = new User("Username", "pa-ssword");

    context.checking(new Expectations() {{
      oneOf(registerView).getUser();
      will(returnValue(userWithPasswordWithProhibitedSymbols));
      oneOf(registrationMessages).getWrongUsernameOrPasswordMessage();
      will(returnValue(message));
      oneOf(registerView).setStatusMessage(message);
    }});

    registerPresenter.registerUser();
  }


}
