package org.arpnetwork.eoswallet.blockchain.api;

import org.arpnetwork.eoswallet.blockchain.types.TypeName;
import org.arpnetwork.eoswallet.blockchain.util.StringUtils;
import com.google.gson.annotations.Expose;

/**
 * Created by swapnibble on 2018-04-16.
 */
public class GetRequestForCurrency {
    @Expose
    protected boolean json = false;

    @Expose
    protected TypeName code;

    @Expose
    protected String symbol;

    public GetRequestForCurrency(String tokenContract, String symbol){
        this.code = new TypeName(tokenContract);
        this.symbol = StringUtils.isEmpty(symbol) ? null : symbol;
    }
}
