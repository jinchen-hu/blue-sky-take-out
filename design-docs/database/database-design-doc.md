# Database Design

## List of Tables

|    | Table name    | Comments                            |
|----|---------------|-------------------------------------|
| 1  | employee      | employee info                       |
| 2  | category      | dish category                       |
| 3  | dish          | dish info                           |
| 4  | dish_flavor   | dish flavors                        |
| 5  | setmeal       | meal combo                          |
| 6  | setmeal_dish  | relationship between dish and combo |
| 7  | user          | user info                           |
| 8  | address_book  | user address book                   |
| 9  | shopping_cart | cart info                           |
| 10 | orders        | order info                          |
| 11 | order_detail  | order details                       |

### 1. employee

`emloyee` stores the employee information

| Colum       | Data Type   | Description       | Comments             |
|-------------|-------------|-------------------|----------------------|
| id          | bigint      | Primary key       | increment            |
| name        | varchar(32) | employee name     |                      |
| username    | varchar(32) | employee username | Unique               |
| password    | varchar(64) | password          |                      |
| phone       | varchar(11) | phone number      |                      |
| sex         | varchar(2)  | gender            |                      |
| id_number   | varchar(18) | ID                |                      |
| status      | int         | account status    | 1-active 0-suspended |
| create_time | datetime    | create time       |                      |
| update_time | datetime    | update time       |                      |
| create_user | bigint      | create user id    |                      |
| update_user | bigint      | update user id    |                      |

### 2. category

`category` stores the categories for meals and combos：

| Column      | Data Type   | Description      | Comments          |
|-------------|-------------|------------------|-------------------|
| id          | bigint      | Primary key      | increment         |
| name        | varchar(32) | category name    | unique            |
| type        | int         | type             | 1-meal  2-combo   |
| sort        | int         | sort             | sorting           |
| status      | int         | status           | 1-active 0-banned |
| create_time | datetime    | create time      |                   |
| update_time | datetime    | last update time |                   |
| create_user | bigint      | creator id       |                   |
| update_user | bigint      | last updater id  |                   |

### 3. dish

`dish` stores meal information：

| Column      | Data Type     | Description      | Comments                 |
|-------------|---------------|------------------|--------------------------|
| id          | bigint        | Primary key      | increment                |
| name        | varchar(32)   | meal name        | Unique                   |
| category_id | bigint        | category id      | foreign key              |
| price       | decimal(10,2) | price            |                          |
| image       | varchar(255)  | pic path         |                          |
| description | varchar(255)  | description      |                          |
| status      | int           | status           | 1-on-sale 0-out-of-stack |
| create_time | datetime      | create time      |                          |
| update_time | datetime      | last update time |                          |
| create_user | bigint        | creator id       |                          |
| update_user | bigint        | last updater id  |                          |

### 4. dish_flavor

`dish_flavor` stores the flavor of dishes：

| Column  | Data Type    | Description | Comments    |
|---------|--------------|-------------|-------------|
| id      | bigint       | Primary key | increment   |
| dish_id | bigint       | dish id     | foreign key |
| name    | varchar(32)  | name        |             |
| value   | varchar(255) | value       |             |

### 5. setmeal

`setmeal` stores combo information：

| Column      | Data Type     | Description      | Comments                 |
|-------------|---------------|------------------|--------------------------|
| id          | bigint        | primary key      | increment                |
| name        | varchar(32)   | combo name       | unique                   |
| category_id | bigint        | category id      | foreign key              |
| price       | decimal(10,2) | price            |                          |
| image       | varchar(255)  | pic path         |                          |
| description | varchar(255)  | description      |                          |
| status      | int           | status           | 1-on-sale 0-out-of-stock |
| create_time | datetime      | create time      |                          |
| update_time | datetime      | last update time |                          |
| create_user | bigint        | creator id       |                          |
| update_user | bigint        | last updater id  |                          |

### 6. setmeal_dish

`setmeal_dish` stores relationship between meals and combos

| Column     | Data Type     | Description | Comment     |
|------------|---------------|-------------|-------------|
| id         | bigint        | primary key | increment   |
| setmeal_id | bigint        | combo id    | foreign key |
| dish_id    | bigint        | dish id     | foreign key |
| name       | varchar(32)   | dish name   |             |
| price      | decimal(10,2) | dish price  |             |
| copies     | int           | dish stock  |             |

### 7. user

`user` stores client user information

| Column      | Data Tyte    | Description              | Comments  |
|-------------|--------------|--------------------------|-----------|
| id          | bigint       | Primary key              | increment |
| openid      | varchar(45)  | wechat unique identifier |           |
| name        | varchar(32)  | name                     |           |
| phone       | varchar(11)  | phone number             |           |
| sex         | varchar(2)   | gender                   |           |
| id_number   | varchar(18)  | id number                |           |
| avatar      | varchar(500) | profile pic path         |           |
| create_time | datetime     | signup time              |           |

### 8. address_book

`address_book` stores address book for client users

| Column        | Data Tyte    | Description         | Comments     |
|---------------|--------------|---------------------|--------------|
| id            | bigint       | Primary key         | increment    |
| user_id       | bigint       | user id             | foreign key  |
| consignee     | varchar(50)  | consignee           |              |
| sex           | varchar(2)   | gender              |              |
| phone         | varchar(11)  | phone number        |              |
| province_code | varchar(12)  | province code       |              |
| province_name | varchar(32)  | province name       |              |
| city_code     | varchar(12)  | zip code            |              |
| city_name     | varchar(32)  | city name           |              |
| district_code | varchar(12)  | direct code         |              |
| district_name | varchar(32)  | direct name         |              |
| detail        | varchar(200) | detailed address    |              |
| label         | varchar(100) | label               | office, home |
| is_default    | tinyint(1)   | if default addresss | 1-yes 0-no   |

### 9. shopping_cart

shopping_cart表为购物车表，用于存储C端用户的购物车信息。具体表结构如下：

| Column      | Data Tyte      | Description         | Comments     |
| ----------- | ------------- | ------------ | -------- |
| id          | bigint        | Primary key         | increment     |
| name        | varchar(32)   | 商品名称     |          |
| image       | varchar(255)  | 商品图片路径 |          |
| user_id     | bigint        | 用户id       | foreign key |
| dish_id     | bigint        | 菜品id       | foreign key |
| setmeal_id  | bigint        | 套餐id       | foreign key |
| dish_flavor | varchar(50)   | 菜品口味     |          |
| number      | int           | 商品数量     |          |
| amount      | decimal(10,2) | 商品单价     |          |
| create_time | datetime      | 创建时间     |          |

### 10. orders

orders表为订单表，用于存储C端用户的订单数据。具体表结构如下：

| Column                  | Data Tyte      | Description         | Comments|
| ----------------------- | ------------- | ------------ | ----------------------------------------------- |
| id                      | bigint        | Primary key         | increment                                            |
| number                  | varchar(50)   | 订单号       |                                                 |
| status                  | int           | 订单状态     | 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 |
| user_id                 | bigint        | 用户id       | foreign key                                        |
| address_book_id         | bigint        | 地址id       | foreign key                                        |
| order_time              | datetime      | 下单时间     |                                                 |
| checkout_time           | datetime      | 付款时间     |                                                 |
| pay_method              | int           | 支付方式     | 1微信支付 2支付宝支付                           |
| pay_status              | tinyint       | 支付状态     | 0未支付 1已支付 2退款                           |
| amount                  | decimal(10,2) | 订单金额     |                                                 |
| remark                  | varchar(100)  | 备注信息     |                                                 |
| phone                   | varchar(11)   | 手机号       |                                                 |
| address                 | varchar(255)  | 详细地址信息 |                                                 |
| user_name               | varchar(32)   | 用户姓名     |                                                 |
| consignee               | varchar(32)   | 收货人       |                                                 |
| cancel_reason           | varchar(255)  | 订单取消原因 |                                                 |
| rejection_reason        | varchar(255)  | 拒单原因     |                                                 |
| cancel_time             | datetime      | 订单取消时间 |                                                 |
| estimated_delivery_time | datetime      | 预计送达时间 |                                                 |
| delivery_status         | tinyint       | 配送状态     | 1立即送出  0选择具体时间                        |
| delivery_time           | datetime      | 送达时间     |                                                 |
| pack_amount             | int           | 打包费       |                                                 |
| tableware_number        | int           | 餐具数量     |                                                 |
| tableware_status        | tinyint       | 餐具数量状态 | 1按餐量提供  0选择具体数量                      |

### 11. order_detail

order_detail表为订单明细表，用于存储C端用户的订单明细数据。具体表结构如下：

| Column      | Data Tyte     | Description         | Comments    |
| ----------- |---------------| ------------ |-------------|
| id          | bigint        | Primary key         | increment   |
| name        | varchar(32)   | 商品名称     |             |
| image       | varchar(255)  | 商品图片路径 |             |
| order_id    | bigint        | 订单id       | foreign key |
| dish_id     | bigint        | 菜品id       | foreign key |
| setmeal_id  | bigint        | 套餐id       | foreign key |
| dish_flavor | varchar(50)   | 菜品口味     |             |
| number      | int           | 商品数量     |             |
| amount      | decimal(10,2) | 商品单价     |             |

