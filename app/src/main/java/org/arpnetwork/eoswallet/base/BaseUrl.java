package org.arpnetwork.eoswallet.base;

/**
 * Created by pocketEos on 2017/11/23.
 */

public class BaseUrl {
    // 测试环境服务器地址
    public final static String HTTP_ADDRESS = "http://59.110.162.106:9999/api_oc_personal/v1.0.0/";
    public final static String HTTP_CHAIN_VOTE_ADDRESS = "http://59.110.162.106:8080/voteoraclechain/";
    public final static String HTTP_CHAIN_ADDRESS = "http://39.108.231.157:30065/v1/chain/";
    public final static String HTTP_ANSWER_ADDRESS = "http://59.110.162.106:8080/";
    public final static String HTTP_CANDY_ADDRESS = "http://59.110.162.106:9999/api_oc_pe_candy_system/";
/*
    // 正式环境服务器地址
    public final static String HTTP_ADDRESS = "http://api.pocketeos.top/api_oc_personal/v1.0.0/";
    public final static String HTTP_CHAIN_VOTE_ADDRESS = "http://api.pocketeos.top/voteoraclechain/";
    public final static String HTTP_CHAIN_ADDRESS = "http://api.pocketeos.top/api_oc_blockchain-v1.3.0/";
    public final static String HTTP_ANSWER_ADDRESS = "http://api.pocketeos.top/eosaskanswer30/";
    public final static String HTTP_CANDY_ADDRESS = "http://api.pocketeos.top/api_oc_pe_candy_system/";*/

    public final static String HOST = "http://122.152.214.84:8080";
    public final static String HTTP_ACCOUNT_ALLOC = HOST + "/eos/contract/alloc/%s";
    public final static String HTTP_ACCOUNT_ACTIVE = HOST + "/eos/contract/active/%s";
    public final static String HTTP_GET_EOS_PRICE = HOST + "/eos/getprice";
    public final static String HTTP_GET_BHKD_PRICE = HOST + "/rate/hkdcny";

    // 注册EOS账号
    public final static String HTTP_eos_register = HTTP_CHAIN_ADDRESS + "create_account";
    //备份EOS账号至服务器
    public final static String HTTP_add_new_eos = HTTP_ADDRESS + "user/add_new_eos";

    // 获取EOS账号信息
    public final static String HTTP_eos_get_account = HTTP_CHAIN_ADDRESS + "get_account_asset";
    // 获取链上信息
    public final static String HTTP_eos_get_table = HTTP_CHAIN_ADDRESS + "get_table_rows";
    // 获取资产汇率
    public final static String HTTP_eos_get_coin_rate = HTTP_CHAIN_ADDRESS + "get_rate";
    // 获取走势图
    public final static String HTTP_get_sparklines = HTTP_CHAIN_ADDRESS + "get_sparklines";
    // 获取余额
    public final static String HTTP_get_currency_balance = HTTP_CHAIN_ADDRESS + "get_currency_balance";

    // 获取区块链状态
    public final static String HTTP_get_chain_info = HTTP_CHAIN_ADDRESS + "get_info";
    // 交易JSON序列化
    public final static String HTTP_get_abi_json_to_bin = HTTP_CHAIN_ADDRESS + "abi_json_to_bin";
    // 获取keys
    public final static String HTTP_get_required_keys = HTTP_CHAIN_ADDRESS + "get_required_keys";
    // 发起交易
    public final static String HTTP_push_transaction = HTTP_CHAIN_ADDRESS + "push_transaction";

    // 获取交易历史
    public final static String HTTP_get_transaction_history = "http://history.pocketeos.top/VX/GetActions";
    // 获取区块链账号信息
    public final static String HTTP_get_chain_account_info = HTTP_CHAIN_ADDRESS + "get_account";
    // 设置主账号
    public final static String HTTP_set_mian_account = HTTP_ADDRESS + "user/toggleEosMain";
    // 保护隐私
    public final static String HTTP_set_policy_account = HTTP_ADDRESS + "user/update_secret";
    // 系统设置
    public final static String HTTP_get_system_info = HTTP_ADDRESS + "system/getInfo";
    // app更新
    public final static String HTTP_get_app_info = HTTP_ADDRESS + "get_last_info";
    // 获取有问必答问题列表
    public final static String HTTP_GetAsks = HTTP_ANSWER_ADDRESS + "GetAsksJson";

    // 获取糖果积分
    public final static String getHTTP_get_candy_score = HTTP_CANDY_ADDRESS + "get_candy_score";
    // 获取热门权益
    public final static String getHTTP_get_all_exchange = HTTP_CANDY_ADDRESS + "get_all_exchange";
    // 获取任务列表
    public final static String getHTTP_get_user_task = HTTP_CANDY_ADDRESS + "get_user_task";
    // 通过公钥获取账号
    public final static String getHTTP_GetAccounts = HTTP_CHAIN_VOTE_ADDRESS + "GetAccounts";
    // 获取BP节点列表
    public final static String getHTTP_GetBpJson = HTTP_CHAIN_VOTE_ADDRESS + "GetBpJson";
    // 获取账号投票信息
    public final static String getHTTP_GetMyVoteInfo = HTTP_CHAIN_VOTE_ADDRESS + "GetMyVoteInfo";
    // 获取权重
    public final static String getHTTP_GetNowVoteWeight = HTTP_CHAIN_VOTE_ADDRESS + "GetNowVoteWeight";
    // 通知完成投票
    public final static String getHTTP_complete_task = HTTP_CANDY_ADDRESS + "complete_task";
}
