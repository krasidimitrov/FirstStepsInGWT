package com.onlinebank.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.onlinebank.client.RegisterService;
import com.onlinebank.client.exception.IncorrectDataFormatException;
import com.onlinebank.client.exception.UsernameAlreadyExistsException;
import com.onlinebank.client.model.User;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@Singleton
public class RegisterServiceImpl extends RemoteServiceServlet implements RegisterService {

  @Inject
  private UserRepository userRepository;
  @Inject
  private AccountRepository accountRepository;

  public RegisterServiceImpl(){

  }

  public RegisterServiceImpl(UserRepository userRepository, AccountRepository accountRepository){
    this.userRepository = userRepository;
    this.accountRepository = accountRepository;
  }


  public void register(User user) {
    if(!user.isValid())  {
      throw new IncorrectDataFormatException();
    }

    if (userRepository.getUser(user.getUsername()) == null){
      int idOfRegisteredUser = userRepository.save(user);
      accountRepository.addAccount(idOfRegisteredUser);
    } else {
      throw new UsernameAlreadyExistsException();
    }
  }
}
