package com.onlinebank.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.onlinebank.client.LoginService;
import com.onlinebank.client.exception.UserNotRegisteredException;
import com.onlinebank.client.exception.WrongPasswordException;
import com.onlinebank.client.model.User;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@Singleton
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

  @Inject
  private UserRepository userRepository;
  @Inject
  private AccountRepository accountRepository;

  public LoginServiceImpl(){

  }

  public LoginServiceImpl(UserRepository userRepository, AccountRepository accountRepository){
    this.userRepository = userRepository;
    this.accountRepository = accountRepository;
  }


  public void login(String username, String password) {
    User loginUser = userRepository.getUser(username);
    if(loginUser == null){
      throw new UserNotRegisteredException();
    } else if(!loginUser.getPassword().equals(password)){
      throw new WrongPasswordException();
    }

  }
}
