package android.paperwrrk.www.krezr;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity{

    private int FINE_LOCATION = 23;
    private AccountHeader headerResult = null;
    String user_Name = "GUEST";
    String user_Email = "guest@mbox.app.in";
    IProfile profile;
    private Drawer result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("");
        setSupportActionBar(toolbar);

        new DrawerBuilder().withActivity(this).build();

        requestLocationPermission();

        drawerImageLoader();


        profile = new ProfileDrawerItem().withName(user_Name).withEmail("futurelabs.app@gmail.com")
                .withIdentifier(100);

        createAccountHeader(savedInstanceState);
        createNavDrawer(toolbar);

    }

    public void finish(){
        super.finish();
        System.exit(0);
    }

    private void drawerImageLoader() {
    /*
            //initialize and create the image loader logic
            DrawerImageLoader.init(new AbstractDrawerImageLoader() {
                @Override
                public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                    Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
                }
                @Override
                public void cancel(ImageView imageView) {
                    Picasso.with(imageView.getContext()).cancelRequest(imageView);
                }
            });
    */
    }

    private void createNavDrawer(Toolbar toolbar) {
        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withSliderBackgroundColor(getApplicationContext().getResources().getColor(R.color.cstm_color))
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Refresh")
                                .withIdentifier(1),
                        new PrimaryDrawerItem().withName("Settings")
                                .withIdentifier(2),
                        new PrimaryDrawerItem().withName("About")
                                .withIdentifier(3),
                        new SectionDrawerItem().withName("Communicate"),
                        new SecondaryDrawerItem().withName("Share App")
                                .withIdentifier(5),
                        new SecondaryDrawerItem().withName("Contact Us")
                                .withIdentifier(6),
                        new SecondaryDrawerItem().withName("Rate App")
                                .withIdentifier(7)

                )

                .build();
    }

    private void createAccountHeader(@Nullable Bundle savedInstanceState) {
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.color.primary)
                .addProfiles(
                        profile
                )
                .withSavedInstance(savedInstanceState)
                .build();
    }





    //Requesting permission
    private void requestLocationPermission(){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                //If the user has denied the permission previously your code will come to this block
                //Here you can explain why you need this permission
                //Explain here why you need this permission
            }
            //And finally ask for the permission
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION);
        }
        //This method will be called when the user will tap on allow or deny
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            //Checking the request code of our request
            if(requestCode == FINE_LOCATION){
                //If permission is granted
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //Displaying a toast
                    Toast.makeText(this,"Permission granted to acess location",Toast.LENGTH_LONG).show();
                }else{
                    //Displaying another toast if permission is not granted
                    Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
                }
            }
            }


}
