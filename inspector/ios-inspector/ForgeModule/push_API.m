#import "push_API.h"

@implementation push_API

+ (void)checkIfRegisteredWithAPNS:(ForgeTask *)task
{
    [ForgeLog d:@"[FETCHNOTES] checkIfRegisteredWithAPNS"];
    
    if ([[UIApplication sharedApplication] enabledRemoteNotificationTypes] != 0) {
        [task success: nil];
    } else {
        [task error: nil];
    }
}

+ (void)registerWithAPNS:(ForgeTask *)task
{
    [ForgeLog d:@"[FETCHNOTES] registerWithAPNS"];
    
    [[UIApplication sharedApplication] registerForRemoteNotificationTypes:UIRemoteNotificationTypeBadge|UIRemoteNotificationTypeAlert|UIRemoteNotificationTypeSound];
    [task success: nil];
}

@end
