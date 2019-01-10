package com.alokda.loginwithfacebook.View.Login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.alokda.loginwithfacebook.Model.User;
import com.alokda.loginwithfacebook.R;
import com.alokda.loginwithfacebook.View.Main.MainActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;

import static com.alokda.loginwithfacebook.Model.User.LoggedUser;


public class LoginActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    LoginButton loginButton;
    AccessToken accessToken;
    boolean isLoggedIn;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);


        accessToken = AccessToken.getCurrentAccessToken();
        isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            //Already Login
        }


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            getData(object);
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), getString(R.string.faceError), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }


                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name,last_name,email,id,public_profile");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), getString(R.string.cancel), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), getString(R.string.faceError), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToMain(User user) {
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        mainIntent.putExtra("Key", user);
        startActivity(mainIntent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);


        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getData(JSONObject object) throws JSONException {
        user = new User();
        // user.setEmail(object.getString("email"));
        user.setFirstName(object.getString("first_name"));
        user.setLastName(object.getString("last_name"));
        user.setProfileURL(object.getString("public_profile"));
        LoggedUser = user;
        goToMain(user);
    }

    void printHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.alokda.loginwithfacebook", PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));


            }

        } catch (Exception e) {
        }

    }
}
