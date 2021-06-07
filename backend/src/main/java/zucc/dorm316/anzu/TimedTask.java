package zucc.dorm316.anzu;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import zucc.dorm316.anzu.entity.GoodsEntity;
import zucc.dorm316.anzu.entity.GoodsOrderEntity;
import zucc.dorm316.anzu.service.TblGoodsOrderService;
import zucc.dorm316.anzu.service.TblGoodsService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class TimedTask {
    private Logger logger =Logger.getLogger(TimedTask.class);
    @Autowired
    TblGoodsOrderService tblGoodsOrderService;
    @Autowired
    TblGoodsService tblGoodsService;

    @Scheduled(cron = "* */5 * * * ?")
    public void testSca(){
        List<GoodsOrderEntity> goodsOrderEntityList = tblGoodsOrderService.findAll();
        for (int i=0;i<goodsOrderEntityList.size();i++){
           GoodsOrderEntity goodsOrderEntity = goodsOrderEntityList.get(i);
           GoodsEntity goodsEntity = tblGoodsService.findById(goodsOrderEntity.getGoodsId());
           long dueTime = goodsOrderEntity.getReceivingTime().getTime() + goodsOrderEntity.getLeaseTime()*1000;
           long nowTime = System.currentTimeMillis();
           double perPrice = goodsEntity.getPrice() / (goodsEntity.getLeaseTime()*1.0/60/60/24) * 1.5;
           if ( dueTime < nowTime)
           {
               if (goodsOrderEntity.getOrderStatus() == 5){
                   goodsOrderEntity.setOrderStatus(6);
               }
               System.out.println(goodsEntity.getDeposit()-((int)((nowTime-dueTime)*1.0/1000/60/60/24)+1)*perPrice);
              if (goodsOrderEntity.getDeposit() > goodsEntity.getDeposit()-((int)((nowTime-dueTime)*1.0/1000/60/60/24)+1)*perPrice){
                    goodsOrderEntity.setDeposit(goodsEntity.getDeposit()-((int)((nowTime-dueTime)*1.0/1000/60/60/24)+1)*perPrice);
              }
              if (goodsOrderEntity.getDeposit() < 0){
                  goodsOrderEntity.setDeposit(0);
                  goodsOrderEntity.setOrderStatus(4);
              }
              tblGoodsOrderService.modifyGoodsOrder(goodsOrderEntity.getUserId(),goodsOrderEntity.getGoodsId(),goodsOrderEntity.getMerchantId(),goodsOrderEntity.getUserAddressId(),goodsOrderEntity.getMerchantAddressId(),goodsOrderEntity.getOrderStatus(),goodsOrderEntity.getGoodsNum(),goodsOrderEntity.getGoodsTotalPrice(),goodsOrderEntity.getReceivingTime(),goodsOrderEntity.getLeaseTime(),goodsOrderEntity.getDeposit(),goodsOrderEntity.getId());
            }
    }
}
}
