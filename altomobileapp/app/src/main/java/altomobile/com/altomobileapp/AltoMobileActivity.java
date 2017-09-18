package altomobile.com.altomobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import altomobile.com.altomobileapp.Animals.AnimalViewActivity;

public class AltoMobileActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private FacebookCallback fbCallBack = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {

            Toast toast = Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        public void onCancel() {
            Toast toast = Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG);
            toast.show();
        }

        @Override
        public void onError(FacebookException exception) {
            Toast toast = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG);
            toast.show();
        }
    };
    private AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {

        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null) {

                showComponents(View.GONE);
                email.setText("");
                emailString = "";
            } else {

                showComponents(View.VISIBLE);
                getUserStatusLogin();
            }
        }
    };

    private String emailString;
    private LoginButton loginBtn;
    private Button animalButton;
    private TextView email;
    private TextView emailLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alto_mobile);

        email = (TextView) findViewById(R.id.email);
        emailLabel = (TextView) findViewById(R.id.emailLabel);

        loginBtn = (LoginButton) findViewById(R.id.login_button);
        loginBtn.setReadPermissions("email");

        animalButton = (Button) findViewById(R.id.animalButton);
        animalButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AnimalViewActivity.class);
                startActivity(intent);
            }
        });

        callbackManager = CallbackManager.Factory.create();

        if ( AccessToken.getCurrentAccessToken() != null && !AccessToken.getCurrentAccessToken().isExpired() ) {

            this.showComponents(View.VISIBLE);
            this.getUserStatusLogin();
        } else
            this.showComponents(View.GONE);

        LoginManager.getInstance().registerCallback(callbackManager, fbCallBack);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void showComponents(int visibility) {

        animalButton.setVisibility(visibility);
        emailLabel.setVisibility(visibility);
        email.setVisibility(visibility);
    }

    private void getUserStatusLogin() {

        //Get the facebook email account
        final GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        Log.v("LoginActivity", response.toString());

                        // Application code
                        try {

                            emailString = object.getString("email");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "email");
        request.setParameters(parameters);
        Thread task = new Thread(new Runnable() {
            @Override
            public void run() {
                request.executeAndWait();
            }
        });

        task.start();

        try {

            task.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        email.setText(emailString);
    }
}
