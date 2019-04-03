package org.arpnetwork.eoswallet.blockchain;

import android.content.Context;

import org.arpnetwork.eoswallet.base.BaseUrl;
import org.arpnetwork.eoswallet.base.Constants;
import org.arpnetwork.eoswallet.blockchain.api.EosChainInfo;
import org.arpnetwork.eoswallet.blockchain.bean.GetRequiredKeys;
import org.arpnetwork.eoswallet.blockchain.bean.JsonToBeanResultBean;
import org.arpnetwork.eoswallet.blockchain.bean.JsonToBinRequest;
import org.arpnetwork.eoswallet.blockchain.chain.Action;
import org.arpnetwork.eoswallet.blockchain.chain.PackedTransaction;
import org.arpnetwork.eoswallet.blockchain.chain.SignedTransaction;
import org.arpnetwork.eoswallet.blockchain.util.GsonEosTypeAdapterFactory;
import org.arpnetwork.eoswallet.data.AccountRes;
import org.arpnetwork.eoswallet.data.ResponseBean;
import org.arpnetwork.eoswallet.net.HttpUtils;
import org.arpnetwork.eoswallet.net.callbck.JsonCallback;
import org.arpnetwork.eoswallet.util.JsonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.model.Response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by pocketEos on 2018/5/2.
 * eosX适配
 */

public class EosDataManger {
    static String EOSCONTRACT = Constants.EOSCONTRACT;
    static String OCTCONTRACT = Constants.OCTCONTRACT;//erctoken
    static String ACTIONTRANSFER = Constants.ACTIONTRANSFER;
    static String PERMISSONION = Constants.PERMISSONION;

    Context mContext;
    EosChainInfo mChainInfoBean = new EosChainInfo();
    JsonToBeanResultBean mJsonToBeanResultBean = new JsonToBeanResultBean();
    String[] permissions;
    SignedTransaction txnBeforeSign;
    Gson mGson = new GsonBuilder()
            .registerTypeAdapterFactory(new GsonEosTypeAdapterFactory())
            .excludeFieldsWithoutExposeAnnotation().create();

    String contract, action, message, userpassword;

    BigDecimal coinRate;//资产汇率

    public EosDataManger(Context context, String password) {
        mContext = context;
        this.userpassword = password;
    }

    public void pushAction(String message, String permissionAccount) {
        this.message = message;
        if (message.contains("EOS")) {
            this.contract = EOSCONTRACT;
        } else {
            this.contract = OCTCONTRACT;
        }
        this.action = ACTIONTRANSFER;
        permissions = new String[]{permissionAccount + "@" + PERMISSONION};
        getChainInfo();
    }

    public void getChainInfo() {
        HttpUtils.getRequets(BaseUrl.HTTP_get_chain_info, this, new HashMap<String, String>(), new JsonCallback<ResponseBean>() {
            @Override
            public void onSuccess(Response<ResponseBean> response) {
                if (response.body().code == 0) {
                    mChainInfoBean = (EosChainInfo) JsonUtil.parseStringToBean(mGson.toJson(response.body().data), EosChainInfo.class);
                    getabi_json_to_bin();
                } else {
//                    if (ShowDialog.dialog != null) {
//                        ShowDialog.dissmiss();
//                    }
//                    ToastUtils.showLongToast(response.body().message);
                }
            }
        });
    }

    public void getabi_json_to_bin() {

        JsonToBinRequest jsonToBinRequest = new JsonToBinRequest(contract, action, message.replaceAll("\\r|\\n", ""));
        HttpUtils.postRequest(BaseUrl.HTTP_get_abi_json_to_bin, this, mGson.toJson(jsonToBinRequest), new JsonCallback<ResponseBean>() {
            @Override
            public void onSuccess(Response<ResponseBean> response) {
                if (response.body().code == 0) {
                    mJsonToBeanResultBean = (JsonToBeanResultBean) JsonUtil.parseStringToBean(mGson.toJson(response.body().data), JsonToBeanResultBean.class);
                    txnBeforeSign = createTransaction(contract, action, mJsonToBeanResultBean.getBinargs(), permissions, mChainInfoBean);
                    //扫描钱包列出所有可用账号的公钥
//                    List<String> pubKey =  PublicAndPrivateKeyUtils.getActivePublicKey();

//                    getRequreKey(new GetRequiredKeys(txnBeforeSign, pubKey));
                } else {
//                    if (ShowDialog.dialog != null) {
//                        ShowDialog.dissmiss();
//                    }
//                    ToastUtils.showLongToast(response.body().message);
                }
            }
        });
    }

    private SignedTransaction createTransaction(String contract, String actionName, String dataAsHex,
            String[] permissions, EosChainInfo chainInfo) {

        Action action = new Action(contract, actionName);
        action.setAuthorization(permissions);
        action.setData(dataAsHex);


        SignedTransaction txn = new SignedTransaction();
        txn.addAction(action);
        txn.putSignatures(new ArrayList<String>());


        if (null != chainInfo) {
            txn.setReferenceBlock(chainInfo.getHeadBlockId());
            txn.setExpiration(chainInfo.getTimeAfterHeadBlockTime(30000));
        }
        return txn;
    }

    public void getRequreKey(GetRequiredKeys getRequiredKeys) {

        HttpUtils.postRequest(BaseUrl.HTTP_get_required_keys, this, mGson.toJson(getRequiredKeys), new JsonCallback<ResponseBean>() {
            @Override
            public void onSuccess(Response<ResponseBean> response) {
//                if (response.body().code == 0) {
//                    RequreKeyResult requreKeyResult = (RequreKeyResult) JsonUtil.parseStringToBean(mGson.toJson(response.body().data), RequreKeyResult.class);
//                    EosPrivateKey eosPrivateKey = new EosPrivateKey(PublicAndPrivateKeyUtils.getPrivateKey(requreKeyResult.getRequired_keys().get(0), userpassword));
//                    txnBeforeSign.sign(eosPrivateKey, new TypeChainId(mChainInfoBean.getChain_id()));
//                    pushTransactionRetJson(new PackedTransaction(txnBeforeSign));
//                } else {
////                    if (ShowDialog.dialog != null) {
////                        ShowDialog.dissmiss();
////                    }
////                    ToastUtils.showLongToast(response.body().message);
//                }
            }
        });

    }

    public void pushTransactionRetJson(PackedTransaction body) {
       /* HttpUtils.postRequest(BaseUrl.HTTP_push_transaction, this, mGson.toJson(body), new JsonCallback<ResponseBean>() {
            @Override
            public void onSuccess(final Response<ResponseBean> response) {


            }
        });*/
    }

    public EosDataManger setCoinRate(BigDecimal coinRate) {
        this.coinRate = coinRate;
        return this;
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
}
