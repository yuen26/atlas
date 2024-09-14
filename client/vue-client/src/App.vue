<template>
  <div>
    <div id="app" class="container mt-5">
      <h3 class="text-center mb-4">Order Client</h3>

      <!-- Simple form to place an order -->
      <form @submit.prevent="placeOrder" class="card p-4 shadow-sm">
        <div class="form-group mb-3">
          <label for="address">Address:</label>
          <input v-model="address" id="address" type="text" class="form-control" placeholder="Enter address" />
        </div>

        <div class="form-group mb-3">
          <label for="product1">Product 1 Quantity:</label>
          <input v-model.number="product1Qty" id="product1" type="number" class="form-control" min="1" placeholder="Quantity" />
        </div>

        <div class="form-group mb-3">
          <label for="product2">Product 2 Quantity:</label>
          <input v-model.number="product2Qty" id="product2" type="number" class="form-control" min="1" placeholder="Quantity" />
        </div>

        <button type="submit" class="btn btn-primary">Place Order</button>
      </form>

      <!-- Display the order ID and status -->
      <div v-if="orderId" class="mt-4 card p-4 shadow-sm">
        <p>Order ID: {{ orderId }}</p>
        <p v-if="orderStatus">Order Status: {{ orderStatus }}</p>
        <p v-if="canceledReason">Canceled Reason: {{ canceledReason }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import SockJS from 'sockjs-client';
import {Stomp} from '@stomp/stompjs'; // Use @stomp/stompjs instead of stompjs

export default {
  data() {
    return {
      address: 'HongKong',
      product1Qty: 1,
      product2Qty: 1,
      orderId: null,
      orderStatus: null,
      canceledReason: null,
      eventSource: null,  // Track the SSE event source
      stompClient: null   // Track the WebSocket stomp client
    };
  },
  methods: {
    async placeOrder() {
      try {
        this.orderId = null;
        this.orderStatus = null;
        this.canceledReason = null;

        const response = await fetch('http://localhost:8080/api/orders/place', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJjdXN0b21lckBhdGxhcy5vcmciLCJpc3MiOiJteS1hcHAiLCJpYXQiOjE3MjYyMzI0MTEsImV4cCI6MTcyODgyNDQxMSwianRpIjoiMjI1ZWE2ODMtMGZjZi00MDA2LThhY2UtMzVkYjE4OTkyNDlkIiwidXNlcl9pZCI6Miwicm9sZSI6IkNVU1RPTUVSIn0.H19UxNC3APg6dQ0I6D6loqgFYR0ieiIrSU-Qj53dZqOeGTyMTeRKmGnGuOBCFv1HGZqhvmXEpN6Xvr5vh7DMcgYBApqmGmXxUt-YT5D3cKhL-eNxcrjKzcEL5FlgxRRqmyfSxP0AGqazz4nug9ApYfkoD_7S1fLX5q1G_8hGO28_YKeHsf6biLIQtvuN3q4AzmACiPxIfHB798AjM8G3FSCJiAE5LSwY3eHhqzM5mM1onj5u7bS5wHT49ZotwT6SM0wUBqzzgipwstm7HaLRkyRm0CpVMDDDSLaMtb8a3Ni0K2Ot-H4iYIrQJbKKvdnHoRl8oK-Zt77aLs8Vyhl-wg'
          },
          body: JSON.stringify({
            "orderItems": [
              { "productId": 1, "quantity": this.product1Qty },
              { "productId": 2, "quantity": this.product2Qty }
            ],
            "address": this.address
          })
        });

        if (response.ok || response.status === 201) {
          const data = await response.json();
          this.orderId = data.data;  // Extract orderId from the response body
          console.log('Order placed, ID:', this.orderId);

          // Subscribe to WebSocket updates for this order
          this.subscribeToWebSocket(this.orderId);

          // Subscribe to SSE updates for this order
          this.subscribeToSSE(this.orderId);
        } else {
          console.error('Failed to place order:', response.statusText);
        }
      } catch (error) {
        console.error('Error placing order:', error);
      }
    },

    // Subscribe to WebSocket updates for the given order ID
    subscribeToWebSocket(orderId) {
      const socket = new SockJS('http://localhost:8092/ws'); // WebSocket endpoint
      this.stompClient = Stomp.over(socket); // Use the Stomp object from @stomp/stompjs

      this.stompClient.connect({}, (frame) => {
        console.log('Connected to WebSocket:', frame);

        // Subscribe to the specific order topic
        this.stompClient.subscribe(`/topic/order/${orderId}`, (message) => {
          const response = JSON.parse(message.body);
          this.orderStatus = response.orderStatus;
          this.canceledReason = response.canceledReason;
        });
      });
    },

    // Subscribe to SSE updates for the given order ID
    subscribeToSSE(orderId) {
      this.eventSource = new EventSource(`http://localhost:8092/sse/orders/${orderId}/status`);

      this.eventSource.addEventListener('orderStatus', (event) => {
        console.log('SSE event: orderStatus', event.data);
      });

      this.eventSource.onerror = (error) => {
        console.error("SSE connection error", error);
        this.eventSource.close(); // Close SSE connection on error
      };
    }
  },

  beforeUnmount() {
    // Clean up SSE connection
    if (this.eventSource) {
      this.eventSource.close();
    }

    // Clean up WebSocket connection
    if (this.stompClient) {
      this.stompClient.disconnect();
    }
  }
};
</script>

<style scoped>
h1 {
  font-family: Arial, sans-serif;
}

.card {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

button {
  width: 100%;
}

h3 {
  margin-top: 20px;
}

p {
  font-size: 1.2em;
}
</style>
