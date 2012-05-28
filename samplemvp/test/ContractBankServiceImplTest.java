import com.onlinebank.client.BankService;
import com.onlinebank.client.exception.IncorrectDataFormatException;
import com.onlinebank.client.model.User;
import com.onlinebank.server.BankServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public abstract class ContractBankServiceImplTest {
  BankService bankService;
  
  @Before
  public void createTestableData(){
    bankService = new BankServiceImpl();
  }

  @Test(expected = IncorrectDataFormatException.class)
  public void shouldNotRegisterNewAccountIfUsernameIsShorterThanTheGivenLimit(){
    bankService.register(new User("Gop","password"));
  }

  protected abstract BankService createBankService();
}
