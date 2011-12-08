import api.YandexMoneyImpl;
import api.rights.AccountInfo;
import api.rights.OperationHistory;
import api.rights.Permission;
import org.junit.Test;
import util.Config;

import java.util.Collection;
import java.util.LinkedList;

/**
 * This code is brought you by
 *
 * @author Olshanikov Konstantin
 */
public class SoimTest {

    @Test
    public void test() {
        YandexMoneyImpl ym = new YandexMoneyImpl(Config.YANDEX_ID);

        Collection<Permission> scope = new LinkedList<Permission>();
        scope.add(new AccountInfo());
        scope.add(new OperationHistory());

        String codeRedirectUri = ym.authorizeUri(scope, Config.REDIRECT_URI);
        int i = 0;
    }
}
