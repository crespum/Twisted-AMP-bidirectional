Twisted-AMP-bidirectional
=========================

This repository contains some basic implementation of a Twisted AMP bidirectional protocol.
* **Server** is written in python.
* **Clients** are written in python and java.

Launching the server
--------------------
Create a virtualenv and install Twisted package: ```pip install Twisted```

Then execute ```python server_amp.py```

Launching the python client
---------------------------
Create a virtualenv and install Twisted package: ```pip install Twisted```

Then execute ```python client/python/client_amp.py```

Launching the java client
---------------------------
The java library that contains the AMP protocol has already been compiled to ```java-amp.jar```. The source code of this library is in [this GitHub repository](https://github.com/glyph/amp-java).

Compile the client: ```javac -cp '.:java-amp.jar' client/java/ClientAMP.java client/java/Commands.java```

Then execute ```java -cp '.:java-amp.jar' client/java/ClientAMP```
