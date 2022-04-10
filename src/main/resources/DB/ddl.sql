-- Create a new database called 'food_delivery_system_db'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Create the new database if it does not exist already
IF NOT EXISTS (
    SELECT [name]
        FROM sys.databases
        WHERE [name] = N'food_delivery_system_db'
)
CREATE DATABASE food_delivery_system_db
GO


-- Create table restaurant

CREATE TABLE [dbo].[restaurant](
    [id] [bigint] NOT NULL,
    [address] [varchar](150) NOT NULL,
    [closing_time] [time](7) NOT NULL,
    [contact] [varchar](10) NOT NULL,
    [mail] [varchar](40) NOT NULL,
    [name] [varchar](100) NOT NULL,
    [opening_time] [time](7) NOT NULL,
    [wallet_id] [binary](255) NULL
    ) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[restaurant] ADD PRIMARY KEY CLUSTERED
    (
    [id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[restaurant]  WITH CHECK ADD  CONSTRAINT [FKft60nqc93x4737n1yv1ry837u] FOREIGN KEY([wallet_id])
    REFERENCES [dbo].[business_wallet] ([id])
    GO
ALTER TABLE [dbo].[restaurant] CHECK CONSTRAINT [FKft60nqc93x4737n1yv1ry837u]
    GO


-- create table users

CREATE TABLE [dbo].[users](
    [id] [bigint] NOT NULL,
    [contact_num] [varchar](10) NOT NULL,
    [name] [varchar](40) NOT NULL,
    [wallet_id] [binary](255) NULL
    ) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[users] ADD PRIMARY KEY CLUSTERED
    (
    [id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [FK2ndfo1foff7a36v7f6sst12ix] FOREIGN KEY([wallet_id])
    REFERENCES [dbo].[wallet] ([id])
    GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [FK2ndfo1foff7a36v7f6sst12ix]
    GO


-- create table business_wallet

CREATE TABLE [dbo].[business_wallet](
    [id] [binary](255) NOT NULL,
    [balance] [float] NOT NULL
    ) ON [PRIMARY]
    GO
    SET ANSI_PADDING ON
    GO
ALTER TABLE [dbo].[business_wallet] ADD PRIMARY KEY CLUSTERED
    (
    [id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    GO


-- create table dish

CREATE TABLE [dbo].[dish](
    [id] [bigint] NOT NULL,
    [name] [varchar](50) NOT NULL,
    [price] [float] NOT NULL,
    [type] [varchar](10) NOT NULL,
    [restaurant_id] [bigint] NULL
    ) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[dish] ADD PRIMARY KEY CLUSTERED
    (
    [id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[dish]  WITH CHECK ADD  CONSTRAINT [FKt13glsbe9ivpka00hbeg539cv] FOREIGN KEY([restaurant_id])
    REFERENCES [dbo].[restaurant] ([id])
    GO
ALTER TABLE [dbo].[dish] CHECK CONSTRAINT [FKt13glsbe9ivpka00hbeg539cv]
    GO

-- create table orders

CREATE TABLE [dbo].[orders](
    [id] [binary](255) NOT NULL,
    [order_date] [datetime2](7) NOT NULL,
    [status] [varchar](10) NOT NULL,
    [dish_id] [bigint] NOT NULL,
    [user_id] [bigint] NOT NULL
    ) ON [PRIMARY]
    GO
    SET ANSI_PADDING ON
    GO
ALTER TABLE [dbo].[orders] ADD PRIMARY KEY CLUSTERED
    (
    [id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK32ql8ubntj5uh44ph9659tiih] FOREIGN KEY([user_id])
    REFERENCES [dbo].[users] ([id])
    GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK32ql8ubntj5uh44ph9659tiih]
    GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FKen96ivb6vdylv2s1eeoxie4c6] FOREIGN KEY([dish_id])
    REFERENCES [dbo].[dish] ([id])
    GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FKen96ivb6vdylv2s1eeoxie4c6]
    GO


-- create table transactions

CREATE TABLE [dbo].[transactions](
    [id] [binary](255) NOT NULL,
    [dish_name] [varchar](255) NOT NULL,
    [transaction_amount] [float] NOT NULL,
    [transaction_date] [datetime2](7) NOT NULL,
    [user_id] [bigint] NOT NULL
    ) ON [PRIMARY]
    GO
    SET ANSI_PADDING ON
    GO
ALTER TABLE [dbo].[transactions] ADD PRIMARY KEY CLUSTERED
    (
    [id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    GO
ALTER TABLE [dbo].[transactions]  WITH CHECK ADD  CONSTRAINT [FKqwv7rmvc8va8rep7piikrojds] FOREIGN KEY([user_id])
    REFERENCES [dbo].[users] ([id])
    GO
ALTER TABLE [dbo].[transactions] CHECK CONSTRAINT [FKqwv7rmvc8va8rep7piikrojds]
    GO


-- create table wallet

CREATE TABLE [dbo].[wallet](
    [id] [binary](255) NOT NULL,
    [balance] [float] NOT NULL
    ) ON [PRIMARY]
    GO
    SET ANSI_PADDING ON
    GO
ALTER TABLE [dbo].[wallet] ADD PRIMARY KEY CLUSTERED
    (
    [id] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    GO