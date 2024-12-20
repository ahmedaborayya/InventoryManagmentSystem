-- Create the InventoryManagement database if it doesn't exist
CREATE DATABASE InventoryManagement;
GO

USE InventoryManagement;
GO

-- Create the Admin table
CREATE TABLE [dbo].[Admin](
    [AdminID] [int] IDENTITY(1,1) NOT NULL,
    L,
      NULL,
RED 
(
    [AdmX = OFF, ST= OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
    [Username] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
GO

-- Create the Customer table
CREATE TABLE [dbo].[Customer](
    [CustomerID] [int] IDENTITY(1,1) NOT NULL,
      NOT NULL,
      NULL,
      NULL,
      NULL,
      NULL,
      NULL,
PRIMARY KEY CLUSTERED 
(
 WITH (PAD_INDEXOMPUTE = OFFF, ALLOW_RAGE_LOCKS =TIAL_KEY = ARY];
GO

-- Create the Order table
CREATE TABLE [dbo].[Order](
    [OrderID] [int] IDENTITY(1,1) NOT NULL,
    [CustomerID] [int] NOT NULL,
    [OrderDate] [date] NOT NULL,
    [TotalAmount] [decimal](10, 2) NULL,
PRIMARY KEY CLUSTERED 
(
    [OrderID] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
GO

-- Create the Order_Product table
CREATE TABLE [dbo].[Order_Product](
    [OrderProductID] [int] IDENTITY(1,1) NOT NULL,
    [OrderID] [int] NOT NULL,
    [ProductID] [int] NOT NULL,
    [Quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [OrderProductID] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
GO

-- Create the Product table
CREATE TABLE [dbo].[Product](
    [ProductID] [int] IDENTITY(1,1) NOT NULL,
      NOT NULL,
      NULL,
    [Quantity] [int] NOT NULL,
    [Price] [decimal](10, 2) NOT NULL,
      NULL,
    [SupplierID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [ProductID] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, ALLOW_ROW = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
GO

-- CreEATE TABLE [dbo].[Shipment](
    [ShipmentID] [int] IDENTITY(1,1) NOT NULL,
    [OrderID] [int] NOT NULL,
    [WarehouseID] [int] NOT NULL,
    [ShipmentDate] [date] NOT NULL,
      NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [ShipmentID] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
GO

-- Create the Supplier table
CREATE TABLE [dbo].[Supplier]] IDENTITY(1,1) NOT NULL,
      NOT NULL,
    [ContactPerson] [nvarchar](100) NULL,
      NULL,
PRIMARY KEY CLUSTERED 
(
    [SupplierID] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
GO

-- Create the Warehouse tause](
    [WareT NULL,
   ity] [int] NOT NULL,
      NULL,
PRIMARY KEY CLUSTERED 
(
    [WarehouseID] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];
GO

-- Add foreign key constraints
ALTER TABLE [dbo].[Order] WITH CHECK ADD FOREIGN KEY([Cu].[Customer] ([CustomerID]);
GO

ALTER TABLE [TH CHECK ADD FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order] ([OrderID]);
GO

ALTER TABLE [dbo].[Order_Product] WITH CHECK ADD FOREIGN KEY([ProductID])
REFERENCES [dbo].[Product] ([ProductID]);
GO

ALTER TABLE [dbo].[Product] WITH CHECK ADD FOREIGN KEY([SupplierID])
REFERENCES [dbo].[Supplier] ([SupplierID]);
GO

ALTER TABLE [dbo].[Shipment] WITH CHECK ADD FOREIGN KEY([OrderID])
REFERENCES [dbo].[Order] ([OrderID]);
GO

ALTER TABLE [dbo].[Shipment] WITH CHECK ADD FOREIGN KEY([WarehouseID])
REFERENCES [dbo].[Warehouse] ([WarehouseID]);
GO

-- Add CHECK constraints
ALTER TABLE [dbo].[Order_Product] WITH CHECK ADD CHECK (([Quantity] > (0)));
GO

ALTER TABLE [dbo].[Product] WITH CHECK ADD CHECK (([Quantity] >= (0)));
GO

ALTER TABLE [dbo].[Warehouse] WITH CHECK ADD CHECK (([Capacity] >= (0)));
GO
