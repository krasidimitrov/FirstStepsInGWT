import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.onlinebank.client.BankServiceAsync;
import com.onlinebank.client.model.User;
import com.onlinebank.client.presenter.RegisterPresenter;
import com.onlinebank.client.view.RegisterView;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

@RunWith(JMock.class)
public class RegisterPresenterTest {
  Mockery context = new JUnit4Mockery();
  RegisterView registerView = context.mock(RegisterView.class);
  BankServiceAsync bankServiceAsync = context.mock(BankServiceAsync.class);
  final InstanceMatcher<AsyncCallback<Void>> asyncCallbackInstanceMatcher = new InstanceMatcher<AsyncCallback<Void>>();
  final InstanceMatcher<User> userInstanceMatcher = new InstanceMatcher<User>();
  User user;

  RegisterPresenter registerPresenter;

  @Before
  public void createTestableData(){
    user = new User("Krasi", "password");
    registerPresenter = new RegisterPresenter(bankServiceAsync, new SimpleEventBus(), registerView);
  }
//
//  @Test
//  public void getUserFromTheRegistrationForm(){
//
//    context.checking(new Expectations(){{
//      oneOf(registerView).getUser();
//    }
//    });
//
//   registerPresenter.registerUser();
//  }

  @Test
  public void sendRequestToTheServer(){

    context.checking(new Expectations(){{
      oneOf(registerView).getUser();
      will(returnValue(user));
      oneOf(bankServiceAsync).register(with(userInstanceMatcher), with(asyncCallbackInstanceMatcher));
    }
    });

    registerPresenter.registerUser();
  }

  public class InstanceMatcher<T> extends BaseMatcher<T> {
    private T instance;

    public T getInstance() {
      return instance;
    }

    public boolean matches(Object o) {
      try {
        instance = (T) o;
        return true;
      } catch (ClassCastException ex) {
        return false;
      }
    }

    @Override
    public void describeTo(Description description) {

    }
  }
}
