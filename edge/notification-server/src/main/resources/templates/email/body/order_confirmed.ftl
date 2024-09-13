<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            color: #333;
        }
        .header {
            background-color: #f8f8f8;
            padding: 10px;
            text-align: center;
            font-size: 20px;
            font-weight: bold;
        }
        .content {
            margin: 20px;
        }
        .order-summary {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }
        .order-summary th, .order-summary td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .order-summary th {
            background-color: #f2f2f2;
        }
        .total {
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="header">Order Confirmation</div>

<div class="content">
    <p>Dear ${order.customer.username},</p>

    <p>Thank you for your order! Your order ID is <strong>${order.id}</strong>, placed on <strong>${order.createdAt?string("yyyy-MM-dd HH:mm:ss")}</strong>.</p>

    <p>Your order status is: <strong>${order.status}</strong>.</p>

    <table class="order-summary">
        <thead>
        <tr>
            <th>Product ID</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
        </tr>
        </thead>
        <tbody>
        <#list order.orderItems as orderItem>
            <tr>
                <td>${orderItem.productId}</td>
                <td>${orderItem.productName}</td>
                <td>${orderItem.productPrice?string("$#,##0.00")}</td>
                <td>${orderItem.quantity}</td>
                <td>${(orderItem.productPrice * orderItem.quantity)?string("$#,##0.00")}</td>
            </tr>
        </#list>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4" class="total">Total Amount</td>
            <td class="total">${order.amount?string("$#,##0.00")}</td>
        </tr>
        </tfoot>
    </table>

    <p>Best regards,</p>
    <p>Your Company Team</p>
</div>
</body>
</html>
