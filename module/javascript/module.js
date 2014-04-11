// Expose the native API to javascript
forge.push = {
    showAlert: function (text, success, error) {
        forge.internal.call('push.showAlert', {text: text}, success, error);
    }
};

// Register for our native event
forge.internal.addEventListener("push.resume", function () {
	alert("Welcome back!");
});
