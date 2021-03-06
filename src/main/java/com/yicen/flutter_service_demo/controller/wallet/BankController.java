package com.yicen.flutter_service_demo.controller.wallet;

import com.yicen.flutter_service_demo.config.NeedLogin;
import com.yicen.flutter_service_demo.controller.wallet.entity.InsertBankVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletBankVo;
import com.yicen.flutter_service_demo.controller.wallet.entity.WalletCrVo;
import com.yicen.flutter_service_demo.controller.wallet.mapper.WalletCrMapper;
import com.yicen.flutter_service_demo.controller.wallet.service.Impl.WalletBankServiceImpl;
import com.yicen.flutter_service_demo.controller.wallet.service.Impl.WalletCrServiceImpl;
import com.yicen.flutter_service_demo.entity.Result;
import com.yicen.flutter_service_demo.entity.User;
import com.yicen.flutter_service_demo.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("wallet/bank")
@Api("银行卡相关的接口")
public class BankController {

    @Autowired
    private WalletBankServiceImpl walletBankService;

    @Autowired
    private WalletCrServiceImpl walletCrService;

    @PostMapping("add")
    @NeedLogin
    @ApiOperation("添加银行卡")
    public Result addBank(HttpServletRequest request,@RequestBody InsertBankVo walletBankVo) {
        WalletBankVo queryByCardNumber = walletBankService.queryByCardNumber(walletBankVo.getNumber());
        if (queryByCardNumber != null){
            if (queryByCardNumber.getIsDelete() == true){
                int i = walletBankService.updateDelete(walletBankVo.getNumber());
                return i > 0 ? Result.ok("添加银行卡成功") : Result.error("添加银行卡失败");
            }else {
                return Result.error("添加银行卡失败,此卡已经添加过了");
            }
        }else {
            User user = JwtUtil.getUser(request.getHeader("token"));
            WalletBankVo bankVo = new WalletBankVo();
            BeanUtils.copyProperties(walletBankVo, bankVo);
            bankVo.setUserId(user.getId());
            bankVo.setUserName(user.getUsername());
            int i = walletBankService.insertBank(bankVo);
            return i > 0 ? Result.ok("添加银行卡成功") : Result.error("添加银行卡失败");
        }
    }

    @GetMapping("list")
    @NeedLogin
    public Result queryByUserId(HttpServletRequest request) {
        User user = JwtUtil.getUser(request.getHeader("token"));
        return Result.ok(walletBankService.getBankList(user.getId().toString()));
    }

    @PostMapping("delete")
    @NeedLogin
    public Result deleteBank(HttpServletRequest request, @RequestParam Integer bankId) {
        int i = walletBankService.deleteBank(bankId);
        return i > 0 ? Result.ok("删除银行卡成功") : Result.error("删除银行卡失败");
    }


    @PostMapping("addCryptocurrency")
    @NeedLogin
    @ApiOperation("添加加密的钱包")
    public Result addWalletAddress(HttpServletRequest request,@RequestBody WalletCrVo walletCrVo) {
        WalletCrVo queryByCardNumber = walletCrService.queryByCrAddress(walletCrVo.getAddress());
        if (queryByCardNumber != null){
            if (queryByCardNumber.getIsDelete() == true){
                int i = walletCrService.updateDelete(walletCrVo.getAddress());
                return i > 0 ? Result.ok("添加钱包成功") : Result.error("添加钱包失败");
            }else {
                return Result.error("添加钱包失败,此钱包已经添加过了");
            }
        }else {
            User user = JwtUtil.getUser(request.getHeader("token"));
            walletCrVo.setUserId(user.getId().toString());
            walletCrVo.setName(user.getUsername());
            int i = walletCrService.insertBank(walletCrVo);
            return i > 0 ? Result.ok("添加钱包成功") : Result.error("添加钱包失败");
        }
    }

    @GetMapping("crList")
    @NeedLogin
    public Result crList(HttpServletRequest request) {
        User user = JwtUtil.getUserByRequestServlet(request);
        return Result.ok(walletCrService.getCrList(user.getId().toString()));
    }
}
