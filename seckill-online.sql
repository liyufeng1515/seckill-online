DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(16) DEFAULT 0 COMMENT '商品名称',
  `description` varchar(255) DEFAULT 0 COMMENT '商品描述',
  `img_url` varchar(64) DEFAULT 0 COMMENT '商品图片',
  `content_Id` bigint(20) NOT NULL DEFAULT 0,
  `unit_price` bigint(20) DEFAULT 0 COMMENT '商品单价',
  `created_stamp` datetime not null,
  `last_updated_stamp` timestamp,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4;

INSERT INTO `product` (`product_name`,`description`,`img_url`,`content_Id`,`unit_price`,`created_stamp`) VALUES
('进口亮光纱松背心','类型精选,毛感丰沛,趣味性手工撞色立体抽绳设计','/img/Z0193KV01-64.jpg',100000,1950,'2018-07-12 19:06:20'),
('日本纸纱Polo领短袖T恤','复古摩登的一款日本纸纱Polo领短袖T恤，轻盈柔和。WHM延续使用的日本和纸材质，纺织成更细腻的规格，织造成更轻薄织物，干爽透气，柔软舒适。','/img/W191KT04.jpg',100001,1650,'2018-07-12 19:06:20');

DROP TABLE IF EXISTS `user_login`;
CREATE TABLE `user_login` (
  `user_login_id` bigint(30) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(65) NOT NULL,
  `created_stamp` datetime not null,
  `last_updated_stamp` timestamp,
  PRIMARY KEY (`user_login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

  drop table if exists `seckill_order`;
  create table `seckill_order`(
  `order_id` bigint not null,
  `product_id` bigint(20) not null,
  `quantity` int not null,
  `created_by` varchar(30) not null,
  `grand_total` bigint not null default 0,
  `created_stamp` datetime not null,
  `last_updated_stamp` timestamp,
  primary key (`order_id`)
  )engine=innodb default charset=utf8mb4;

drop table if exists `inventory_item`;
  create table `inventory_item`(
  `inventory_item_id`  bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) not null,
  `quantity_total` bigint not null default 0,
  `created_stamp` datetime not null,
  `last_updated_stamp` timestamp,
  primary key (`inventory_item_id`),
  index `indx_product_id` (`product_id`)
  )engine=innodb default charset=utf8mb4;

 insert into`inventory_item` (`product_id`,`quantity_total`,`created_stamp`)
 values
 ('100000','100','2018-07-12 19:06:20'),('10000','100','2018-07-12 19:06:20'),
 ('100001','100','2018-07-12 19:06:20'),('10000','100','2018-07-12 19:06:20');
