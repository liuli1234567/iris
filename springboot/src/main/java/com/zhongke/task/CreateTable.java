package com.zhongke.task;

import com.zhongke.mapper.OrderMapper;
import com.zhongke.pojo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName CreateTable
 * @Description 动态创建交易流水表
 * @Author liuli
 * @Date 2020/4/28 18:43
 * @Version 1.0
 **/
@Configuration
public class CreateTable {
    private final Logger log = LoggerFactory.getLogger(CreateTable.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired(required = false)
    private OrderMapper orderMapper;
    @Value("${spring.datasource.driver-class-name}")
    private String mysqlDriver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${mysql.table.orderNum}")
    private int orderNum;
    @Value("${mysql.table.orderNewName}")
    private String orderTableNewName;
    @Value("${mysql.table.orderAllName}")
    private String orderTableAllName;
    private Connection conn = null;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void createTable(){
        String tableAllName = null;
        int count = orderMapper.selectCount(new Order());
        if (count>=orderNum){
            try {
                Class.forName(mysqlDriver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                int num = (int) ((Math.random()*9+1)*10000);
                String tableName = "zk_order"+ num;
                String allName = (String) redisTemplate.boundValueOps(orderTableAllName).get();
                if (!StringUtils.isEmpty(allName)){
                    while (true){
                        boolean isExist = getName(tableName, allName.split(","));
                        if (isExist){
                            num = (int) ((Math.random()*9+1)*10000);
                            tableName = "zk_order"+ num;
                        }else {
                            break;
                        }
                    }
                }
                String tableSql = "CREATE TABLE "+tableName+" (\n" +
                        "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `order_id` varchar(35) DEFAULT NULL COMMENT '订单号',\n" +
                        "  `order_amount` decimal(22,8) DEFAULT NULL COMMENT '订单金额',\n" +
                        "  `buyer_logon_id` varchar(32) DEFAULT NULL COMMENT '买家支付宝账号',\n" +
                        "  `buyer_user_id` varchar(32) DEFAULT NULL COMMENT '买家userID',\n" +
                        "  `status` int(4) DEFAULT NULL COMMENT '0 未支付 1 已支付 -1 已取消 -2 退款 -3 支付失败',\n" +
                        "  `code` varchar(8) DEFAULT NULL COMMENT '支付状态码',\n" +
                        "  `msg` varchar(32) DEFAULT NULL COMMENT '支付描述',\n" +
                        "  `sub_code` varchar(32) DEFAULT NULL COMMENT '支付错误码',\n" +
                        "  `sub_msg` varchar(128) DEFAULT NULL COMMENT '支付错误描述',\n" +
                        "  `pay_method` varchar(25) DEFAULT NULL COMMENT '支付方式',\n" +
                        "  `pay_aisle` varchar(25) DEFAULT NULL COMMENT '支付通道',\n" +
                        "  `actually_paid` decimal(22,8) DEFAULT NULL COMMENT '实付金额',\n" +
                        "  `discount` decimal(22,8) DEFAULT NULL COMMENT '优惠金额',\n" +
                        "  `transaction_id` varchar(32) DEFAULT NULL COMMENT '交易流水号',\n" +
                        "  `spu_ids` varchar(500) DEFAULT NULL COMMENT '商品id集合',\n" +
                        "  `jam_id` int(11) DEFAULT NULL COMMENT '优惠卷id',\n" +
                        "  `jam_name` varchar(64) DEFAULT NULL COMMENT '优惠卷名称',\n" +
                        "  `jam_type` varchar(32) DEFAULT NULL COMMENT '优惠卷类型',\n" +
                        "  `jam_amount` double(8,0) DEFAULT NULL COMMENT '优惠券金额',\n" +
                        "  `full_rule_id` int(11) DEFAULT NULL COMMENT '满减规则id',\n" +
                        "  `fund_channel` varchar(32) DEFAULT NULL COMMENT '支付渠道',\n" +
                        "  `refund_buyer_amount` decimal(22,2) DEFAULT NULL COMMENT '买家退款金额',\n" +
                        "  `refund_discount_amount` decimal(22,2) DEFAULT NULL COMMENT '平台优惠退款金额',\n" +
                        "  `refund_mdiscount_amount` decimal(22,0) DEFAULT NULL COMMENT '商家优惠退款金额',\n" +
                        "  `device_id` int(11) NOT NULL COMMENT '设备id',\n" +
                        "  `member_id` int(11) DEFAULT NULL COMMENT '会员id',\n" +
                        "  `member_name` varchar(64) DEFAULT NULL COMMENT '会员名字',\n" +
                        "  `cashier_id` int(11) NOT NULL COMMENT '收银员id',\n" +
                        "  `cashier_name` varchar(25) DEFAULT NULL COMMENT '收银员名字',\n" +
                        "  `store_name` varchar(255) DEFAULT NULL COMMENT '门店名称',\n" +
                        "  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',\n" +
                        "  `createtime` datetime DEFAULT NULL COMMENT '创建时间',\n" +
                        "  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',\n" +
                        "  PRIMARY KEY (`id`) USING BTREE,\n" +
                        "  KEY `suoyi` (`order_amount`),\n" +
                        "  KEY `time` (`createtime`)\n" +
                        ") ENGINE=MyISAM AUTO_INCREMENT=2292518 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='订单表'";
                conn = DriverManager.getConnection(url, username, password);
                Statement smt = conn.createStatement();
                if (conn != null) {
                    log.info("数据库连接成功");
                    int i = smt.executeUpdate(tableSql);
                    if (i == 0){
                        log.info("表创建成功");
                        System.out.println(redisTemplate.boundValueOps("orderTableAllName").get());
                        System.out.println(redisTemplate.boundValueOps("orderTableNewName").get());
                        redisTemplate.boundValueOps(orderTableNewName).set(tableName); // 设置订单新表名
                        tableAllName = (String) redisTemplate.boundValueOps(orderTableAllName).get(); // 得到所有订单表名的字符串
                        if (StringUtils.isEmpty(tableAllName)) {
                            redisTemplate.boundValueOps(orderTableAllName).set("zk_order,"+tableName+",");
                        }else {
                            tableAllName = redisTemplate.boundValueOps(orderTableAllName).get().toString();
                            redisTemplate.boundValueOps(orderTableAllName).set(tableAllName+tableName+",");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean getName(String name,String[] names){
        for (String s : names) {
            if (s.equals(name)){
                return true;
            }
        }
        return false;
    }
}
