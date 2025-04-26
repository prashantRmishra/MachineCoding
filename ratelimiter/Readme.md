# Rate limiter with 5 request in 10 seconds

We use a Deque (double-ended queue) to store timestamps.
Every new request, we clean up timestamps older than the allowed window.
If the size of the deque is under the limit, we add the timestamp and allow the request
