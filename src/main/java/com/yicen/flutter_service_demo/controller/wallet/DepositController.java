package com.yicen.flutter_service_demo.controller.wallet;


import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositTypeVo;
import com.yicen.flutter_service_demo.controller.wallet.service.Impl.WalletDepositChannelServiceImpl;
import com.yicen.flutter_service_demo.controller.wallet.service.Impl.WalletDepositServiceImpl;
import com.yicen.flutter_service_demo.controller.wallet.service.WalletDepositChannelService;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.utils.RedisUtil;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wallet/deposit/")
@ApiOperation("存款相关的接口")
public class DepositController {

    private static final String kDepositStyle = "WALLET_DEPOSIT_STYLE_KEY";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WalletDepositServiceImpl walletDepositService;

    @GetMapping("style")
    public Result depositStyle() {
        List<WalletDepositTypeVo> lists;
        String s = redisUtil.get(kDepositStyle);
        if (StringUtil.isNullOrEmpty(s)) {
            List<WalletDepositTypeVo> depositStyle = walletDepositService.getDepositStyle();
            redisUtil.set(kDepositStyle, String.valueOf(JSONArray.fromObject(depositStyle)));
            lists = depositStyle;

        } else {
            JSONArray jsonArray = JSONArray.fromObject(redisUtil.get(kDepositStyle));
            lists = (List<WalletDepositTypeVo>) JSONArray.toCollection(jsonArray, WalletDepositTypeVo.class);
        }
        return Result.ok(walletDepositService.getDepositStyle());
    }

    public Result test(){
        return Result.ok(walletDepositService.test());
    }
}
