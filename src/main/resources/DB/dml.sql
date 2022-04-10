
-- Insert rows into table 'orders' in schema '[dbo]'
INSERT INTO [dbo].[orders]
( -- Columns to insert data into
    [id], [order_date], [status],[dish_id],[user_id]
)
VALUES
    ( -- First row: values for the columns in the list above
    1, 'Sun Apr 10 05:52:10 GMT 2022', 'confirm','1','1'
    ),
    ( -- Second row: values for the columns in the list above
    2, 'Sun Apr 12 07:52:10 GMT 2022', 'confirm','2','1'
    )

    GO


-- Insert rows into table 'restaurant' in schema '[dbo]'
INSERT INTO [dbo].[restaurant]
( -- Columns to insert data into
    [id], [address], [closing_time],[contact],[mail],[name],[opening_time],[wallet_id]
)
VALUES
    ( -- First row: values for the columns in the list above
    1, 'Sector 44 noida UP, 201301', '19:00:00','7457037445','mail123@mail.com','abc restaurant','10:00:00','4e8b2a24-e406-4e4f-98f0-af98da0f3888'
    ),
    ( -- Second row: values for the columns in the list above
    2, 'Sector 16 noida UP, 201301', '19:00:00','9978451298','mail12@mail.com','Raz restaurant','10:00:00','729debf7-103a-48ac-b491-df7cb6a13eaa'
    )
    GO



-- Insert rows into table 'business_wallet' in schema '[dbo]'
INSERT INTO [dbo].[business_wallet]
( -- Columns to insert data into
    [id], [balance]
)
VALUES
    ( -- First row: values for the columns in the list above
    '729debf7-103a-48ac-b491-df7cb6a13eaa', 0
    ),
    ( -- Second row: values for the columns in the list above
    '86b392f6-68b5-4e49-a77c-4fcc4b0ccbb8', 0
    )
    GO

-- Insert rows into table 'dish' in schema '[dbo]'
INSERT INTO [dbo].[dish]
( -- Columns to insert data into
    [id], [name], [price],[type],[restaurant_id]
)
VALUES
    ( -- First row: values for the columns in the list above
    1, 'Biryani', 100,'Non-Veg',1
    ),
    ( -- Second row: values for the columns in the list above
    2, 'Pizza', 200,'Veg',1
    ),
    ( -- Second row: values for the columns in the list above
    3, 'Veg thali', 100,'Veg',1
    )
    Go



-- Insert rows into table 'transactions' in schema '[dbo]'
INSERT INTO [dbo].[transactions]
( -- Columns to insert data into
    [id], [dish_name], [transaction_amount],[transaction_date],[user_id]
)
VALUES
    ( -- First row: values for the columns in the list above
    '7c2b0aa8-b05e-4e75-ac87-d70b2e73449b','Biryani', 100, 'Sun Apr 10 05:52:10 GMT 2022',1
    ),
    ( -- Second row: values for the columns in the list above
    'c1d5a9ec-ae33-44f1-b331-200324577b5d','Pizza', 200, 'Sun Apr 11 05:52:10 GMT 2022',2
    )
    GO
SELECT TOP (1000) [id]
     ,[contact_num]
     ,[name]
     ,[wallet_id]
FROM [food_delivery_system_db].[dbo].[users]


-- Insert rows into table 'users' in schema '[dbo]'
INSERT INTO [dbo].[users]
( -- Columns to insert data into
    [id], [contact_num], [name],[wallet_id]
)
VALUES
    ( -- First row: values for the columns in the list above
    1, 9927396322, 'user1','991d71f2-4f4c-41e5-8667-96ab856eedab'
    ),
    ( -- Second row: values for the columns in the list above
    2, 9927396330, 'user2','26ec2c60-c64b-4932-8b46-135a3617bf39'
    )
    GO



-- Insert rows into table 'wallet' in schema '[dbo]'
INSERT INTO [dbo].[wallet]
( -- Columns to insert data into
    [id], [balance]
)
VALUES
    ( -- First row: values for the columns in the list above
    '991d71f2-4f4c-41e5-8667-96ab856eedab', 0
    ),
    ( -- Second row: values for the columns in the list above
    '26ec2c60-c64b-4932-8b46-135a3617bf39', 0
    )
-- Add more rows here
    GO