forge-push
==========

Expose native device tokens &amp; local/remote push notifications to a UIWebView using Trigger.IO.

## Getting Started
To use this as your own trigger module, fork it and upload a new module to trigger's toolkit titled `push`. After enabling the module you can use any of the below methods and listeners as you see fit.

## Methods

#### Register with APNS
```js
forge.internal.call('push.registerWithAPNS', success, error);
```

#### Check APNS registration status
This can be used to check whether or a device has been previously registered with APNS. Refer below to point 3 for why this is useful.
```js
forge.internal.call('push.checkIfRegisteredWithAPNS', success, error);
```

## Listeners
#### Grab device token
APNS registration is done using the unique `deviceToken` generated after `didRegisterForRemoteNotificationsWithDeviceToken` fires. We grab the token, sanitize it, and expose it to the JS via the following listener. After calling `push.registerWithAPNS` use this capture the device token.
```js
forge.push.addEventListener('onDidRegisterWithAPNS', success, error);
```

#### Background Push notifications (closed app)
Capture push notifications received while the app is not running. The success object will contain the push notification payload.
```js
forge.push.addEventListener('onPushNotificationReceivedWhileClosed', success, error);
```

#### Background Push notifications (app running)
Capture push notifications received while the app is running and in the background. The success object will contain the push notification payload.
```js
forge.push.addEventListener('onPushNotificationReceivedBackground', success, error);
```

#### Foreground Push notifications
Capture push notifications received while the app is running in the foreground. The success object will contain the push notification payload.
```js
forge.push.addEventListener('onPushNotificationReceivedForeground', success, error);
```

## General Push Notification Background

1.  In setting up push notifications, it should be noted that a "distribution" Apple Push Notification Service (APNS) certificate differs from a "development" APNS certificate in that Apple has two separate APNS servers that DO NOT work together. A device registered on one will not receive push notifications from the other. 

2. Registering a device with a APNS will yield a `deviceToken`. This associates certain device(s) with a user.

3. The very first time that a device is registered with an APNS server, an alert prompts the user for access on their device. Successive attempts at registration do not bring up the prompt. Apple docs encourage registration on every app launch. It's often wise not to immediately bombard a user with the push prompt upon their first use. This gives you some control.

4. A push notification is just a small JSON payload with details like what alert sound to make, what to increment the badge to, and custom properties. IMPORTANT: It's limited in size to 256 bytes in total.

##License

Copyright (c) 2014, Fetchnotes Inc.
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies, 
either expressed or implied, of the FreeBSD Project.
