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

`shopping_cart` stores client shopping cart information

| Column      | Data Tyte     | Description   | Comments    |
|-------------|---------------|---------------|-------------|
| id          | bigint        | Primary key   | increment   |
| name        | varchar(32)   | dish name     |             |
| image       | varchar(255)  | dish pic path |             |
| user_id     | bigint        | user id       | foreign key |
| dish_id     | bigint        | dish id       | foreign key |
| setmeal_id  | bigint        | combo id      | foreign key |
| dish_flavor | varchar(50)   | flavor        |             |
| number      | int           | quantity      |             |
| amount      | decimal(10,2) | total price   |             |
| create_time | datetime      | create time   |             |

### 10. orders

`orders` stores order information

| Column                  | Data Tyte     | Description               | Comments                                                                               |
|-------------------------|---------------|---------------------------|----------------------------------------------------------------------------------------|
| id                      | bigint        | Primary key               | increment                                                                              |
| number                  | varchar(50)   | order number              |                                                                                        |
| status                  | int           | order status              | 1-pending pay 2-pending accepted 3-accepted <br/>4-deliverying 5-completed 6-cancelled |
| user_id                 | bigint        | user id                   | foreign key                                                                            |
| address_book_id         | bigint        | address id                | foreign key                                                                            |
| order_time              | datetime      | order time                |                                                                                        |
| checkout_time           | datetime      | payment time              |                                                                                        |
| pay_method              | int           | pay method                | 1-wechat pay 2-alipay                                                                  |
| pay_status              | tinyint       | pay status                | 0-pending pay 1-paid 2-refund                                                          |
| amount                  | decimal(10,2) | order amount              |                                                                                        |
| remark                  | varchar(100)  | comments                  |                                                                                        |
| phone                   | varchar(11)   | phone number              |                                                                                        |
| address                 | varchar(255)  | detailed address          |                                                                                        |
| user_name               | varchar(32)   | user name                 |                                                                                        |
| consignee               | varchar(32)   | consignee                 |                                                                                        |
| cancel_reason           | varchar(255)  | cancellation reason       |                                                                                        |
| rejection_reason        | varchar(255)  | rejection reason          |                                                                                        |
| cancel_time             | datetime      | cancel time               |                                                                                        |
| estimated_delivery_time | datetime      | anticipated delivery time |                                                                                        |
| delivery_status         | tinyint       | delivery status           | 1-instant  0-chosen time                                                               |
| delivery_time           | datetime      | delivered time            |                                                                                        |
| pack_amount             | int           | packaging amount          |                                                                                        |
| tableware_number        | int           | tableware amount          |                                                                                        |
| tableware_status        | tinyint       | tableware status          | 1-based on dish amount  0-chosen by user                                               |

### 11. order_detail

`order_detail` stores detailed order information

| Column      | Data Tyte     | Description   | Comments    |
|-------------|---------------|---------------|-------------|
| id          | bigint        | Primary key   | increment   |
| name        | varchar(32)   | meal name     |             |
| image       | varchar(255)  | meal pic path |             |
| order_id    | bigint        | order id      | foreign key |
| dish_id     | bigint        | dish id       | foreign key |
| setmeal_id  | bigint        | set id        | foreign key |
| dish_flavor | varchar(50)   | dish flavor   |             |
| number      | int           | amount        |             |
| amount      | decimal(10,2) | unit price    |             |

