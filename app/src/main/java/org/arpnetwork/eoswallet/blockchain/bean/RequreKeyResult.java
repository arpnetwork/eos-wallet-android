package org.arpnetwork.eoswallet.blockchain.bean;

import org.arpnetwork.eoswallet.blockchain.cypto.ec.EosPublicKey;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapnibble on 2017-09-12.
 */

public class RequreKeyResult {

    @Expose
    private List<String> required_keys ;

    public List<EosPublicKey> getKeys() {
        if ( null == required_keys ){
            return new ArrayList<>();
        }

        ArrayList<EosPublicKey> retKeys = new ArrayList<>(required_keys.size());
        for ( String pubKey: required_keys ){
            retKeys.add( new EosPublicKey( pubKey));
        }

        return retKeys;
    }
}
