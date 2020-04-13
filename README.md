# bot
Telegram bot for BTC excange.<br>
Bot sends messages every 30 minutes.<br>

<b>Message format</b><br>
Best ask 9999.99 USD<br>
Best bid 9999.99 USD

<b>Bot handles two chat commands:</b><br>
<i>/start</i> - subscribes new user;<br>
<i>/stop</i> - unsubscribes user.

Subscribers are stored in MongoDB in Subscriber collection.
