# bot
Telegram bot for BTC exchange.<br>
Bot sends messages every 30 minutes.<br>

<b>Message format</b><br>
Best ask 9999.9999 USD<br>
Best bid 9999.9999 USD

<b>Bot handles two chat commands:</b><br>
<i>/start</i> - subscribes new user;<br>
<i>/stop</i> - unsubscribes user.

Subscribers are stored in MongoDB in Subscriber collection.
