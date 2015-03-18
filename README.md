# Twuni I/O

## Overview

Non-blocking I/O for everyone!

Build highly scalable event-oriented network services with a simplified, yet highly configurable, API.

To demonstrate how easy this is, here is a simple echo server:

    Acceptor.listen( 2020, new EventHandler() {
    
        @Override
        public void onData( Connection connection, byte [] data ) {
            connection.write( data );
        }
    
    } );

Congratulations! This server can handle thousands of concurrent connections without falling over.

## Advanced Configuration

The primary building blocks of the Twuni I/O library are the `EventHandler` object and the `Connection` object. A connection is writable and stateful, and an event handler is notified whenever a connection is **connected**, **disconnected**, when incoming **data** is ready to be processed, and when an **exception** occurs.

Typically, you would register a connection with your application in `EventHandler#onConnected`, unregister it from your application in `EventHandler#onDisconnected`, and forward data to your application for processing in `EventHandler#onData`.

If you roll your own `ConnectionFactory` and `Connection`, then you can attach all sorts of things to your connection which allow its state to be shared throughout the connection's lifespan.

There are some additional helper classes to lay the foundation for some common use cases, such as **authentication** (`Authenticator` and friends) and **packet relay** (`Transporter` and `Queue`).

## License

Copyright 2014 Twuni

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

