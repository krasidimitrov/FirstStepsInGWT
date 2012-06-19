package com.clouway.sampleRF.client;

import com.google.web.bindery.requestfactory.shared.Receiver;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

@RunWith(JMock.class)
public class SimpleFormPresenterTest {

  private Mockery context = new Mockery() {{
    setImposteriser(ClassImposteriser.INSTANCE);
  }};

  private PersonRequestFactory requestFactory = context.mock(PersonRequestFactory.class);
  private SimpleView simpleView = context.mock(SimpleView.class);
  private String name = "realName";
  private String nick = "nickName";
  private SimpleFormPresenter simpleFormPresenter = new SimpleFormPresenter(requestFactory, simpleView);
  private InstanceMatcher<Receiver<Void>> instanceMatcher = new InstanceMatcher<Receiver<Void>>();
  private Receiver<Void> receiver = context.mock(Receiver.class);


  public class FakeReceiver extends Receiver<Void>{

    @Override
    public void onSuccess(Void response) {
//      simpleView.showSuccessMessage();
    }
  }

  @Test
  public void addPersonSendRequest(){
    final FakeReceiver fakeReceiver = new FakeReceiver();
    
    context.checking(new Expectations(){{
      oneOf(simpleView).getNameValue();
      will(returnValue(name));
      oneOf(simpleView).getNickValue();
      will(returnValue(nick));
      oneOf(requestFactory).personRequest().save(name,nick).fire(fakeReceiver);
    }});


    simpleFormPresenter.addPerson();
  }

  @Test
  public void addPersonOnSuccess(){
    final FakeReceiver fakeReceiver = new FakeReceiver();
    context.checking(new Expectations(){{
      oneOf(simpleView).getNameValue();
      will(returnValue(name));
      oneOf(simpleView).getNickValue();
      will(returnValue(nick));
      oneOf(requestFactory).personRequest().save(name,nick).fire(receiver);
//      oneOf(simpleView).showSuccessMessage();
    }});


    simpleFormPresenter.addPerson();
    receiver.onSuccess(null);


  }
}
