#import "push_EventListener.h"

@implementation push_EventListener

+ (void)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    NSDictionary *userInfo = [launchOptions objectForKey:UIApplicationLaunchOptionsRemoteNotificationKey];
        if (userInfo) {
            [ForgeLog d:@"[FETCHNOTES] Received Push Notification while closed"];
            [[ForgeApp sharedApp] event:@"push.pushNotificationReceivedWhileClosed" withParam:userInfo];
    }
}

+ (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken
{
    [ForgeLog d:@"[FETCHNOTES] didRegisterForRemoteNotificationsWithDeviceToken"];
    
    NSString *token = [[deviceToken description] stringByTrimmingCharactersInSet:[NSCharacterSet characterSetWithCharactersInString:@"<>"]];
    token = [token stringByReplacingOccurrencesOfString:@" " withString:@""];
    
    [[ForgeApp sharedApp] event:@"push.didRegisterWithAPNS" withParam:token];
}

+ (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error
{
    [ForgeLog d:@"[FETCHNOTES] didFailToRegisterForRemoteNotificationsWithDeviceToken"];
}

+ (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo {
    UIApplicationState state = [application applicationState];
    
    [ForgeLog d:@"[FETCHNOTES] didReceiveRemoteNotification"];
    
    if (state == UIApplicationStateActive)
    {
        [ForgeLog d:@"[FETCHNOTES] Received Push Notification while in foreground"];
        [[ForgeApp sharedApp] event:@"push.pushNotificationReceivedForeground" withParam:userInfo];
    } else {
        [ForgeLog d:@"[FETCHNOTES] Received Push Notification while in background"];
        [[ForgeApp sharedApp] event:@"push.pushNotificationReceivedBackground" withParam:userInfo];
    }
}

@end