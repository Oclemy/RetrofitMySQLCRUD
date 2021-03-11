package info.camposha.retrofitmysqlcrud.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import info.camposha.retrofitmysqlcrud.R;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.flip_page)
                .setDescription("We are a small development team located in Nairobi, Kenya. We specialize in android development,but also do C# and Python")
                .addGroup("Written By")
                .addItem(new Element("Clement Ochieng",R.drawable.flip_page))
                .addGroup("About This Project")
                .addWebsite("https://camposha.info/student-project/alien-planets-firebase/","Rate This Project")
                .addWebsite("https://camposha.info/student-projects/","Download Other Projects")
                .addWebsite("https://camposha.info/student-projects/","Become a Pro-Member")
                .addWebsite(" https://camposha.info/tutorials-list/","View our Latest Tutorials")
                .addGroup("Connect with us")
                .addEmail("oclemmi@gmail.com")
                .addWebsite("https://camposha.info")
                .addGitHub("Oclemy","Find us on Github")
                .create();
        setContentView(aboutPage);
    }

}