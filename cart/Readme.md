<h1>JMS ActiveMQ Cart - Order</h1>
<p>Simple example how Spring JMS + ActiveMQ work together to save the cart from one service to another.</p>

<h3>Run ActiveMQ in the Docker</h3>
<p><code>docker run -p 8161:8161 -p 61616:61616 --name activemq webcenter/activemq:latest </code></p>

<h3>ActiveMQ admin panel</h3>
<p><code>http://localhost:8161/admin/</code></p>
<p>
<code>Login: admin</code><br>
<code>Password: admin</code>
</p>

<h3>Cart service</h3>
<table>
<thead>
<tr>
<th align="left">Method</th>
<th align="left">Resource</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td align="left"><code>POST</code></td>
<td align="left"><code>/cart/saveOrder</code></td>
<td align="left">Save an order</td>
</tr>
</tbody>
</table>

<h3>Order service</h3>
<table>
<thead>
<tr>
<th align="left">Method</th>
<th align="left">Resource</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td align="left"><code>GET</code></td>
<td align="left"><code>/orders</code></td>
<td align="left">Get all orders</td>
</tr>
<tr>
<td align="left"><code>GET</code></td>
<td align="left"><code>/orders/{id}</code></td>
<td align="left">Get one order</td>
</tr>
</tbody>
</table>

<h3>Testing</h3>
<p>
<h4>Save order:</h4>
<code>curl -X POST \
  http://localhost:8081/cart/saveOrder \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Length: 537' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8081' \
  -H 'Postman-Token: 09ab565e-42b6-49a4-be12-6a24d0e07040,3284bf6e-b956-4e16-8032-4eeab29eb730' \
  -H 'User-Agent: PostmanRuntime/7.19.0' \
  -H 'cache-control: no-cache' \
  -d '{
    "firstName": "Thomas",
    "lastName": "White",
    "address": "New York street 155.5",
    "products": [
        {
            "productId": 1,
            "name": "Iphone X",
            "quantity": 1,
            "price": 1500
        },
         {
            "productId": 2,
            "name": "Cannon X1500",
            "quantity": 1,
            "price": 5500
        }
        ,
         {
            "productId": 3,
            "name": "Samsung TV",
            "quantity": 2,
            "price": 4500
        }
    ]
}'s</code>
</p>

<p>
<h4>Find all orders:</h4>
<code>curl -X GET \
  http://localhost:8081/orders \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8081' \
  -H 'Postman-Token: af7de86a-1b9e-44cb-8fab-fb59f75aa22f,df247439-35d8-4544-ac7b-c977571bfd82' \
  -H 'User-Agent: PostmanRuntime/7.19.0' \
  -H 'cache-control: no-cache'</code>
</p>

<p>
<h4>Find one order:</h4>
<code>curl -X GET \
  http://localhost:8081/orders/1 \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:8081' \
  -H 'Postman-Token: bae64828-286b-47fe-a8b5-480a4395dd49,9abec91b-122e-4f70-a500-701692a8a4ae' \
  -H 'User-Agent: PostmanRuntime/7.19.0' \
  -H 'cache-control: no-cache'</code>
</p>
