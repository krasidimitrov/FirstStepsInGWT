import com.onlinebank.client.BankService;
import com.onlinebank.server.BankServiceImpl;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class RealBankServiceTest extends ContractBankServiceImplTest{

  @Override
  protected BankService createBankService() {
    return new BankServiceImpl();
  }
}
