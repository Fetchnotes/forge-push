// Expose the native API to javascript
forge.push = {
    // Events
    'onPushNotificationReceivedWhileClosed': {
        'addListener': function (success) {
           forge.internal.addEventListener("push.pushNotificationReceivedWhileClosed", success);
        }
    },
    'onPushNotificationReceivedForeground': {
        'addListener': function (success) {
           forge.internal.addEventListener("push.pushNotificationReceivedForeground", success);
        }
    },
    'onPushNotificationReceivedBackground': {
        'addListener': function (success) {
           forge.internal.addEventListener("push.pushNotificationReceivedBackground", success);
        }
    },
    'onDidRegisterWithAPNS': {
        'addListener': function (success) {
            forge.internal.addEventListener("push.didRegisterWithAPNS", success);
        }
    }
}
