package com.borka.facebookexample;



        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import android.content.Intent;
        import android.view.View;
        import android.widget.Button;

        import com.facebook.CallbackManager;
        import com.facebook.FacebookCallback;
        import com.facebook.FacebookException;
        import com.facebook.FacebookSdk;
        import com.facebook.GraphRequest;
        import com.facebook.GraphResponse;
        import com.facebook.appevents.AppEventsLogger;
        import com.facebook.login.LoginManager;
        import com.facebook.login.LoginResult;
        import com.facebook.login.widget.LoginButton;
        import com.facebook.share.ShareApi;
        import com.facebook.share.model.SharePhoto;
        import com.facebook.share.model.SharePhotoContent;
        import com.facebook.share.widget.ShareButton;

        import org.json.JSONObject;

        import java.util.Arrays;
        import java.util.List;


public class MainActivity extends Activity {
    //;
    CallbackManager callbackManager;
    private LoginManager loginManager;
    private ShareButton shareButton;
    Button btShareOnFacebook;
    private Bitmap image;
    //counter
    private int counter = 0;
    //;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //                    חייב להופיע לפני setContentView                  //
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        shareButton = (ShareButton) findViewById(R.id.share_btn);
        btShareOnFacebook = (Button) findViewById(R.id.btstartfb);
        btShareOnFacebook.setOnClickListener(new View.OnClickListener() {
            //;
            @Override
            public void onClick(View view) {
                //               רשימה לבקשת הרשאות מפייסבוק                       //
                List<String> permissionNeeds = Arrays.asList("publish_actions");
                //              מנהל הlogin של פייסבוק                         //;
                loginManager = LoginManager.getInstance();
                // מבקשים רשימת הרשאות (במקרה שלנו רק אחת)           //;
                loginManager.logInWithPublishPermissions(MainActivity.this, permissionNeeds);
                //;

                //             מאזינים לתשובה ע"י registerCallback                 //;
                loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        //   sharePhotoToFacebook();
                        postPicture();
                    }
                    //;
                    @Override
                    public void onCancel() {
                        System.out.println("onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        System.out.println("onError");
                    }
                });
            }
        });
        //;
    }
    //;
    //;
    public void postPicture() {
        //check counter
        if (counter == 0) {
            //save the screenshot
            //      נקבל מהמערכת את הView הראשי של האפליקציה                  //;
            View rootView = findViewById(android.R.id.content).getRootView();
            //                                                           //;
            //          לכידת צילום מסך של הVIEW הראשי                         //;
            rootView.setDrawingCacheEnabled(true);
            // שמירת צילום המסך למשתנה מסוג Bitmap                     //
            image = Bitmap.createBitmap(rootView.getDrawingCache());
            // destroy
            rootView.destroyDrawingCache();
            //share dialog
            AlertDialog.Builder shareDialog = new AlertDialog.Builder(this);
            shareDialog.setTitle("Share Screenshot");
            shareDialog.setMessage("Share image to Facebook?");
            shareDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //share the image to Facebook
                    SharePhoto photo = new SharePhoto.Builder().setBitmap(image).build();
                    SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();
                    shareButton.setShareContent(content);
                    counter = 1;
                    // shareButton.performClick();
                    ShareApi.share(content, null);
                }
            });
            shareDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            shareDialog.show();
        } else {
            counter = 0;
            shareButton.setShareContent(null);
        }
    }
    //;
    private void sharePhotoToFacebook() {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .setCaption("this is java class test !")
                .build();
        //;
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        //;
        ShareApi.share(content, null);
    }
    //;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    //;
    protected void getUserDetails(LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted( JSONObject json_object,
                                             GraphResponse response) {
                        Intent intent = new Intent(MainActivity.this, UserProfile.class);
                        intent.putExtra("userProfile", json_object.toString());
                        startActivity(intent);
                    }
                    //;
                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();
    }
    //;
    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
}
