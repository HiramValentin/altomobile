package altomobile.com.altomobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class AltoMobileActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private FacebookCallback fcCallBack = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {
            Toast toast = Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT);
            toast.show();

            Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
            startActivity(intent);
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

    private LoginButton loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alto_mobile);

        loginBtn = (LoginButton) findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();

        if ( AccessToken.getCurrentAccessToken() != null && !AccessToken.getCurrentAccessToken().isExpired() ) {

            Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
            startActivity(intent);
        }

        LoginManager.getInstance().registerCallback(callbackManager, fcCallBack);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
