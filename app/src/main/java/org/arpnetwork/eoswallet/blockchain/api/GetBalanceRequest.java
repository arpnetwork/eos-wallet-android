package org.arpnetwork.eoswallet.blockchain.api;

import org.arpnetwork.eoswallet.blockchain.types.TypeAccountName;
import com.google.gson.annotations.Expose;


/**
 * Created by swapnibble on 2018-04-16.
 */
public class GetBalanceRequest extends GetRequestForCurrency{

    @Expose
    private TypeAccountName account;

    public GetBalanceRequest(String tokenContract, String account, String symbol){
        super( tokenContract, symbol );
        this.account = new TypeAccountName(account);
    }
}
