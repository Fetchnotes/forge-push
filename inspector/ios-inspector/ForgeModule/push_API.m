#import "push_API.h"

#define kNotificationSettings UIUserNotificationTypeSound | UIUserNotificationTypeBadge | UIUserNotificationTypeAlert
#define kNotificationTypes UIRemoteNotificationTypeSound | UIRemoteNotificationTypeBadge | UIRemoteNotificationTypeAlert

@implementation push_API

+ (void)checkIfRegisteredWithAPN:(ForgeTask *)task
{
    [ForgeLog d:@"[FETCHNOTES] checkIfRegisteredWithAPNS"];

    UIApplication *application = [UIApplication sharedApplication];

    if ([application respondsToSelector:@selector(isRegisteredForRemoteNotifications)]) {

        // iOS 8 check
        if ([application isRegisteredForRemoteNotifications]) {
            [task success:nil];
        } else {
            [task error:nil];
        }

    } else if ([application enabledRemoteNotificationTypes] != UIRemoteNotificationTypeNone) {

        // iOS 7 check success
        [task success: nil];
    } else {

        // iOS 7 check failure
        [task error: nil];
    }
}

+ (void)registerWithAPNS:(ForgeTask *)task
{
    [ForgeLog d:@"[FETCHNOTES] registerWithAPNS"];

    UIApplication *application = [UIApplication sharedApplication];

    // Check if application responds to iOS 8 only notification method
    if ([application respondsToSelector:@selector(isRegisteredForRemoteNotifications)]) {

        // iOS 8 way of doing things
        [application registerForRemoteNotifications];

        // We should be able to leave categories nil since we're not yet doing anything special
        UIUserNotificationSettings *notificationSettings = [UIUserNotificationSettings settingsForTypes:kNotificationSettings categories:nil];
        [application registerUserNotificationSettings:notificationSettings];

    } else {

        // iOS 7 way of doing things
        [application registerForRemoteNotificationTypes:kNotificationTypes];

    }

    [task success: nil];
}

@end
