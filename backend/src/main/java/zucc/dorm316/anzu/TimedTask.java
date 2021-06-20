package zucc.dorm316.anzu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zucc.dorm316.anzu.entity.GoodsEntity;
import zucc.dorm316.anzu.entity.GoodsOrderEntity;
import zucc.dorm316.anzu.entity.MerchantEntity;
import zucc.dorm316.anzu.service.TblGoodsOrderService;
import zucc.dorm316.anzu.service.TblGoodsService;
import zucc.dorm316.anzu.service.TblMerchantService;
import zucc.dorm316.anzu.service.TblUserService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class TimedTask {
    @Autowired
    TblGoodsOrderService tblGoodsOrderService;
    @Autowired
    TblGoodsService tblGoodsService;
    @Autowired
    TblUserService tblUserService;
    @Autowired
    TblMerchantService tblMerchantService;

   @Scheduled(cron = "*/15 *  * * * ?")
    public void Schedule(){
        List<GoodsOrderEntity> goodsOrderEntityList = tblGoodsOrderService.findAll();
        for (int i=0;i<goodsOrderEntityList.size();i++){
           GoodsOrderEntity goodsOrderEntity = goodsOrderEntityList.get(i);
           GoodsEntity goodsEntity = tblGoodsService.findById(goodsOrderEntity.getGoodsId());
           MerchantEntity merchantEntity = tblMerchantService.findByMerchantId(goodsOrderEntity.getMerchantId());
           long dueTime = goodsOrderEntity.getReceivingTime().getTime() + (long)goodsOrderEntity.getLeaseTime()*1000;
           long nowTime = System.currentTimeMillis()+13*60*60*1000;
           double perPrice = goodsEntity.getPrice() / (goodsEntity.getLeaseTime()*1.0/60/60/24) * 1.5;
           if ( dueTime < nowTime)
           {
               if (goodsOrderEntity.getOrderStatus() == 5){
                   goodsOrderEntity.setOrderStatus(6);
               }
              if (goodsOrderEntity.getDeposit() > (goodsEntity.getDeposit()-((int)((nowTime-dueTime)*1.0/1000/60/60/24)+1)*perPrice)*goodsOrderEntity.getGoodsNum()){
                    goodsOrderEntity.setDeposit((goodsEntity.getDeposit()-((int)((nowTime-dueTime)*1.0/1000/60/60/24)+1)*perPrice)*goodsOrderEntity.getGoodsNum());
              }
              if (goodsOrderEntity.getDeposit() < 0){
                  goodsOrderEntity.setDeposit(0);
                  goodsOrderEntity.setOrderStatus(4);
                  tblMerchantService.modifyMerchant(merchantEntity.getId(),merchantEntity.getAccount(),merchantEntity.getPassword(),merchantEntity.getMerchantName(),merchantEntity.getBalance()+goodsOrderEntity.getGoodsNum()*goodsEntity.getDeposit()+goodsOrderEntity.getGoodsTotalPrice(),merchantEntity.getAdminFlag());
              }
              tblGoodsOrderService.modifyGoodsOrder(goodsOrderEntity.getUserId(),goodsOrderEntity.getGoodsId(),goodsOrderEntity.getMerchantId(),goodsOrderEntity.getUserAddressId(),goodsOrderEntity.getMerchantAddressId(),goodsOrderEntity.getOrderStatus(),goodsOrderEntity.getGoodsNum(),goodsOrderEntity.getGoodsTotalPrice(),goodsOrderEntity.getReceivingTime(),goodsOrderEntity.getLeaseTime(),goodsOrderEntity.getDeposit(),goodsOrderEntity.getId());
           }
    }
}
}
