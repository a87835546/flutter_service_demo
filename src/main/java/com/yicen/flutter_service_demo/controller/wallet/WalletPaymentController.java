package com.yicen.flutter_service_demo.controller.wallet;


import com.yicen.flutter_service_demo.config.NeedLogin;
import com.yicen.flutter_service_demo.controller.payment.entity.PaymentVo;
import com.yicen.flutter_service_demo.controller.payment.service.impl.PaymentServiceImpl;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositDo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletDepositTypeVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletTransactionVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletWithdrawVo;
import com.yicen.flutter_service_demo.controller.wallet.service.Impl.TransactionServiceImpl;
import com.yicen.flutter_service_demo.controller.wallet.service.Impl.WalletDepositServiceImpl;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.utils.JwtUtil;
import com.yicen.flutter_service_demo.utils.RedisUtil;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("wallet/deposit/")
@ApiOperation("存款相关的接口")
@Slf4j
public class WalletPaymentController {

    private static final String kDepositStyle = "WALLET_DEPOSIT_STYLE_KEY";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WalletDepositServiceImpl walletDepositService;

    @Autowired
    private PaymentServiceImpl paymentService;

    @Autowired
    private TransactionServiceImpl transactionService;

    @GetMapping("style")
    @NeedLogin
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

    public Result test() {
        return Result.ok(walletDepositService.test());
    }

    @PostMapping("deposit")
    @NeedLogin
    public Result deposit(HttpServletRequest request, @RequestBody WalletDepositDo depositDo) {
        log.info("insert record request {}", depositDo);
        User user = JwtUtil.getUser(request.getHeader("token"));
        PaymentVo deposit = paymentService.deposit(user.getId().toString(), BigDecimal.valueOf(depositDo.getMoney()));
        WalletTransactionVo walletTransactionVo = new WalletTransactionVo();
        walletTransactionVo.setAmount(depositDo.getMoney().longValue());
        walletTransactionVo.setType(0);
        walletTransactionVo.setUserName(user.getUsername());
        String desc = "";
        switch (depositDo.getTypeId()) {
            case 0:
                desc = "微信充值";
            case 1:
                desc = "支付宝充卡";
            default:
                desc = "银联充卡";

        }
        walletTransactionVo.setDescription(desc);
        walletTransactionVo.setResultType(true);
        boolean b = transactionService.insertRecord(walletTransactionVo);
        log.info("insert record {}", b ? "success" : "fail");
        return Result.ok(deposit);
    }

    @PostMapping("withdraw")
    public Result withdraw(HttpServletRequest request,@RequestBody WalletWithdrawVo vo){
        User user = JwtUtil.getUser(request.getHeader("token"));
        PaymentVo withdraw = paymentService.withdraw(user.getId().toString(), BigDecimal.valueOf(vo.getAmount()));
        WalletTransactionVo walletTransactionVo = new WalletTransactionVo();
        walletTransactionVo.setAmount(vo.getAmount().longValue());
        walletTransactionVo.setType(1);
        walletTransactionVo.setUserName(user.getUsername());
        String desc = "取款";
        walletTransactionVo.setDescription(desc);
        if (withdraw == null){
            walletTransactionVo.setResultType(false);
            boolean b = transactionService.insertRecord(walletTransactionVo);
            log.info("insert record {}", b ? "success" : "fail");
            return  Result.error("取款错误，金额不足");
        }else {
            walletTransactionVo.setResultType(true);
            boolean b = transactionService.insertRecord(walletTransactionVo);
            log.info("insert record {}", b ? "success" : "fail");
            return  Result.ok("取款成功");
        }
    }
}
