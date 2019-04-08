package org.arpnetwork.eoswallet.blockchain;

import android.content.Context;
import android.util.Log;

import org.arpnetwork.eoswallet.base.BaseUrl;
import org.arpnetwork.eoswallet.blockchain.api.EosChainInfo;
import org.arpnetwork.eoswallet.blockchain.api.GetBalanceRequest;
import org.arpnetwork.eoswallet.blockchain.bean.GetRequiredKeys;
import org.arpnetwork.eoswallet.blockchain.bean.JsonToBeanResultBean;
import org.arpnetwork.eoswallet.blockchain.bean.JsonToBinRequest;
import org.arpnetwork.eoswallet.blockchain.bean.RequreKeyResult;
import org.arpnetwork.eoswallet.blockchain.chain.Action;
import org.arpnetwork.eoswallet.blockchain.chain.PackedTransaction;
import org.arpnetwork.eoswallet.blockchain.chain.SignedTransaction;
import org.arpnetwork.eoswallet.blockchain.types.EosTransfer;
import org.arpnetwork.eoswallet.blockchain.types.TypeChainId;
import org.arpnetwork.eoswallet.blockchain.types.TypeSymbol;
import org.arpnetwork.eoswallet.blockchain.util.GsonEosTypeAdapterFactory;
import org.arpnetwork.eoswallet.blockchain.wallet.EosWalletManager;
import org.arpnetwork.eoswallet.data.AccountRes;
import org.arpnetwork.eoswallet.misc.Constant;
import org.arpnetwork.eoswallet.net.HttpUtils;
import org.arpnetwork.eoswallet.net.callbck.JsonCallback;
import org.arpnetwork.eoswallet.util.Util;
import org.arpnetwork.eoswallet.util.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static org.arpnetwork.eoswallet.misc.Constant.EOSIO_TOKEN_CONTRACT;
import static org.arpnetwork.eoswallet.misc.Constant.TX_EXPIRATION_IN_MILSEC;

/**
 * Created by evan on 2018/12/19.
 * eos chain http api.
 */

public class EosDataManger {
    private EosWalletManager mWalletMgr;
    private Context mContext;
    private Gson mGson = new GsonBuilder()
            .registerTypeAdapterFactory(new GsonEosTypeAdapterFactory())
            .excludeFieldsWithoutExposeAnnotation().create();
    private EosChainInfo currentBlockInfo;

    private static EosDataManger sInstance = null;

    public static void init(Context context) {
        sInstance = new EosDataManger(context);
    }

    public static void fini() {
        if (sInstance != null) {
            sInstance = null;
        }
    }

    public static EosDataManger getInstance() {
        return sInstance;
    }

    public EosDataManger(Context context) {
        mContext = context.getApplicationContext();
        mWalletMgr = EosWalletManager.getInstance();

        // set core symbol
        TypeSymbol.setCoreSymbol(Constant.DEFAULT_SYMBOL_PRECISION, Constant.EOS_SYMBOL_STRING);
    }

    public void setInfo(EosChainInfo info) {
        currentBlockInfo = info;
    }

    public void accountAlloc(String publicKey, Object tag) {
        String url = String.format(Locale.US, BaseUrl.HTTP_ACCOUNT_ALLOC, publicKey);
        HttpUtils.getRequets(url, tag, null, new JsonCallback<AccountRes>() {
            @Override
            public void onSuccess(Response<AccountRes> response) {
                super.onSuccess(response);
            }

            @Override
            public void onError(Response<AccountRes> response) {
                super.onError(response);
            }
        });
    }

    public void accountActive(String account, Object tag) {
        String url = String.format(Locale.US, BaseUrl.HTTP_ACCOUNT_ACTIVE, account);
        HttpUtils.getRequets(url, tag, null, new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                super.onSuccess(response);
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });
    }

    public JsonObject transfer(String userPasswd, String from, String to, long amount, String memo) {
        EosTransfer transfer = new EosTransfer(from, to, amount, memo);
        // TODO: wallet manager unlock the wallet with the userPasswd.
        return pushActionRetJson(EOSIO_TOKEN_CONTRACT, transfer.getActionName(), Util.prettyPrintJson(transfer), getActivePermission(from)); //transfer.getAsHex()
    }

    public JsonObject pushActionRetJson(String contract, String action, String data, String[] permissions) {
        JsonToBeanResultBean jsonToBinResp = getAbiJsonToBinSync(contract, action, data);
        EosChainInfo chainInfo = getChainInfo();
        SignedTransaction txnBeforeSign = createTransaction(contract, action, jsonToBinResp.getBinargs(), permissions, chainInfo);
        PackedTransaction packedTransaction = signAndPackTransaction(txnBeforeSign);
        return pushTransactionRetJson(packedTransaction);
    }

    // sync call.
    public EosChainInfo getChainInfo() {
        try {
            EosChainInfo resultBean = HttpUtils.getRequetsSync(BaseUrl.HTTP_get_chain_info, this, new HashMap<String, String>(), EosChainInfo.class);
            return resultBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JsonToBeanResultBean getAbiJsonToBinSync(final String contract, final String action, String jsonStr) {
        try {
            JsonToBinRequest jsonToBinRequest = new JsonToBinRequest(contract, action, jsonStr.replaceAll("\\r|\\n", ""));
            JsonToBeanResultBean result = HttpUtils.postRequestSync(BaseUrl.HTTP_get_abi_json_to_bin, this, mGson.toJson(jsonToBinRequest), JsonToBeanResultBean.class);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RequreKeyResult getRequiredKeys(SignedTransaction txnBeforeSign) {
        try {
            GetRequiredKeys requiredKeys = new GetRequiredKeys(txnBeforeSign, mWalletMgr.listPubKeys());
            RequreKeyResult result = HttpUtils.postRequestSync(BaseUrl.HTTP_get_required_keys, this, mGson.toJson(requiredKeys), RequreKeyResult.class);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JsonObject pushTransactionRetJson(PackedTransaction packedTransaction) {
        try {
            JsonObject result = HttpUtils.postRequestSync(BaseUrl.HTTP_push_transaction, this, mGson.toJson(packedTransaction), JsonObject.class);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getCurrencyBalance(String contract, String account, String symbol, JsonCallback<JsonArray> callback) {
        GetBalanceRequest requiredKeys = new GetBalanceRequest(contract, account, symbol);
        HttpUtils.postRequest(BaseUrl.HTTP_get_currency_balance, this, mGson.toJson(requiredKeys), callback);
    }

    private String[] getActivePermission(String accountName) {
        return new String[]{accountName + "@active"};
    }

    private SignedTransaction createTransaction(String contract, String actionName, String dataAsHex,
            String[] permissions, EosChainInfo chainInfo) {
        currentBlockInfo = chainInfo;
        Action action = new Action(contract, actionName);
        action.setAuthorization(permissions);
        action.setData(dataAsHex);

        SignedTransaction txn = new SignedTransaction();
        txn.addAction(action);
        txn.putSignatures(new ArrayList<String>());

        if (null != chainInfo) {
            txn.setReferenceBlock(chainInfo.getHeadBlockId());
            txn.setExpiration(chainInfo.getTimeAfterHeadBlockTime(TX_EXPIRATION_IN_MILSEC));
        }

        return txn;
    }

    private PackedTransaction signAndPackTransaction(SignedTransaction txnBeforeSign) {
        RequreKeyResult requiredKey = getRequiredKeys(txnBeforeSign);
        SignedTransaction stxn = mWalletMgr.signTransaction(txnBeforeSign, requiredKey.getKeys(), new TypeChainId(currentBlockInfo.getChain_id()));
        return new PackedTransaction(stxn);
    }

    public static void getEOSPrice() {
        HttpUtils.getRequets(BaseUrl.HTTP_GET_EOS_PRICE, "", null, new JsonCallback<Float>() {
            @Override
            public void onSuccess(Response<Float> response) {
                Log.d("EOS", "price = " + response.body());
                PreferenceManager.getInstance().putFloat(Constant.EOS_PRICE, response.body());
            }

            @Override
            public void onError(Response<Float> response) {
                super.onError(response);
            }
        });
    }
}
