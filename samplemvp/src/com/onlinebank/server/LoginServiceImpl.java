package com.onlinebank.server;

import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.onlinebank.client.LoginService;
import com.onlinebank.client.exception.UserNotRegisteredException;
import com.onlinebank.client.exception.WrongPasswordException;
import com.onlinebank.client.model.Sid;
import com.onlinebank.client.model.User;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@Singleton
public class LoginServiceImpl extends XsrfProtectedServiceServlet implements LoginService {

  @Inject(optional = true)
  private UserRepository userRepository;
  @Inject(optional = true)
  private AccountRepository accountRepository;
  @Inject(optional = true)
  private UsersOnlineRepository usersOnlineRepository;

  public LoginServiceImpl() {

  }

  public LoginServiceImpl(UserRepository userRepository, AccountRepository accountRepository, UsersOnlineRepository usersOnlineRepository) {
    this.userRepository = userRepository;
    this.accountRepository = accountRepository;
    this.usersOnlineRepository = usersOnlineRepository;
  }

//  @Override
//  protected void validateXsrfToken(RpcToken token, Method method) throws RpcTokenException {
//    this.token = (XsrfToken) token;
//  }

  public Sid login(String username, String password) {

    User loginUser = userRepository.getUser(username);
    if (loginUser == null) {
      throw new UserNotRegisteredException();
    } else if (!loginUser.getPassword().equals(password)) {
      throw new WrongPasswordException();
    }
//    String tokenValue = token.getSid();
    String sidValue = getThreadLocalRequest().getSession().getId();
    Sid sidObject = new Sid(sidValue, username, accountRepository.getAccountId(loginUser.getUserId()));
    usersOnlineRepository.save(sidObject, 300);
    return sidObject;
  }
}
